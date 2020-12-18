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

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
@TableName("mp_staff_positive_approval")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "转正审批表")
public class StaffPositiveApproval extends Model<StaffPositiveApproval> {
private static final long serialVersionUID = 1L;

    /**
     * 录用审批id
     */
    @TableId
    @ApiModelProperty(value="录用审批id")
    private Long id;
    /**
     * 旧人力OID
     */
    @ApiModelProperty(value="旧人力OID")
    private String oid;
    /**
     * 员工id
     */
    @ApiModelProperty(value="员工id")
    private Long userId;
    /**
     * 员工oid
     */
    @ApiModelProperty(value="员工oid")
    private String userOid;
    /**
     * 部门id
     */
    @ApiModelProperty(value="部门id")
    private Long orgId;
    /**
     * 部门oid
     */
    @ApiModelProperty(value="部门oid")
    private String orgOid;
    /**
     * 岗位id
     */
    @ApiModelProperty(value="岗位id")
    private Long postId;
    /**
     * 岗位oid
     */
    @ApiModelProperty(value="岗位oid")
    private String postOid;
    /**
     * 转正申请oid
     */
    @ApiModelProperty(value="转正申请oid")
    private String staffpositiveapplicationOid;
    /**
     * 转正申请id
     */
    @ApiModelProperty(value="转正申请id")
    private Long staffpositiveapplicationId;
    /**
     * 入职日期
     */
    @ApiModelProperty(value="入职日期")
    private LocalDateTime entryTime;
    /**
     * 转正时间
     */
    @ApiModelProperty(value="转正时间")
    private LocalDateTime positiveTime;
    /**
     * 转正前月度总收入
     */
    @ApiModelProperty(value="转正前月度总收入")
    private BigDecimal previousMonthIncome;
    /**
     * 其他
     */
    @ApiModelProperty(value="其他")
    private BigDecimal otherCosts;
    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String posExamScore;
    /**
     * 年度岗位编制
     */
    @ApiModelProperty(value="年度岗位编制")
    private Integer yearPostPrepareCount;
    /**
     * 月度岗位编制
     */
    @ApiModelProperty(value="月度岗位编制")
    private Integer monthPostPrepareCount;
    /**
     * 已到岗人数
     */
    @ApiModelProperty(value="已到岗人数")
    private Integer postHasCount;
    /**
     * 是否编制置换
     */
    @ApiModelProperty(value="是否编制置换")
    private Integer isPrepareChange;
    /**
     * 转正前岗位id
     */
    @ApiModelProperty(value="转正前岗位id")
    private Long beforePostId;
    /**
     * 转正前岗位oid
     */
    @ApiModelProperty(value="转正前岗位oid")
    private String beforePostOid;
    /**
     * 现年度岗位编制
     */
    @ApiModelProperty(value="现年度岗位编制")
    private Integer nowYearPostPrepareCount;
    /**
     * 现月度岗位编制
     */
    @ApiModelProperty(value="现月度岗位编制")
    private Integer nowMonthPostPrepareCount;
    /**
     * 现已到岗人数
     */
    @ApiModelProperty(value="现已到岗人数")
    private Integer nowPostHasCount;
    /**
     * 面谈时间
     */
    @ApiModelProperty(value="面谈时间")
    private LocalDateTime interviewsTime;
    /**
     * 面谈地点
     */
    @ApiModelProperty(value="面谈地点")
    private String interviewsPlace;
    /**
     * 入职引导人
     */
    @ApiModelProperty(value="入职引导人")
    private String entryLeader;
    /**
     * 面谈主持人
     */
    @ApiModelProperty(value="面谈主持人")
    private String interviewsHost;
    /**
     * 面谈参与人
     */
    @ApiModelProperty(value="面谈参与人")
    private String interviewsParticipants;
    /**
     * 记录人
     */
    @ApiModelProperty(value="记录人")
    private String recordMan;
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
     * 改善建议
     */
    @ApiModelProperty(value="改善建议")
    private String improveProposal;
    /**
     * 面谈总结
     */
    @ApiModelProperty(value="面谈总结")
    private String interviewSummary;
    /**
     * 新建人工号
     */
    @ApiModelProperty(value="新建人工号")
    private String creatorCode;
    /**
     * 新建人
     */
    @ApiModelProperty(value="新建人")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改人工号
     */
    @ApiModelProperty(value="修改人工号")
    private String modifierCode;
    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;
    /**
     * 修改人时间
     */
    @ApiModelProperty(value="修改人时间")
    private LocalDateTime modifierTime;
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    private Boolean delFlag;
    /**
     * 岗位组织关系id
     */
    @ApiModelProperty(value="岗位组织关系id")
    private Long postOrgId;
    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;
    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNo;
    /**
     * 试用期/月
     */
    @ApiModelProperty(value="试用期/月")
    private String probation;

    /**
     * 是否通知
     */
    @ApiModelProperty(value = "是否通知(0:通知,1:不通知)")
    private Long whetherOrNotNoticeOf;

}
