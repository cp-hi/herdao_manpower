/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:35 下午
 */

@Data
public class RemovalPageVO {

    @ApiModelProperty("任免 id")
    private Long id;

    @ApiModelProperty("任免岗位的组织名称")
    private String orgName;

    @ApiModelProperty("任免岗位的岗位名称")
    private String postOrgName;

    @ApiModelProperty("任免岗位的生效日期")
    private Long endDate;
}
