
package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 员工教育批量导入DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工教育批量导入DTO")
public class StaffEduAddDTO {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private String staffId;

    @ExcelProperty(value = "员工姓名",index =0)
    @NotBlank(message = "不能为空")
    private String staffName;

    @ExcelProperty(value = "员工工号",index =1)
    @NotBlank(message = "不能为空")
    private String staffCode;

    @ExcelProperty(value = "入学日期",index =2)
    @NotBlank(message = "不能为空")
    private String beginDate;

    @ExcelProperty(value = "毕业日期",index =3)
    @NotBlank(message = "不能为空")
    private String endDate;

    @ExcelProperty(value = "毕业院校",index =4)
    @NotBlank(message = "不能为空")
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
