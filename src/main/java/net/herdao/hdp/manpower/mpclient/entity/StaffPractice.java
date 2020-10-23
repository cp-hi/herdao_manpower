/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工实习记录
 *
 * @author andy
 * @date 2020-10-09 17:51:16
 */
@Data
@TableName("mp_staff_practice")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工实习记录")
public class StaffPractice extends BaseModel<StaffPractice> {
private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 开始日期 yyyy-MM-dd
     */
    @ApiModelProperty(value="开始日期 yyyy-MM-dd ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beginDate;
    /**
     * 结束日期 yyyy-MM-dd
     */
    @ApiModelProperty(value="结束日期 yyyy-MM-dd ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date endDate;
    /**
     * 实习期 以月为单位，开始日期和结束日期之差，取整
     */
    @ApiModelProperty(value="实习期 以月为单位，开始日期和结束日期之差，取整")
    private String period;
    /**
     * 集团
     */
    @ApiModelProperty(value="集团")
    private String groupName;
    /**
     * 集团ID
     */
    @ApiModelProperty(value="集团ID")
    private Long groupId;
    /**
     * 公司
     */
    @ApiModelProperty(value="公司")
    private String corporationName;
    /**
     * 公司ID
     */
    @ApiModelProperty(value="公司ID")
    private Long corporationId;
    /**
     * 部门
     */
    @ApiModelProperty(value="部门")
    private String departName;
    /**
     * 部门ID
     */
    @ApiModelProperty(value="部门ID")
    private Long departId;
    /**
     * 板块ID
     */
    @ApiModelProperty(value="板块ID")
    private Long plateId;
    /**
     * 管线ID
     */
    @ApiModelProperty(value="管线ID")
    private Long pipeId;
    /**
     * 岗位ID
     */
    @ApiModelProperty(value="岗位ID")
    private Long postId;
    /**
     * 职级
     */
    @ApiModelProperty(value="职级")
    private String jobLevel;
    /**
     * 实习成绩

     */
    @ApiModelProperty(value="实习成绩")
    private Double score;
    /**
     * 评价
    */
    @ApiModelProperty(value="评价")
    private String evaluate;
    /**
     * 评价人ID
    */
    @ApiModelProperty(value="评价人ID")
    private String evaluateId;
    /**
     * 评价人姓名
    */
    @ApiModelProperty(value="评价人姓名")
    private String evaluateName;
    /**
     * 评价时间
    */
    @ApiModelProperty(value="评价时间")
    private LocalDate evaluateTime;
    /**
     * 新建用户
    */
    @ApiModelProperty(value="新建用户")
    private String creatorId;
    /**
     * 新建时间
    */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
    */
    @ApiModelProperty(value="最后修改人")
    private String modifierId;
    /**
     * 最后修改时间
    */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 人员外键
    */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
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
     * 是否删除
    */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

}
