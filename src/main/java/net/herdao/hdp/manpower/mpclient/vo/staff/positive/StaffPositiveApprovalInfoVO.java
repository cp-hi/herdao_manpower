/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */

package net.herdao.hdp.manpower.mpclient.vo.staff.positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
//@TableName("mp_staff_positive_approval")
@ApiModel(value = "转正审批详情")
public class StaffPositiveApprovalInfoVO {
    private static final long serialVersionUID = 1L;


    /**
     * 转正申请id
     */
    @ApiModelProperty(value = "转正申请id")
    private Long staffpositiveapplicationId;
    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private Long entryTime;
    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间")
    private Long positiveTime;

    /**
     * 新建时间
     */
    @ApiModelProperty(value = "新建时间")
    private Long creatorTime;


    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "所属组织")
    private String nowOrgName;

    @ApiModelProperty(value = "岗位")
    private String nowPostName;

    /**
     * 转正类型
     */
    @ApiModelProperty(value = "转正类型")
    private String positiveType;


    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;

    /**
     * 转正问题1回答
     */
    @ApiModelProperty(value="转正问题1回答")
    private String positiveQuestionAns1;
    /**
     * 转正问题2回答
     */
    @ApiModelProperty(value="转正问题2回答")
    private String positiveQuestionAns2;
    /**
     * 转正问题3回答
     */
    @ApiModelProperty(value="转正问题3回答")
    private String positiveQuestionAns3;
    /**
     * 转正问题4回答
     */
    @ApiModelProperty(value="转正问题4回答")
    private String positiveQuestionAns4;
    /**
     * 转正问题5回答
     */
    @ApiModelProperty(value="转正问题5回答")
    private String positiveQuestionAns5;
    /**
     * 转正问题6回答
     */
    @ApiModelProperty(value="转正问题6回答")
    private String positiveQuestionAns6;
    /**
     * 转正问题7回答
     */
    @ApiModelProperty(value="转正问题7回答")
    private String positiveQuestionAns7;

    /**
     * 所在组织
     */
    @ApiModelProperty(value = "所在组织")
    private String orgName;
    /**
     * 试任岗位
     */
    @ApiModelProperty(value = "试任岗位")
    private String tryAnyJob;

    /**
     * 试任日期
     */
    @ApiModelProperty(value = "试任日期")
    private String entryTime1;
}
