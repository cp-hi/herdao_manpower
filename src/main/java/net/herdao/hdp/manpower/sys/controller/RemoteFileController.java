
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
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

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
			R result = OssFileUtils.uploadFile(file,"hdc", "ftp"+File.separator+"hdp", user.getId(), uploadFileUrlDev);
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
 	public void downloadFile(HttpServletResponse response,String fileId)  {
		try {
			String downFileUrlDev = env.getProperty("download.file.url.dev");
			fileId="f083cae5-b049-4f1a-81c0-5ef326ba19d7";
			String fileName="1111.jpg";


			InputStream input = OssFileUtils.getFileInputStream(downFileUrlDev, fileId); // 得到输入流
			// 获取输入流 end

			fileName = fileName.replaceAll(" ", "");

			String outFileName = URLDecoder.decode(fileName, "utf-8");

			outFileName = new String(outFileName.getBytes("GB2312"), "ISO_8859_1");

			response.setHeader("Content-Type", "application/octet-stream"); // 设置被下载而不是被打开
			response.setHeader("Content-Disposition", "attachment;filename=" + outFileName); // 设置被第三方工具打开，设置下载的文件名
			OutputStream output = null;
			try {
				output = response.getOutputStream();
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = input.read(bytes)) != -1) {
					output.write(bytes, 0, len);
				}
				output.flush();
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException e) {
					log.error(e.getLocalizedMessage());
				}
			}

		}catch (Exception ex){
			log.error("文件下载失败。",ex);

		}

	}

	/**
	 * 图片预览
	 * @param response
	 * @author andy
	 * @return
	 */
	@PostMapping("/previewPic")
 	public R previewPic(HttpServletResponse response)  {
		try {
			String downloadAddr = env.getProperty("download.file.url.dev");
			InputStream fileInputStream = OssFileUtils.getFileInputStream(downloadAddr, "f083cae5-b049-4f1a-81c0-5ef326ba19d7");
 			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			while (fileInputStream.read(b) != -1) {
				os.write(b);
			}
			fileInputStream.close();
			os.flush();
			os.close();
			log.info("文件预览成功。");
			//return R.failed("文件预览成功。");
		} catch (Exception e) {
			log.error("文件预览失败。",e);
			//return R.failed("文件预览失败。");
		}
	 	return  null;
	}

}
