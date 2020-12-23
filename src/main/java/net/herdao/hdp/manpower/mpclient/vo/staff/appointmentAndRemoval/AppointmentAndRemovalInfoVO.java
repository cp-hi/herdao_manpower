/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/22 5:37 下午
 */
@Data
public class AppointmentAndRemovalInfoVO {

    @ApiModelProperty(value = "兼职任免列表 id")
    private Long id;

    @ApiModelProperty(value = "员工 id")
    private Long staffId;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private String staffScope;

    @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
    private String staffJobType;

    @ApiModelProperty(value = "主岗组织 id", name = "orgName")
    private String orgName;

    @ApiModelProperty(value = "主岗 id", name = "postOrgName")
    private String postOrgName;

    @ApiModelProperty(value = "")
    private Page<AppointmentPageVO> appointmentPage;

    @ApiModelProperty(value = "")
    private Page<RemovalPageVO> removalPage;

    @ApiModelProperty(value = "")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;
}
