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

package com.hedao.hdp.mpclient.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 *
 * @author liang
 * @date 2020-09-09 15:31:20
 */
@Data
@TableName("mp_organization")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class Organization extends Model<Organization> {
private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 组织OID
     */
    @ApiModelProperty(value="组织OID")
    private String oid;
    /**
     * 组织名称
     */
    @ApiModelProperty(value="组织名称")
    private String orgName;
    /**
     * 组织全名
     */
    @ApiModelProperty(value="组织全名")
    private String orgFullname;
    /**
     * 组织编码
     */
    @ApiModelProperty(value="组织编码")
    private String orgCode;
    /**
     * 上级OID
     */
    @ApiModelProperty(value="上级OID")
    private String parentOid;
    /**
     * 父ID
     */
    @ApiModelProperty(value="父ID")
    private Long parentId;
    /**
     * 组织级别
     */
    @ApiModelProperty(value="组织级别")
    private String orgLevel;
    /**
     * 是否组织
     */
    @ApiModelProperty(value="是否组织")
    private Boolean organizational;
    /**
     * 管线编码
     */
    @ApiModelProperty(value="管线编码")
    private String pipelineCode;
    /**
     * 福利类型
     */
    @ApiModelProperty(value="福利类型")
    private String welfareType;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private String sortNo;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Integer isStop;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 是否虚拟组织
     */
    @ApiModelProperty(value="是否虚拟组织")
    private Boolean isVirtual;
    /**
     * 组织类型
     */
    @ApiModelProperty(value="组织类型")
    private String orgType;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 组织描述
     */
    @ApiModelProperty(value="组织描述")
    private String orgDesc;
    /**
     * 组织负责人姓名
     */
    @ApiModelProperty(value="组织负责人姓名")
    private String orgChargeName;
    /**
     * 组织负责人工号
     */
    @ApiModelProperty(value="组织负责人工号")
    private String orgChargeWorkNo;
    /**
     * 组织简称
     */
    @ApiModelProperty(value="组织简称")
    private String orgSimpleDesc;
    /**
     * 部门负责人岗位（负责岗位）
     */
    @ApiModelProperty(value="部门负责人岗位（负责岗位）")
    private String chargeOrg;
    /**
     * 人员编制
     */
    @ApiModelProperty(value="人员编制")
    private String staff;
    /**
     * 在职员工数
     */
    @ApiModelProperty(value="在职员工数")
    private String empInService;

    /**
     * 父组织架构集合
     */
    @ApiModelProperty(value="父组织架构集合")
    @TableField(exist = false)
    private List<Organization> parent;

    /**
     * 子组织架构集合
     */
    @ApiModelProperty(value="子组织架构集合")
    @TableField(exist = false)
    private List<Organization> children;

    @ApiModelProperty(value="判断是否加载根组织架构 传参")
    @TableField(exist = false)
    private Boolean isRoot;

    @ApiModelProperty(value="子组织架构集合 点击触发")
    @TableField(exist = false)
    private List<Organization> childrenClick;


}



