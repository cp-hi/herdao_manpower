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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工职称及职业资料
 *
 * @author andy
 * @date 2020-10-09 10:53:38
 */
@Data
@TableName("mp_staff_profession")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工职称及职业资料")
public class StaffProfession extends BaseModel<StaffProfession> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
    private String proName;
    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String proCase;
    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String evaluateUnit;
    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String proNo;
    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    private Date certificateTime;
    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String depandUnit;
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
    private String staffId;

    /**
     * 租户ID
     */
    @ExcelIgnore
    @ApiModelProperty(value="租户ID")
    @ExcelProperty("tenantId")
    private Long tenantId;

}
