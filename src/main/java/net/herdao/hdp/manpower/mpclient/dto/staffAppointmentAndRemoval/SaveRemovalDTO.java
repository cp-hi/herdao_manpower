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
 * @Date 2020/12/23 2:33 下午
 */
@Data
public class SaveRemovalDTO {

    @ApiModelProperty(value = "兼职任免列表中的 id", name = "staffAppointmentRemovalId")
    private Long staffAppointmentRemovalId;

    @ApiModelProperty(value = "即将任免岗位的组织 id")
    private Long orgId;

    @ApiModelProperty(value = "即将任免岗位 id")
    private Long postOrgId;

    @ApiModelProperty(value = "即将任免岗位 id")
    private Long userpostId;

    @ApiModelProperty(value = "即将任免岗位的生效日期")
    private Long endDate;
}
