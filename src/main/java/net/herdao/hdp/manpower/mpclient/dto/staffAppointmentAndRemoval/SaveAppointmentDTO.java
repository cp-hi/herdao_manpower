/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:41 下午
 */
@Data
public class SaveAppointmentDTO {

    @ApiModelProperty(value = "兼职任免列表中的 id", name = "staffAppointmentRemovalId")
    private Long staffAppointmentRemovalId;

    @ApiModelProperty(value = "兼职的组织 id", name = "orgId")
    private Long orgId;

    @ApiModelProperty(value = "兼职的岗位 id(业务岗位)", name = "postOrgId")
    private Long postOrgId;

    @ApiModelProperty(value = "兼职岗位的开始时间")
    private Long startDate;
}
