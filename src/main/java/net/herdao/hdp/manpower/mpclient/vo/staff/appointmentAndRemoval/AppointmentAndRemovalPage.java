/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:36 下午
 */
@Data
public class AppointmentAndRemovalPage {

    @ApiModelProperty("兼职任免 id")
    private Long id;

    @ApiModelProperty(value = "员工 id")
    private Long staffId;

    @ApiModelProperty("员工姓名")
    private String staffName;

    @ApiModelProperty("员工工号")
    private String staffCode;

    @ApiModelProperty(value = "主岗组织名称")
    private String orgName;

    @ApiModelProperty(value = "主岗岗位名称")
    private String postOrgName;

    @ApiModelProperty(value = "主岗职级名称")
    private String jobLevelName;

    /**
     *  该字段有兼职列表转换为字符串
     */
    @ApiModelProperty(value = "兼职岗位列表字符串")
    private List<AppointmentPageVO> appointmentPostOrgNames;

    @ApiModelProperty(value = "任免岗位列表字符串")
    private List<RemovalPageVO> removalPostOrgNames;

    @ApiModelProperty(value = "更新人名字")
    private String modifierName;

    @ApiModelProperty(value = "更新时间")
    private String modifierTime;
}
