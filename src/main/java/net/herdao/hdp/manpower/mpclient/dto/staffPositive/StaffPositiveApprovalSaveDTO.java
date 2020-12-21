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

package net.herdao.hdp.manpower.mpclient.dto.staffPositive;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
@TableName("mp_staff_positive_approval")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "转正审批保存DTO")
public class StaffPositiveApprovalSaveDTO extends Model<StaffPositiveApprovalSaveDTO> {
private static final long serialVersionUID = 1L;

    /**
     * 录用审批id
     */
    @TableId
    @ApiModelProperty(value="录用审批id")
    private Long id;

    /**
     * 员工id
     */
    @ApiModelProperty(value="员工id")
    private Long userId;


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
    private Long entryTime;
    /**
     * 转正时间
     */
    @ApiModelProperty(value="转正时间")
    private Long positiveTime;

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

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String staffName;


    @ApiModelProperty(value = "所属组织")
    private String nowOrgName;

    private Integer monthPostPrepareCount;





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
    private Long creatorTime;
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
    private Long modifierTime;

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
     * 转正类型
     */
    @ApiModelProperty(value = "转正类型")
    private String positiveType;


    /**
     * 试任岗位薪酬级别
     */
    @ApiModelProperty(value = "试任岗位薪酬级别")
    private String tryAnyJobPay;


    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;

//--------------------------------------------------------------->'
    /**
     * 所在组织
     */
    @ApiModelProperty(value = "所在组织")
    private String orgName;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private String entryDate;

    /**
     * 试任岗位
     */
    @ApiModelProperty(value = "试任岗位")
    private String tryAnyJob;

    /**
     * 试任日期
     */
    @ApiModelProperty(value = "试任日期")
    private String tryAnyDate;



    /**
     * 部门id
     */
    @ApiModelProperty(value="部门id")
    private Long nowOrgId;
}
