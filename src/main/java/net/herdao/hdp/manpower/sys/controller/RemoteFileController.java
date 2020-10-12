
package net.herdao.hdp.manpower.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	@ApiImplicitParams({
		@ApiImplicitParam(name="file",value="文件")
	})
 	public R uploadFile(@RequestParam(value = "file") MultipartFile file)  {
		try {
			if (file ==null || file.isEmpty()){
				return R.failed("请选择上传文件！");
			}

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

				//获得文件后缀名 文件类型
 				int begin = file.getOriginalFilename().indexOf(".");
				int last = file.getOriginalFilename().length();
				String fileType = file.getOriginalFilename().substring(begin, last);
				//保存文件后缀名 文件类型
				entity.setFileType(fileType);

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
			String downFileUrlDev = env.getProperty("download.file.url.dev");
			StaffFile staffFile = staffFileService.getOne(new QueryWrapper<StaffFile>().eq("file_id", fileId));
			if (staffFile!=null){
				OssFileUtils.downloadFile(response,fileId,staffFile.getName(),downFileUrlDev);
			}else{
				return R.failed("无对应的下载文件！");
			}

		}catch (Exception ex){
			log.error("文件下载失败！",ex);
			return R.failed("文件下载失败！");
		}

		return R.ok("文件下载成功。");
	}

	/**
	 * 图片预览
	 * @param response
	 * @author andy
	 * @return
	 */
	@GetMapping("/previewPic")
	@ApiImplicitParams({
		@ApiImplicitParam(name="fileId",value="文件ID")
	})
 	public void previewPic(HttpServletResponse response,String fileId)  {
		try {
			String downloadAddr = env.getProperty("download.file.url.dev");
			InputStream fileInputStream = OssFileUtils.getFileInputStream(downloadAddr, fileId);
 			OutputStream os = response.getOutputStream();

			byte[] bytes = new byte[1024];
			boolean var8 = false;

			int len;
			while((len = fileInputStream.read(bytes)) != -1) {
				os.write(bytes, 0, len);
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

	}

}
