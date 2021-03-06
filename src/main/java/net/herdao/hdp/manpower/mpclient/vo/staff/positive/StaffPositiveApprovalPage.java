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

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
//@TableName("mp_staff_positive_approval")
@ApiModel(value = "转正审批分页列表")
public class StaffPositiveApprovalPage   {
    private static final long serialVersionUID = 1L;





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
     * 状态
     */
    @ApiModelProperty(value="状态")
    private String status;




    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;





    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "所属组织")
    private String nowOrgName;

    @ApiModelProperty(value = "岗位")
    private String nowPostName;

    @ApiModelProperty(value = "职级")
    private String nowJobLevelName;

    @ApiModelProperty(value = "任职类型")
    private String jobType;


    @ApiModelProperty(value = "司龄")
    private Double companySeniority;


    /**
     * 修改人时间
     */
    @ApiModelProperty(value="修改人时间")
    private LocalDateTime modifierTime;


    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;


    /**
     * 转正类型
     */
    @ApiModelProperty(value = "转正类型")
    private String positiveType;


    /**
     * 试用期/月
     */
    @ApiModelProperty(value="试用期/月")
    private String probation;
}
