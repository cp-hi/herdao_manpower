package net.herdao.hdp.manpower.mpclient.dto.organization;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织批量新增 DTO
 * 
 * @author shuling
 * @date 2020-10-22 10:15:22
 * @version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "组织批量新增")
@HeadFontStyle
@ColumnWidth(30)
public class OrganizationAddDTO {
	
	@ExcelProperty(value = "组织名称", index = 0)
	@Valid
	@NotBlank(message = "不能为空")
	@HeadFontStyle(color = 10)
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ExcelProperty(value = "组织类型", index = 1)
	@Valid
	@NotBlank(message = "不能为空")
	private String orgType;
	
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ExcelProperty(value = "上级组织编码", index = 2)
	@HeadFontStyle(color = 10)
	private String parentOrgCode;

	@ApiModelProperty(value = "组织负责人id")
	private String orgChargeId;

	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeName;
	
	@ExcelProperty(value = "组织负责人工号", index = 3)
	private String orgChargeWorkNo;

	@ApiModelProperty(value = "负责岗位id")
	private Long postId;
	
	// TOTO 需要通过岗位名称与当前用户的集团信息做关联查询
	@ExcelProperty(value = "负责岗位", index = 4)
	private String postCode;

}
