

package net.herdao.hdp.manpower.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import java.net.URLEncoder;

/**
 * <p>
 * 本地文件操作
 * </p>
 *
 * @author andy
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/file")
@Api(value = "file", tags = "本地文件操作")
@Slf4j
public class LocalFileController {
	private final Environment env;

	/**
	 * 文件上传
	 * @param file 文件
	 * @param secondTypeId 二级附件分类ID
	 * @return
	 */
	@PostMapping("/uploadFile")
	@ApiImplicitParams({
	    @ApiImplicitParam(name="file",value="上传文件"),
	 	@ApiImplicitParam(name="secondTypeId",value="二级附件分类ID")
	})
 	public R uploadFile(@RequestParam(value = "file") MultipartFile file,Long secondTypeId)  {
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
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName)  {
		try {
			String fullPath = ResourceUtils.getURL("").getPath()+env.getProperty("local.file.upload.address")+File.separator+fileName;
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

				fileName = URLEncoder.encode(fileName, "UTF-8");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"",fileName);
				response.setHeader(headerKey, headerValue);
				InputStream myStream = new FileInputStream(fullPath);
				IOUtils.copy(myStream, response.getOutputStream());
				response.flushBuffer();
				log.info("文件下载成功。");
				//return R.ok("文件下载成功。");
			}else{
				log.error("文件下载失败,文件不存在。");
				/*return R.failed("文件下载失败,文件不存在。");*/
			}
 		} catch (Exception e) {
 			log.error("文件下载失败。",e);
			/*return R.failed("文件下载失败");*/
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
