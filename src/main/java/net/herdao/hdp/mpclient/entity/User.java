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

package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author andy
 * @date 2020-09-15 17:59:33
 */
@Data
@TableName("mp_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class User extends Model<User> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private String oid;
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;
    /**
     * 登录帐号
     */
    @ApiModelProperty(value="登录帐号")
    private String loginCode;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;
    /**
     * 密码2
     */
    @ApiModelProperty(value="密码2")
    private String password2;
    /**
     * 所属组织外键
     */
    @ApiModelProperty(value="所属组织外键")
    private Long orgOid;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Long orgId;
    /**
     * 所属部门外键
     */
    @ApiModelProperty(value="所属部门外键")
    private String orgDeptOid;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Long orgDeptId;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Long isStop;
    /**
     * 最后登陆时间
     */
    @ApiModelProperty(value="最后登陆时间")
    private LocalDateTime lastLoginTime;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private LocalDateTime leaveTime;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    }
