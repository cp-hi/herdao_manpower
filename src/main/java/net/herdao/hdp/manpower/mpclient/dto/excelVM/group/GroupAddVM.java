package net.herdao.hdp.manpower.mpclient.dto.excelVM.group;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangrr
 *
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "集团批量新增")
@HeadFontStyle
@ColumnWidth(30)
public class GroupAddVM {

    @ExcelProperty(value = "集团名称", index = 0)
    @ApiModelProperty(value="集团名称")
    private String groupName;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ExcelProperty(value = "对应组织编码", index = 1)
    @ApiModelProperty(value="对应组织编码")
    private String orgCode;

    @ExcelProperty(value = "排序", index = 2)
    @ApiModelProperty(value="排序")
    private Integer sortNo;

    @ExcelProperty(value = "绩效计算标准", index = 3)
    @ApiModelProperty(value="绩效计算标准")
    private String achieveCalculateStandard;

    @ExcelProperty(value = "备注", index = 4)
    @ApiModelProperty(value="备注")
    private String remark;

}
