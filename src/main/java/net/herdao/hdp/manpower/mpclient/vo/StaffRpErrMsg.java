package net.herdao.hdp.manpower.mpclient.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;

import java.util.Date;

@Data
@ApiModel(value = "家庭情况分页VO")
public class StaffRpErrMsg extends StaffRewardsPulishments implements ExcelMsg {
    
    @ExcelProperty(value = "错误信息")
    @ColumnWidth(100)
    private String errMsg;

    /**
     * ID
     */
    @ApiModelProperty(value="ID")
    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "员工姓名")
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ExcelProperty(value = "员工工号")
    @ApiModelProperty(value="员工工号")
    private String staffCode;

    /**
     * 奖励/惩罚 0:奖励 1:惩罚
     */
    @ExcelProperty(value = "奖励/惩罚")
    @ApiModelProperty(value="奖励/惩罚 0:奖励 1:惩罚")
    private String choiceName;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ExcelProperty(value = "奖惩类别")
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

    /**
     * 奖惩时间
     */
    @ApiModelProperty(value="奖惩时间")
    @ExcelProperty(value = "奖惩时间")
    private Date executeDate;




}
