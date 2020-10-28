package net.herdao.hdp.manpower.mpclient.dto.organization;

import javax.validation.constraints.NotBlank;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 组织批量新增异常处理
 * @author      shuling
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "组织批量新增异常处理")
@ExcelIgnoreUnannotated
public class OrganizationAddErrDTO {

    @ExcelProperty(value = "错误信息", index = 0)
    @ColumnWidth(75)
    private String errMsg;
    
    @ExcelProperty(value = "组织名称", index = 1)
	@ColumnWidth(30)
	@NotBlank(message = "不能为空")
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ExcelProperty(value = "组织类型", index = 2)
	@ColumnWidth(30)
	@NotBlank(message = "不能为空")
	private String orgType;
	
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ExcelProperty(value = "上级组织编码", index = 3)
	@ColumnWidth(30)
	private String parentOrgCode;

	@ApiModelProperty(value = "组织负责人id")
	private String orgChargeId;

	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeName;
	
	@ExcelProperty(value = "组织负责人工号", index = 4)
	@ColumnWidth(30)
	@NotBlank(message = "不能为空")
	private String orgChargeWorkNo;

	@ApiModelProperty(value = "负责岗位id")
	private Long postId;
	
	@ExcelProperty(value = "岗位编码", index = 5)
	@ColumnWidth(30)
	@NotBlank(message = "不能为空")
	private String postCode;
	
	@ExcelProperty(value = "是否虚拟组织", index = 6)
	private String isVirtualStr;

	@ExcelProperty(value = "是否项目/中心及以上", index = 7)
	private String organizationalStr;

	@ExcelProperty(value = "福利类型", index = 8)
	private String welfareTypeStr;
	
	@ExcelProperty(value = "组织描述", index = 9)
	private String orgDesc;


}
