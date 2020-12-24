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

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/23 2:46 下午
 */
@Data
@TableName("mp_staff_appointment_removal_item")
@EqualsAndHashCode(callSuper = true)
public class StaffAppointmentAndRemovalItem extends BaseEntity<StaffAppointmentAndRemovalItem> {

    @ApiModelProperty(value = "兼职任免明细id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "兼职任免id")
    private Long staffAppointmentRemovalId;

    @ApiModelProperty(value = "任命部门id")
    private Long orgId;

    @ApiModelProperty(value = "任命岗位id")
    private Long postId;

    /**
     * 该表中的该字段默认都是 0 -- 兼岗（非主岗）
     */
    @ApiModelProperty(value = "是否主岗")
    private Integer mainPost;

    /**
     * 该字段当做 remark，当前版本（v1.2.0）该字段暂无意义
     */
    @ApiModelProperty(value = "实际职务")
    private String ofactual;

    @ApiModelProperty(value = "兼岗开始时间，任免时该字段从 userpost_id 中拿过来填写")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "兼岗不填，任免时该字段保存页面针对 userpor_id 职位的传值 ")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "兼职任免类型，兼职-0，任免-1")
    private Integer appointType;

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

    /**
     * 关联到 mp_userpost 表 id
     */
    @ApiModelProperty(value = "用户任职表")
    private Long userpostId;

    /**
     * 关联到 mp_post_org 表 id
     */
    @ApiModelProperty(value = "组织岗位关系 id(业务岗)")
    private Long postOrgId;
}
