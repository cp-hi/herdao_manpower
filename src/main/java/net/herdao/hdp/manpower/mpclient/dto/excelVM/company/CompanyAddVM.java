package net.herdao.hdp.manpower.mpclient.dto.excelVM.company;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangrr
 *
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "注册公司批量新增")
@HeadFontStyle
@ColumnWidth(30)
public class CompanyAddVM {

    @ApiModelProperty(value="公司编码")
    private String companyCode;

    @ExcelProperty(value = "公司名称", index = 0)
    @ApiModelProperty(value="公司名称")
    @HeadFontStyle(color = 10)
    private String companyName;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ExcelProperty(value = "对应组织编码", index = 1)
    @ApiModelProperty(value="对应组织编码")
    @HeadFontStyle(color = 10)
    private String orgCode;

    @ExcelProperty(value = "目标系统", index = 2)
    @ApiModelProperty(value="目标系统")
    private String targetSys;

    @ExcelProperty(value = "保险标准", index = 3)
    @ApiModelProperty(value="保险标准")
    private String securityStandard;

    @ExcelProperty(value = "所在城市ID", index = 4)
    @ApiModelProperty(value="所在城市ID")
    private Long cityId;

    @ExcelProperty(value = "公积金标准", index = 5)
    @ApiModelProperty(value="公积金标准")
    private String cityFund;

    @ExcelProperty(value = "工会费比例", index = 6)
    @ApiModelProperty(value="工会费比例")
    private BigDecimal lohnkostenRate;

    @ExcelProperty(value = "保险科目", index = 7)
    @ApiModelProperty(value="保险科目")
    private String securitySubject;

    @ExcelProperty(value = "支付公司账号", index = 8)
    @ApiModelProperty(value="支付公司账号")
    private String payAcctNo;

    @ExcelProperty(value = "纳税人识别码", index = 9)
    @ApiModelProperty(value="纳税人识别码")
    private String taxerNo;

    @ExcelProperty(value = "排序", index = 10)
    @ApiModelProperty(value="排序")
    private Long sort;

    @ExcelProperty(value = "备注", index = 11)
    @ApiModelProperty(value="备注")
    private String remark;

}
