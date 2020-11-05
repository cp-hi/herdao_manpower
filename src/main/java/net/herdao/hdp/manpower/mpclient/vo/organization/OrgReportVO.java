
package net.herdao.hdp.manpower.mpclient.vo.organization;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织报表-组织架构VO
 *
 * @author andy
 * @date 2020-10-18 10:37:22
 */
@Data
@ApiModel(value = "组织报表-组织架构VO")
public class OrgReportVO {

	/**
	 * 组织名称
	 */
	@ApiModelProperty(value = "组织名称")
	@ExcelProperty(value = "组织名称")
	private String orgName;

	/**
	 * 组织编码
	 */
	@ApiModelProperty(value = "组织编码")
	@ExcelProperty(value = "组织编码")
	private String orgCode;

	/**
	 * 上级组织名称
	 */
	@ApiModelProperty(value = "上级组织名称")
	@ExcelProperty(value = "上级组织名称")
	private String parentName;
	/**
	 * 上级组织
	 */
	@ApiModelProperty(value = "上级组织编码")
	@ExcelProperty(value = "上级组织编码")
	private String parentCode;

	@ApiModelProperty(value = "组织类型名称")
	@ExcelProperty(value = "组织类型名称")
	private String orgTypeName;
	
	@ApiModelProperty(value = "组织层级名称")
	@ExcelProperty(value = "组织层级名称")
	private String orgLevelName;
	
	/**
	 * 组织描述
	 */
	@ApiModelProperty(value = "组织描述")
	@ExcelProperty(value = "组织描述")
	private String orgDesc;

}
