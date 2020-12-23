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
public class RemovalInfoVO {

    @ApiModelProperty("任免 id")
    private Long id;

    @ApiModelProperty("任免所在职位的 id")
    private Long userpostId;

    @ApiModelProperty("即将任免岗位组织 id")
    private Long orgId;

    @ApiModelProperty("即将任免岗位组织名称")
    private String orgName;

    @ApiModelProperty("即将任免岗位id")
    private Long postOrgId;

    @ApiModelProperty("即将任免岗位名称")
    private String postOrgName;

    @ApiModelProperty("即将任免岗位的到期时间")
    private Long endDate;
}
