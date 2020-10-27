package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffEduExcelErrDTO {

    @ExcelProperty(index =0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;

    @ExcelProperty(value = "员工姓名",index =1)
    private String staffName;

    @ExcelProperty(value = "员工工号",index =2)
    private String staffCode;

    @ExcelProperty(value = "入学日期",index =3)
    private String beginDate;

    @ExcelProperty(value = "毕业日期",index =4)
    private String endDate;

    @ExcelProperty(value = "毕业院校",index =5)
    private String schoolName;

    @ExcelProperty(value = "专业",index =6)
    private String professional;

    @ExcelProperty(value = "学位",index =7)
    private String educationDegree;

    @ExcelProperty(value = "学历",index =8)
    private String educationQua;

    @ExcelProperty(value = "学习形式",index =9)
    private String learnForm;

}
