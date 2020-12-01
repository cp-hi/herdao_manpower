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
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@Data
@TableName("mp_staff_entrypost_approve")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "录用审批表")
public class StaffEntrypostApprove extends Model<StaffEntrypostApprove> {

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
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String username;
    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;
    /**
     * 人才oid
     */
    @ApiModelProperty(value="人才oid")
    private String recruitmentOid;
    /**
     * 入职类型
     */
    @ApiModelProperty(value="入职类型")
    private String entryType;
    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private String status;
    /**
     * 人员性质
     */
    @ApiModelProperty(value="人员性质")
    private String personnelNature;
    /**
     * 人员类别
     */
    @ApiModelProperty(value="人员类别")
    private String personnelType;
    /**
     * 人员归属范围
     */
    @ApiModelProperty(value="人员归属范围")
    private String staffStatus;
    /**
     * 任职类型
     */
    @ApiModelProperty(value="任职类型")
    private String officeType;
    /**
     * 入职部门id
     */
    @ApiModelProperty(value="入职部门id")
    private Long orgId;
    /**
     * 入职部门oid
     */
    @ApiModelProperty(value="入职部门oid")
    private String orgOid;
    /**
     * 入职岗位id
     */
    @ApiModelProperty(value="入职岗位id")
    private Long postId;
    /**
     * 入职岗位oid
     */
    @ApiModelProperty(value="入职岗位oid")
    private String postOid;
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
     * 入职时间
     */
    @ApiModelProperty(value="入职时间")
    private LocalDateTime entryPostTime;
    /**
     * 合同期限（年）
     */
    @ApiModelProperty(value="合同期限（年）")
    private BigDecimal contractPeriod;
    /**
     * 试用期（月）
     */
    @ApiModelProperty(value="试用期（月）")
    private BigDecimal probation;
    /**
     * 劳动合同签订主体id
     */
    @ApiModelProperty(value="劳动合同签订主体id")
    private Long contractCompanyId;
    /**
     * 劳动合同签订主体oid
     */
    @ApiModelProperty(value="劳动合同签订主体oid")
    private String contractCompanyOid;
    /**
     * 其它补贴
     */
    @ApiModelProperty(value="其它补贴")
    private BigDecimal otherSubsides;
    /**
     * 经办人意见
     */
    @ApiModelProperty(value="经办人意见")
    private String remark;
    /**
     * 员工信息id
     */
    @ApiModelProperty(value="员工信息id")
    private Long staffId;
    /**
     * 员工信息oid
     */
    @ApiModelProperty(value="员工信息oid")
    private String staffOid;
    /**
     * 入职状态
     */
    @ApiModelProperty(value="入职状态")
    private String entryStatus;
    /**
     * 工号
     */
    @ApiModelProperty(value="工号")
    private String staffCode;
    /**
     * 离职补充备注
     */
    @ApiModelProperty(value="离职补充备注")
    private String leaveSupplyRemark;
    /**
     * 原占编员工id
     */
    @ApiModelProperty(value="原占编员工id")
    private Long replaceUserId;
    /**
     * 原占编员工oid
     */
    @ApiModelProperty(value="原占编员工oid")
    private String replaceUserOid;
    /**
     * 原占编部门id
     */
    @ApiModelProperty(value="原占编部门id")
    private Long replaceOrganizationId;
    /**
     * 原占编部门oid
     */
    @ApiModelProperty(value="原占编部门oid")
    private String replaceOrganizationOid;
    /**
     * 原占编岗位id
     */
    @ApiModelProperty(value="原占编岗位id")
    private Long replacePostId;
    /**
     * 原占编岗位oid
     */
    @ApiModelProperty(value="原占编岗位oid")
    private String replacePostOid;
    /**
     * 是否任命
     */
    @ApiModelProperty(value="是否任命")
    private Integer isAppointment;
    /**
     * 工资发放单位id
     */
    @ApiModelProperty(value="工资发放单位id")
    private Long paidUnitsId;
    /**
     * 工资发放单位oid
     */
    @ApiModelProperty(value="工资发放单位oid")
    private String paidUnitsOid;
    /**
     * 社保缴纳单位id
     */
    @ApiModelProperty(value="社保缴纳单位id")
    private Long securityUnitsId;
    /**
     * 社保缴纳单位oid
     */
    @ApiModelProperty(value="社保缴纳单位oid")
    private String securityUnitsOid;
    /**
     * 公积金缴纳单位id
     */
    @ApiModelProperty(value="公积金缴纳单位id")
    private Long fundUnitsId;
    /**
     * 公积金缴纳单位oid
     */
    @ApiModelProperty(value="公积金缴纳单位oid")
    private String fundUnitsOid;
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
     * 入职登记状态
     */
    @ApiModelProperty(value="入职登记状态")
    private String entryLoginStatus;
    /**
     * 邀请状态
     */
    @ApiModelProperty(value="邀请状态")
    private String inviteStatus;
    /**
     * 职级
     */
    @ApiModelProperty(value="职级")
    private Long jobLevelId;
    /**
     * 入职登记进度
     */
    @ApiModelProperty(value="入职登记进度")
    private String entryLoginProgress;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    @TableLogic
    private Boolean delFlag;
    /**
     * 岗位组织关系id
     */
    @ApiModelProperty(value="岗位组织关系id")
    private Long postOrgId;
    /**
     * 移动号码
     */
    @ApiModelProperty(value="移动号码")
    private String mobileNo;
    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNo;
    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;
 }
