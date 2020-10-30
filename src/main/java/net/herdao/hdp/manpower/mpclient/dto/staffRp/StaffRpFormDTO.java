package net.herdao.hdp.manpower.mpclient.dto.staffRp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "员工奖惩表单DTO")
@ColumnWidth(20)
public class StaffRpFormDTO {
    /**
     * ID
     */
    @ApiModelProperty(value="ID")
    private Long id;

    @ApiModelProperty(value="员工ID")
    private Long staffId;

    /**
     * 奖励/惩罚 0:奖励 1:惩罚
     */
    @ApiModelProperty(value="奖励/惩罚 1:奖励 2:惩罚")
    private String choice;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ApiModelProperty(value="奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择")
    private String type;

    /**
     * 奖惩内容
     */
    @ExcelProperty(value = "奖惩内容")
    @ApiModelProperty(value="奖惩内容")
    private String content;

    /**
     * 奖惩金额
     */
    @ExcelProperty(value = "奖惩金额")
    @ApiModelProperty(value="奖惩金额")
    private String amount;

    /**
     * 奖惩原因
     */
    @ExcelProperty(value = "奖惩原因")
    @ApiModelProperty(value="奖惩原因")
    private String reason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value="备注")
    private String remarks;
}
