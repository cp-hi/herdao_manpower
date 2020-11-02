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
@ApiModel(value = "注册公司批量编辑")
@HeadFontStyle
@ColumnWidth(30)
public class CompanyUpdateVM {

    @ApiModelProperty(value="公司编码")
    private String companyCode;

    @ApiModelProperty(value="公司名称")
    @ExcelProperty(value = "对应组织编码", index = 0)
    private String companyName;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ExcelProperty(value = "对应组织编码", index = 1)
    @ApiModelProperty(value="对应组织编码")
    private String orgCode;

    @ExcelProperty(value = "对应组织编码", index = 2)
    @ApiModelProperty(value="目标系统")
    private String targetSys;

    @ExcelProperty(value = "对应组织编码", index = 3)
    @ApiModelProperty(value="保险标准")
    private String securityStandard;

    @ExcelProperty(value = "对应组织编码", index = 4)
    @ApiModelProperty(value="所在城市ID")
    private Long cityId;

    @ExcelProperty(value = "对应组织编码", index = 5)
    @ApiModelProperty(value="公积金标准")
    private String cityFund;

    @ExcelProperty(value = "对应组织编码", index = 6)
    @ApiModelProperty(value="工会费比例")
    private BigDecimal lohnkostenRate;

    @ExcelProperty(value = "对应组织编码", index = 7)
    @ApiModelProperty(value="保险科目")
    private String securitySubject;

    @ExcelProperty(value = "对应组织编码", index = 8)
    @ApiModelProperty(value="支付公司账号")
    private String payAcctNo;

    @ExcelProperty(value = "对应组织编码", index = 9)
    @ApiModelProperty(value="纳税人识别码")
    private String taxerNo;

    @ExcelProperty(value = "对应组织编码", index = 10)
    @ApiModelProperty(value="排序")
    private Long sort;

    @ExcelProperty(value = "对应组织编码", index = 11)
    @ApiModelProperty(value="备注")
    private String remark;

}
