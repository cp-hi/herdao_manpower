/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/23 2:45 下午
 */
@Data
@TableName("mp_staff_appointment_removal")
@EqualsAndHashCode(callSuper = true)
public class StaffAppointmentAndRemoval extends BaseEntity<StaffAppointmentAndRemoval> {

    @ApiModelProperty(value = "兼职任免id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "任命部门id")
    private Long orgId;

    @ApiModelProperty(value = "任命岗位id(业务岗)")
    private Long postId;

    @ApiModelProperty(value = "新建人工号")
    private String creatorCode;

    @ApiModelProperty(value = "新建人")
    private String creatorName;

    @ApiModelProperty(value = "新建时间")
    private LocalDateTime creatorTime;

    @ApiModelProperty(value = "修改人工号")
    private String modifierCode;

    @ApiModelProperty(value = "修改人")
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "经办人意见")
    private String remark;
}
