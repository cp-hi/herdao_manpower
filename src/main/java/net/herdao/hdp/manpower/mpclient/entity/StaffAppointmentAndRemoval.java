/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/23 2:45 下午
 */
@Data
@TableName("mp_staff_appointment_removal")
public class StaffAppointmentAndRemoval {

    @ApiModelProperty(value = "兼职任免id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "主岗部门id")
    private Long orgId;

    @ApiModelProperty(value = "主岗位id(业务岗)")
    private Long postId;

    @ApiModelProperty(value = "新建人工号")
    @TableField(value = "creator_code", fill = FieldFill.INSERT)
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    @TableField(value = "creator_name", fill = FieldFill.INSERT)
    private String creatorName;

    @ApiModelProperty(value = "新建时间")
    @TableField(value = "creator_time", fill = FieldFill.INSERT)
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    @TableField(value = "modifier_code", fill = FieldFill.UPDATE)
    private String modifierCode;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "modifier_name", fill = FieldFill.UPDATE)
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    @TableField(value = "modifier_time", fill = FieldFill.UPDATE)
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;

    @ApiModelProperty(value = "租户 id")
    private Long tenantId;

    @TableLogic
    private Boolean delFlag;

    private Long postOrgId;
}
