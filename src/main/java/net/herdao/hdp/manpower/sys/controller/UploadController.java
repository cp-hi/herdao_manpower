/*
 *
 *      Copyright (c) 2018-2025, hdp All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: hdp
 *
 */

package net.herdao.hdp.manpower.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysDict;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.common.core.constant.CacheConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.listener.UserExcelListener;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.service.SysDictService;
import org.apache.commons.io.IOUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author andy
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/file")
@Api(value = "upload", tags = "文件上传")
@Slf4j
public class UploadController {
	private Environment env;

	/**
	 * 文件上传
	 * @param file 文件
	 * @return
	 */
	@PostMapping("/uploadFile")
 	public R uploadFile(@RequestParam(value = "file") MultipartFile file)  {
		try {
			if (file!=null && !file.isEmpty()){
				//文件上传的地址
				String path = ResourceUtils.getURL("").getPath()+env.getProperty("file.upload.address");
				String realPath = path.replace('/', '\\').substring(1,path.length());
				String fileName = file.getOriginalFilename();
				File tranFile = new File(realPath,fileName);
				file.transferTo(tranFile);
			}else {
				return R.ok("文件上传失败,请选择上传文件。");
			}
		}catch (Exception ex){
			log.error("文件上传失败。",ex);
			return R.ok("文件上传失败。");
		}

		log.info("文件上传成功。");
		return R.ok(" 文件上传成功。");
	}

	/**
	 * 文件下载
	 * @param request
     * @param response
	 * @return
	 */
	@GetMapping("/downloadFile")
	public R downloadFile(HttpServletRequest request, HttpServletResponse response)  {
		try {
			String fullPath = ResourceUtils.getURL("").getPath()+env.getProperty("file.upload.address")+"/核心人力表结构NEW-20200910.xlsx";
			File downloadFile = new File(fullPath);

			if (downloadFile.exists()){
				ServletContext context = request.getServletContext();
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					// set to binary type if MIME mapping not found
					mimeType = "application/octet-stream";
					System.out.println("context getMimeType is null");
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				InputStream myStream = new FileInputStream(fullPath);
				IOUtils.copy(myStream, response.getOutputStream());
				response.flushBuffer();
				log.info("文件下载成功。");
				return R.ok("文件下载成功。");
			}else{
				log.error("文件下载失败,文件不存在。");
				return R.failed("文件下载失败,文件不存在。");
			}
 		} catch (Exception e) {
 			log.error("文件下载失败。",e);
			return R.failed("文件下载失败");
		}
	}

	/**
	 * 图片预览预览
	 * @param response
	 * @return
	 */
	@RequestMapping("/previewPic")
	public void preview(HttpServletResponse response) throws FileNotFoundException {
		String uploadAddr = env.getProperty("file.upload.address");
		String path = ResourceUtils.getURL("").getPath()+uploadAddr+"/timg.jpg";
		String realPath = path.replace('/', '\\').substring(1,path.length());
		File file = new File(realPath);

		if (file.exists()) {
			InputStream in;
			try {
				in = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[1024];
				while (in.read(b) != -1) {
					os.write(b);
				}
				in.close();
				os.flush();
				os.close();
				log.info("文件预览成功。");
				//return R.failed("文件预览成功。");
			} catch (Exception e) {
 				log.error("文件预览失败。",e);
				//return R.failed("文件预览失败。");
			}
		}else {
			log.error("文件预览失败,文件不存在。");
			//return R.failed("文件预览失败,文件不存在。");
		}
	}
}
