package net.herdao.hdp.manpower.mpclient.dto.staffRp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "员工奖惩列表DTO")
@ColumnWidth(20)
public class StaffRpDTO  {
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
    @ApiModelProperty(value="奖励/惩罚 1:奖励 2:惩罚")
    private String choice;

    /**
     * 奖惩时间
     */
    @ExcelProperty(value="奖惩时间", index =3)
    private String executeDate;

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
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    @ExcelIgnore
    private String updateDesc;
    /**
     * 集团id
     */
    @ApiModelProperty(value="集团id")
    @ExcelProperty(value = "集团id")
    private String groupId;
    /**
     * 集团名
     */
    @ApiModelProperty(value="集团名")
    @ExcelProperty(value = "集团名")
    private String groupName;
    /**
     * 所在组织id
     */
    @ApiModelProperty(value = "所在组织id")
    private Long orgId;
}
