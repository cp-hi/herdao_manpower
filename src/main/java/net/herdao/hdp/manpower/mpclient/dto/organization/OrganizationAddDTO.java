package net.herdao.hdp.manpower.mpclient.dto.organization;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织导入 DTO
 * 
 * @author shuling
 * @date 2020-10-22 10:15:22
 * @version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "组织导入")
public class OrganizationAddDTO {

	@ExcelProperty("组织名称")
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ExcelProperty(value = "组织类型")
	private String orgType;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ExcelProperty(value = "上级组织编码")
	private String parentOrgCode;

	@ApiModelProperty(value = "组织负责人id")
	private String orgChargeId;

	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeName;
	
	@ExcelProperty(value = "组织负责人工号")
	private String orgChargeWorkNo;

	@ApiModelProperty(value = "负责岗位id")
	private Long postId;
	
	@ExcelProperty(value = "岗位编码")
	private String postCode;

}
