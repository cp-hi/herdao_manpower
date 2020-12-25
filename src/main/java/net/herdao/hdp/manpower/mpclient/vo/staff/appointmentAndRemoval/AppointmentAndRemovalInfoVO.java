/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:37 下午
 */
@Data
public class AppointmentAndRemovalInfoVO {

    @ApiModelProperty(value = "兼职任免列表 id")
    private Long id;

    @ApiModelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value = "员工工号")
    private String staffCode;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private String staffScope;

    @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
    private String staffJobType;

    @ApiModelProperty(value = "主岗组织 id")
    private  Long orgId;

    @ApiModelProperty(value = "主岗组织名称")
    private String orgName;

    @ApiModelProperty(value = "主岗 id")
    private Long postOrgId;

    @ApiModelProperty(value = "主岗名称")
    private String postOrgName;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;
}
