
package net.herdao.hdp.manpower.mpclient.dto.staffRp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 员工奖惩批量新增DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工奖惩批量新增DTO")
@ColumnWidth(20)
@HeadFontStyle
public class StaffRpAddDTO {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private Long staffId;

    @ExcelProperty(value = "员工姓名",index =0)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    @ExcelProperty(value = "员工工号",index =1)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffCode;

    /**
     * 奖励/惩罚 0:奖励 1:惩罚
     */
    @ExcelProperty(value = "奖励/惩罚",index =2)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String choice;

    /**
     * 奖惩时间
     */
    @ExcelProperty(value="奖惩时间", index =3)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String executeDate;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ExcelProperty(value = "奖惩类别",index =4)
    @HeadFontStyle(color = 10)
    private String type;

    /**
     * 奖惩内容
     */
    @ExcelProperty(value = "奖惩内容",index =5)
    private String content;

    /**
     * 奖惩金额
     */
    @ExcelProperty(value = "奖惩金额",index =6)
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String amount;

    /**
     * 奖惩原因
     */
    @ExcelProperty(value = "奖惩原因",index =7)
    private String reason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注",index =8)
    private String remarks;
}
