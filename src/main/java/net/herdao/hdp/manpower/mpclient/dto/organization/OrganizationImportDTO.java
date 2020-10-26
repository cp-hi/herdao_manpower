package net.herdao.hdp.manpower.mpclient.dto.organization;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织批量新增、修改 DTO
 * 
 * @author shuling
 * @date 2020-10-22 10:15:22
 * @version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "组织批量修改")
public class OrganizationImportDTO {

	@ApiModelProperty(value = "组织id")
	private Long id;
	
	@ApiModelProperty(value = "组织名称")
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ApiModelProperty(value = "组织类型")
	private String orgType;
	
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ApiModelProperty(value = "上级组织编码")
	private String parentOrgCode;

	@ApiModelProperty(value = "组织负责人id")
	private String orgChargeId;

	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeName;
	
	@ApiModelProperty(value = "组织负责人工号")
	private String orgChargeWorkNo;

	@ApiModelProperty(value = "负责岗位id")
	private Long postId;
	
	@ApiModelProperty(value = "岗位编码")
	private String postCode;
	
	@ApiModelProperty(value = "是否虚拟组织")
	private String isVirtual;

	@ApiModelProperty(value = "是否项目/中心及以上")
	private String organizational;

	@ApiModelProperty(value = "福利类型")
	private String welfareType;
	
	@ApiModelProperty(value = "组织描述")
	private String orgDesc;
}
