
package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 员工教育批量编辑DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工教育批量编辑DTO")
@ColumnWidth(20)
@HeadFontStyle
public class StaffEduUpdateDTO {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private String staffId;

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

    @ExcelProperty(value = "入学日期",index =2)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String beginDate;

    @ExcelProperty(value = "毕业日期",index =3)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String endDate;

    @ExcelProperty(value = "毕业院校",index =4)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String schoolName;

    @ExcelProperty(value = "专业",index =5)
    private String professional;

    @ExcelProperty(value = "学历",index =6)
    private String educationQua;

    @ExcelProperty(value = "学位",index =7)
    private String educationDegree;

    @ExcelProperty(value = "学习形式",index =8)
    private String learnForm;
}
