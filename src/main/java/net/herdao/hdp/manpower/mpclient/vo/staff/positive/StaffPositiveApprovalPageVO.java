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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
//@TableName("mp_staff_positive_approval")
@ApiModel(value = "转正审批分页列表")
public class StaffPositiveApprovalPageVO {
    private static final long serialVersionUID = 1L;


    /**
     * 转正申请id
     */
    @ApiModelProperty(value = "转正申请id")
    @ExcelIgnore
    private Long staffpositiveapplicationId;
    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    @ExcelProperty(value = "入职日期")
    private Long entryTime;
    /**
     * 转正时间
     */
    @ApiModelProperty(value = "转正时间")
    @ExcelProperty(value = "转正时间")
    private Long positiveTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @ExcelProperty(value = "状态")
    private String status;


    /**
     * 新建时间
     */
    @ApiModelProperty(value = "新建时间")
    @ExcelProperty(value = "新建时间")
    private Long creatorTime;


    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    @ExcelIgnore
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    @ExcelProperty(value = "员工编码")
    private String staffCode;

    @ApiModelProperty(value = "所属组织")
    @ExcelProperty(value = "所属组织")
    private String nowOrgName;

    @ApiModelProperty(value = "岗位")
    @ExcelProperty(value = "岗位")
    private String nowPostName;

    @ApiModelProperty(value = "职级")
    @ExcelProperty(value = "职级")
    private String nowJobLevelName;

    @ApiModelProperty(value = "任职类型")
    @ExcelProperty(value = "任职类型")
    private String jobType;


    @ApiModelProperty(value = "司龄")
    @ExcelProperty(value = "司龄")
    private Double companySeniority;


    /**
     * 转正类型
     */
    @ApiModelProperty(value = "转正类型")
    @ExcelProperty(value = "转正类型")
    private String positiveType;

    @ApiModelProperty(value = "更新信息", name = "updateInfo", example = "由李四于2020-01-02更新")
    @ExcelProperty(value = "最近更新情况")
    private String updateInfo;

    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    @ExcelProperty(value = "执行状态")
    private String executingStatus;

    /**
     * 试用期/月
     */
    @ApiModelProperty(value="试用期/月")
    @ExcelProperty(value = "试用期/月")
    private String probation;


}
