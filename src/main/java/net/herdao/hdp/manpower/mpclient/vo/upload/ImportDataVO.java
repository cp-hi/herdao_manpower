package net.herdao.hdp.manpower.mpclient.vo.upload;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 导入接收参数
 * 
 * @author  shuling
 * @date    2020-10-28 10:47:32
 */
@Data
public class ImportDataVO {
	/**
	 * 导入文件
	 */
	@ApiModelProperty(value = "导入文件")
	MultipartFile file;
	/**
	 * 导入类型，值： 0  批量新增； 值 1 批量编辑
	 */
	@ApiModelProperty(value = "导入类型，值： 0  批量新增； 值 1 批量编辑")
	Integer importType;
	/**
	 * 是否下载错误信息，值：0 或空 不下载 值：1 下载
	 */
	@ApiModelProperty(value = "是否下载错误信息，值：0 或空 不下载 值：1 下载")
	Integer downloadErrMsg;
}
