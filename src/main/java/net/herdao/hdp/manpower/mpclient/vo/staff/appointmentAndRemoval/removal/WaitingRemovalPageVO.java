/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 待免职列表字段
 *
 * @Author Liu Chang
 * @Date 2020/12/22 5:04 下午
 */
@Data
public class WaitingRemovalPageVO {

    @ApiModelProperty("即将任免的用户任职岗位 id")
    private Long userpostId;

    @ApiModelProperty("即将任免岗位的组织 id")
    private Long orgId;

    @ApiModelProperty("即将任免岗位的组织名称")
    private String orgName;

    @ApiModelProperty("即将任免岗位的 id(业务岗)")
    private Long postOrgId;

    @ApiModelProperty("即将任免岗位名称(业务岗)")
    private String postOrgName;
}
