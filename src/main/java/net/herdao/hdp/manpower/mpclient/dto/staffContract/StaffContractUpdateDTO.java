
package net.herdao.hdp.manpower.mpclient.dto.staffContract;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 员工合同批量编辑DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工合同批量编辑DTO")
@ColumnWidth(20)
@HeadFontStyle
public class StaffContractUpdateDTO {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private Long staffId;

    @ExcelIgnore
    private String companyCode;

    /**
     * 是否当前生效合同 （合同是否生效）,0生效 ，1失效
     */
    @ExcelIgnore
    private Boolean newest;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名",index=0)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号",index=1)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffCode;

    /**
     * 劳动合同起始日期
     */
    @ApiModelProperty(value="劳动合同起始日期")
    @ExcelProperty(value = "劳动合同起始日期",index=2)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String startDate;

    /**
     * 劳动合同结束日期
     */
    @ApiModelProperty(value="劳动合同结束日期")
    @ExcelProperty(value = "劳动合同结束日期",index=3)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String endDate;

    /**
     * 合同签订主体
     */
    @ApiModelProperty(value="合同签订主体")
    @ExcelProperty(value = "合同签订主体",index=4)
    private String company;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    @ExcelProperty(value = "合同编号",index=5)
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String contractId;

    /**
     * 合同是否生效
     */
    @ApiModelProperty(value="合同是否生效")
    @ExcelProperty(value = "合同是否生效",index=6)
    private String contractStatus;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    @ExcelProperty(value = "合同期限类型",index=7)
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限")
    @ExcelProperty(value = "合同期限",index=8)
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String contractPeriod;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期",index=9)
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String probationMonth;

}
