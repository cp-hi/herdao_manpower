package net.herdao.hdp.manpower.mpclient.dto.excelVM.staff;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author yangrr
 *
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "人员批量新增")
@HeadFontStyle
@ColumnWidth(30)
public class StaffUpdateVM {

    @ExcelProperty(value = "姓名", index = 0)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    @ExcelProperty(value = "移动电话", index = 1)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String mobile;
}
