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
 * @Date 2020/12/23 2:34 下午
 */
@Data
public class SaveAppointAndRemovalDTO {

    @ApiModelProperty(value = "主岗组织 id", name = "orgId")
    private Long orgId;

    @ApiModelProperty(value = "主岗业务岗 id", name = "postOrgId")
    private Long postOrgId;

    @ApiModelProperty(value = "主岗职级 id", name = "postOrgId")
    private Long jobLevelId;

    @ApiModelProperty(value = "主岗标准岗 id", name = "postId")
    private Long postId;

    @ApiModelProperty(value = "用户id", name = "userId")
    private Long userId;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;
}
