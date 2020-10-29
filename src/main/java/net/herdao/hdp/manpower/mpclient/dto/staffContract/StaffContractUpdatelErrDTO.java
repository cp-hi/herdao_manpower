package net.herdao.hdp.manpower.mpclient.dto.staffContract;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffContractUpdatelErrDTO {

    @ExcelProperty(index =0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名",index=1)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号",index=2)
    private String staffCode;

    /**
     * 劳动合同起始日期
     */
    @ApiModelProperty(value="劳动合同起始日期")
    @ExcelProperty(value = "劳动合同起始日期",index=3)
    private String startDate;

    /**
     * 劳动合同结束日期
     */
    @ApiModelProperty(value="劳动合同结束日期")
    @ExcelProperty(value = "劳动合同结束日期",index=4)
    private String endDate;

    /**
     * 合同签订主体
     */
    @ApiModelProperty(value="合同签订主体")
    @ExcelProperty(value = "合同签订主体",index=5)
    private String company;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    @ExcelProperty(value = "合同编号",index=6)
    private String contractId;

    /**
     * 合同是否生效
     */
    @ApiModelProperty(value="合同是否生效")
    @ExcelProperty(value = "合同是否生效",index=7)
    private String contractStatus;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    @ExcelProperty(value = "合同期限类型",index=8)
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限")
    @ExcelProperty(value = "合同期限",index=9)
    private String contractPeriod;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期",index=10)
    private String probationMonth;
}
