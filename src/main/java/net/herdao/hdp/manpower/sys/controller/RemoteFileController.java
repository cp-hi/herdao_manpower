
package net.herdao.hdp.manpower.sys.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.oss.util.OssFileUtils;
import net.herdao.hdp.common.security.service.HdpUser;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

	/**
	 * 文件上传
	 * @param file 文件
	 * @return
	 */
	@PostMapping("/uploadFile")
 	public R uploadFile(@RequestParam(value = "file") MultipartFile file)  {
		try {
			String uploadFileUrlDev = env.getProperty("upload.file.url.dev");
			HdpUser user = SecurityUtils.getUser();
			R result = OssFileUtils.uploadFile(file, "hdp", user.getId(), uploadFileUrlDev);
			log.info(result.toString());
		}catch (Exception ex){
			log.error("文件上传失败。",ex);
			return R.ok("文件上传失败。");
		}

		log.info("文件上传成功。");
		return R.ok(" 文件上传成功。");
	}


}
