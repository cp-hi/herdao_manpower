
package net.herdao.hdp.manpower.sys.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.oss.util.OssFileUtils;
import net.herdao.hdp.common.security.service.HdpUser;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.entity.StaffFile;
import net.herdao.hdp.manpower.mpclient.service.StaffFileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 *  远程文件操作
 * </p>
 * @author andy
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/remote/file")
@AllArgsConstructor
@Api(value = "remoteFile", tags = "远程文件操作")
@Slf4j
public class RemoteFileController {

	private Environment env;

	private StaffFileService staffFileService;

	/**
	 * 文件上传
	 * @param file 文件
	 * @author andy
	 * @return
	 */
	@PostMapping("/uploadFile")
	@Transactional(rollbackFor = Exception.class)
 	public R uploadFile(@RequestParam(value = "file") MultipartFile file)  {
		try {
			String uploadFileUrlDev = env.getProperty("upload.file.url.dev");
			HdpUser user = SecurityUtils.getUser();
			R result = OssFileUtils.uploadFile(file, "hdp", user.getId(), uploadFileUrlDev);
			if (result != null){
				//保存附件上传记录
				String fileId = result.getData().toString();
				String fileName= file.getOriginalFilename();
				StaffFile entity=new StaffFile();
				entity.setFileId(fileId);
				if (file!=null){
					if (fileName !=null && !fileName.isEmpty()){
						entity.setName(fileName);
					}
				}
				//传一个员工id
				entity.setStaffId(1L);
				staffFileService.saveOrUpdate(entity);
			}
 		}catch (Exception ex){
			log.error("文件上传失败。",ex);
			return R.failed("文件上传失败。");
		}

		log.info("文件上传成功。");
		return R.ok(" 文件上传成功。");
	}


	/**
	 * 文件下载
	 * @param fileId 文件ID
	 * @author andy
	 * @return
	 */
	@PostMapping("/downloadFile")
 	public R downloadFile(HttpServletResponse response,String fileId)  {
		try {
			String uploadFileUrlDev = env.getProperty("upload.file.url.dev");
			fileId="1619eae4-4fc7-45ae-9d04-8429a86671fb";
			String fileName="（新）蓝凌LBPM集成服务接口规范";
			OssFileUtils.downloadFile(response,fileId,fileName,uploadFileUrlDev);
		}catch (Exception ex){
			log.error("文件下载失败。",ex);
			return R.failed("文件下载失败。");
		}
		return R.failed("文件下载成功。");
	}

	/**
	 * 图片预览
	 * @param response
	 * @author andy
	 * @return
	 */
	@PostMapping("/previewPic")
 	public R previewPic(HttpServletResponse response)  {

	 	return  null;
	}

}
