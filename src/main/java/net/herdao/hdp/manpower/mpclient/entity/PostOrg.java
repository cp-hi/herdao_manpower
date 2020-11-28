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

import java.time.LocalDateTime;

/**
 * 岗位组织关系表
 *
 * @author hsh
 * @date 2020-11-25 11:59:30
 */
@Data
@TableName("mp_post_org")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位组织关系表")
public class PostOrg extends Model<PostOrg> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 岗位编码
     */
    @ApiModelProperty(value="岗位编码")
    private String postCode;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String postName;
    /**
     * 集团d
     */
    @ApiModelProperty(value="集团id")
    private Long groupId;
    /**
     * 标准岗位id
     */
    @ApiModelProperty(value="标准岗位id")
    private Long postId;
    /**
     * 所属组织id
     */
    @ApiModelProperty(value="所属组织id")
    private Long orgId;
    /**
     * 是否一把手0不是1是
     */
    @ApiModelProperty(value="是否一把手0不是1是")
    private String isLeader;
    /**
     * 是否干部岗0不是1是
     */
    @ApiModelProperty(value="是否干部岗0不是1是")
    private String isCadre;
    /**
     * 板块id
     */
    @ApiModelProperty(value="板块id")
    private Long sectionId;
    /**
     * 管线id
     */
    @ApiModelProperty(value="管线id")
    private Long pipelineId;
    /**
     * 职级id
     */
    @ApiModelProperty(value="职级id")
    private Long jobLevelId;
    /**
     * 岗位编制
     */
    @ApiModelProperty(value="岗位编制")
    private Integer jobStaffs;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Long sortNo;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Integer isStop;
    /**
     * 新建用户ID
     */
    @ApiModelProperty(value="新建用户工号")
    private String creatorCode;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改用户ID
     */
    @ApiModelProperty(value="修改用户工号")
    private String modifierCode;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierName;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifierTime;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field1;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field2;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field3;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field4;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field5;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Boolean delFlag;
    /**
     * 停用日期
     */
    @ApiModelProperty(value="停用日期")
    private LocalDateTime stopDate;
    /**
     * 启用日期
     */
    @ApiModelProperty(value="启用日期")
    private LocalDateTime startDate;
    }
