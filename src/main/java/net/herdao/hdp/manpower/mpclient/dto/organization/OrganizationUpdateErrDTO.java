package net.herdao.hdp.manpower.mpclient.dto.organization;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 组织批量修改异常处理
 * @author      shuling
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "组织批量修改异常处理")
@ExcelIgnoreUnannotated
public class OrganizationUpdateErrDTO {

    @ExcelProperty(value = "错误信息", index = 0)
    @ColumnWidth(75)
    private String errMsg;
    
    @ExcelProperty(value = "组织名称", index = 1)
    @ColumnWidth(30)
	private String orgName;

	@ExcelProperty(value = "组织类型", index = 2)
	@ColumnWidth(30)
	private String orgType;
	
	@ExcelProperty(value = "上级组织编码", index = 3)
	@ColumnWidth(30)
	private String parentOrgCode;
	
	@ExcelProperty(value = "组织负责人工号", index = 4)
	@ColumnWidth(30)
	private String orgChargeWorkNo;
	
	@ExcelProperty(value = "岗位编码", index = 5)
	@ColumnWidth(30)
	private String postCode;


}
