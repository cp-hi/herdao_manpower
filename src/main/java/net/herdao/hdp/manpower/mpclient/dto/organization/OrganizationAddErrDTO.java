package net.herdao.hdp.manpower.mpclient.dto.organization;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;

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
@ColumnWidth(30)
public class OrganizationAddErrDTO {

    @ExcelProperty(value = "错误信息", index = 0)
    @ColumnWidth(75)
    private String errMsg;
    
    @ExcelProperty(value = "组织名称", index = 1)
	@HeadFontStyle(color = 10)
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	@ExcelProperty(value = "组织类型", index = 2)
	@HeadFontStyle(color = 10)
	private String orgType;
	
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ExcelProperty(value = "上级组织编码", index = 3)
	@HeadFontStyle(color = 10)
	private String parentOrgCode;
	
	@ExcelProperty(value = "组织负责人工号", index = 4)
	private String orgChargeWorkNo;
	
	// TOTO 需要通过岗位名称与当前用户的集团信息做关联查询
	@ExcelProperty(value = "负责岗位", index = 5)
	private String postCode;


}
