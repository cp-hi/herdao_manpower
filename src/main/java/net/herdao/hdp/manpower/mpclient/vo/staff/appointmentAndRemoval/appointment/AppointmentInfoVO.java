/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:34 下午
 */
@Data
public class AppointmentInfoVO {

    @ApiModelProperty(value = "兼职列表 id")
    private Long id;

    @ApiModelProperty(value = "兼职所在组织 id")
    private Long orgId;

    @ApiModelProperty(value = "兼职所在组织名称")
    private String orgName;

    @ApiModelProperty(value = "兼职岗位的 id")
    private Long postOrgId;

    @ApiModelProperty(value = "兼职岗位名称")
    private String potOrgName;

    @ApiModelProperty(value = "兼职岗位开始时间")
    private Long startDate;
}
