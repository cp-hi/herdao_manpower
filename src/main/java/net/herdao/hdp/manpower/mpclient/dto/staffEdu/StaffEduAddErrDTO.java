package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffEduAddErrDTO {

    @ExcelProperty(index =0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;

    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private String staffId;

    @ExcelProperty(value = "员工姓名",index =1)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    @ExcelProperty(value = "员工工号",index =2)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffCode;

    @ExcelProperty(value = "入学日期",index =3)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String beginDate;

    @ExcelProperty(value = "毕业日期",index =4)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String endDate;

    @ExcelProperty(value = "毕业院校",index =5)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String schoolName;

    @ExcelProperty(value = "专业",index =6)
    private String professional;

    @ExcelProperty(value = "学历",index =7)
    private String educationQua;

    @ExcelProperty(value = "学位",index =8)
    private String educationDegree;

    @ExcelProperty(value = "学习形式",index =9)
    private String learnForm;

}
