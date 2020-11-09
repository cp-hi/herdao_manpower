package net.herdao.hdp.manpower.mpclient.dto.excelVM.staff;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author yangrr
 *
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "人员批量新增")
@HeadFontStyle
@ColumnWidth(30)
public class StaffAddVM {

    @ExcelProperty(value = "员工姓名", index = 0)
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ExcelProperty(value = "证件类型", index = 1)
    @ApiModelProperty(value="证件类型")
    private String idType;

    @ExcelProperty(value = "证件号码", index = 2)
    @ApiModelProperty(value="证件号码")
    private String idNumber;

    @ExcelProperty(value = "移动电话", index = 3)
    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ExcelProperty(value = "部门编码", index = 4)
    @ApiModelProperty(value="部门编码")
    private String orgCode;

    @ExcelProperty(value = "岗位编码", index = 5)
    @ApiModelProperty(value="岗位编码")
    private String postCode;

    @ExcelProperty(value = "人员性质", index = 6)
    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    @ExcelProperty(value = "人员归属范围", index = 7)
    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @ExcelProperty(value = "任职类型", index = 8)
    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ExcelProperty(value = "劳动合同签订主体", index = 9)
    @ApiModelProperty(value="劳动合同签订主体")
    private String contractCompany;

    @ExcelProperty(value = "入职日期", index = 10)
    @ApiModelProperty(value="入职日期")
    private String entryTimeStr;

    @ApiModelProperty(value="入职本公司日期")
    private LocalDate entryTime;
}
