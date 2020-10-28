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
 * 组织批量修改 DTO
 * 
 * @author shuling
 * @date 2020-10-22 10:15:22
 * @version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "组织批量修改")
@HeadFontStyle
@ColumnWidth(30)
public class OrganizationUpdateDTO {

	@ExcelProperty(value = "组织名称", index = 0)
	@HeadFontStyle(color = 10)
	@Valid
	@NotBlank(message = "不能为空")
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ExcelProperty(value = "组织类型", index = 1)
	private String orgType;
	
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ExcelProperty(value = "上级组织编码", index = 2)
	@HeadFontStyle(color = 10)
	@Valid
	@NotBlank(message = "不能为空")
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
	
	@ExcelProperty(value = "是否虚拟组织", index = 5)
	private String isVirtual;

	@ExcelProperty(value = "是否项目/中心及以上", index = 6)
	private String organizational;

	@ExcelProperty(value = "福利类型", index = 7)
	private String welfareType;
	
	@ExcelProperty(value = "组织描述", index = 8)
	private String orgDesc;
}
