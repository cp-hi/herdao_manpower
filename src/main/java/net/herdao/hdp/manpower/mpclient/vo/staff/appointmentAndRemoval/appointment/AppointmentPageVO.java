/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 兼职信息列表字段
 *
 * @Author Liu Chang
 * @Date 2020/12/22 5:29 下午
 */
@Data
public class AppointmentPageVO {

    @ApiModelProperty(value = "兼职记录 id")
    private Long id;

    @ApiModelProperty(value = "任命部门")
    private String orgName;

    @ApiModelProperty(value = "任命岗位")
    private String postOrgName;

    @ApiModelProperty(value = "任命生效日期")
    private Long startDate;
}
