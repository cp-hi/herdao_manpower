package net.herdao.hdp.manpower.mpclient.dto.staffFamily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffFamilyExcelErrDTO {

    @ExcelProperty(index =0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;

    @ExcelProperty(value = "员工姓名",index =1)
    @NotBlank(message = "不能为空")
    private String staffName;

    @ExcelProperty(value = "员工工号",index =2)
    @NotBlank(message = "不能为空")
    private String staffCode;

    /**
     * 奖励/惩罚 0:奖励 1:惩罚
     */
    @ExcelProperty(value = "奖励/惩罚",index =3)
    private String choice;

    /**
     * 奖惩时间
     */
    @ExcelProperty(value="奖惩时间", index =4)
    private String executeDate;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ExcelProperty(value = "奖惩类别",index =5)
    private String type;

    /**
     * 奖惩内容
     */
    @ExcelProperty(value = "奖惩内容",index =6)
    private String content;

    /**
     * 奖惩金额
     */
    @ExcelProperty(value = "奖惩金额",index =7)
    private String amount;

    /**
     * 奖惩原因
     */
    @ExcelProperty(value = "奖惩原因",index =8)
    private String reason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注",index =9)
    private String remarks;

}
