package net.herdao.hdp.manpower.mpclient.dto.attachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回的 文件参数
 *
 * @author zengjinbao
 * @date 2020-12-09
 */
@Data
@ApiModel(value = "文件数据")
public class HerDaoFileDTO {

	/**
	 * 源文件名称
	 */
	@ApiModelProperty(value = "源文件名称")
	private String originalFilename;
	
	/**
	 * 保存文件名
	 */
	@ApiModelProperty(value = "保存文件名")
	private String fileName;

	/**
	 * 拓展名
	 */
	@ApiModelProperty(value = "拓展名")
	private String extend;
	
	/**
	 * 文件路径
	 */
	@ApiModelProperty(value = "文件路径")
	private String ftpFilePath;
	
	/**
	 * 调用远程返回的fileId
	 */
	@ApiModelProperty(value = "调用远程返回的fileId")
	private String fileId;
	
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private Long fileSize;
	
	/**
	 * 直接下载的URL
	 */
	@ApiModelProperty(value = "直接下载的URL")
	private String url;
	
}
