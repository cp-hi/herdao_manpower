package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Data
@TableName("mp_staff_rewards_pulishments")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工奖惩")
public class StaffRewardsPulishments extends BaseModel<StaffRewardsPulishments> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    @ExcelIgnore
    private Long id;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ApiModelProperty(value="奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择")
    @ExcelIgnore
    private String type;

    /**
     * 奖惩内容
     */
    @ApiModelProperty(value="奖惩内容")
    @ExcelIgnore
    private String content;

    /**
     * 奖惩金额
     */
    @ApiModelProperty(value="奖惩金额")
    @ExcelIgnore
    private String amount;

    /**
     * 奖惩原因
     */
    @ApiModelProperty(value="奖惩原因")
    @ExcelIgnore
    private String reason;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    @ExcelIgnore
    private String remarks;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    @ExcelIgnore
    private String creatorCode;

    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    @ExcelIgnore
    private LocalDateTime createdTime;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    @ExcelIgnore
    private String modifierCode;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    @ExcelIgnore
    private LocalDateTime modifiedTime;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    @ExcelIgnore
    private Long tenantId;

    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    @ExcelIgnore
    private String field1;

    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    @ExcelIgnore
    private String field2;

    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    @ExcelIgnore
    private String field3;

    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    @ExcelIgnore
    private String field4;

    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    @ExcelIgnore
    private String field5;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    @ExcelIgnore
    private Boolean delFlag;

    /**
     * 奖励/惩罚 0:奖励 1:惩罚
     */
    @ApiModelProperty(value="奖励/惩罚 1:奖励 2:惩罚")
    @ExcelIgnore
    private Long choice;

    /**
     * 奖惩时间
     */
    @ApiModelProperty(value="奖惩时间")
    @ExcelIgnore
    private Date executeDate;

    @ApiModelProperty(value="人员外键")
    @ExcelIgnore
    private Long staffId;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID" ,hidden = true)
    private Long creatorId;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称" ,hidden = true)
    private String creatorName;

    /**
     * 修改ID
     */
    @ApiModelProperty(value = "修改ID" ,hidden = true)
    private Long modifierId;

    /**
     * 修改人(操作人姓名)
     */
    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;


}
