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

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@Data
@TableName("mp_userposthistory")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工岗位历史表")
public class Userposthistory extends BaseEntity<Userposthistory> {
private static final long serialVersionUID = 1L;

    /**
     * 用户外键
     */
    @ApiModelProperty(value="用户外键")
    private Long userId;
    /**
     * 岗位外键
     */
    @ApiModelProperty(value="岗位外键")
    private Long postId;
    /**
     * 所属组织外键
     */
    @ApiModelProperty(value="所属组织外键")
    private Long orgId;
    /**
     * 所属部门外键
     */
    @ApiModelProperty(value="所属部门外键")
    private Long orgDeptId;
    /**
     * 是否主岗位
     */
    @ApiModelProperty(value="是否主岗位")
    private Boolean mainPost;
    /**
     * 实际职务
     */
    @ApiModelProperty(value="实际职务")
    private String actualJob;
    /**
     * 任职日期
     */
    @ApiModelProperty(value="任职日期")
    private LocalDateTime startDate;
    /**
     * 免职日期
     */
    @ApiModelProperty(value="免职日期")
    private LocalDateTime endDate;
    /**
     * 是否虚拟
     */
    @ApiModelProperty(value="是否虚拟")
    private Boolean isVirtual;
    /**
     * 用户岗位外键
     */
    @ApiModelProperty(value="用户岗位外键")
    private Long userPostId;
    /**
     * 代码表行政级别
     */
    @ApiModelProperty(value="代码表行政级别")
    private String administrativeLevel;

    /**
     * 是否定制岗位
     */
    @ApiModelProperty(value="是否定制岗位")
    private String officePostType;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;

}
