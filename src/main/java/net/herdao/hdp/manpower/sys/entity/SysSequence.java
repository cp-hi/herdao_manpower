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

package net.herdao.hdp.manpower.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 序列表
 *
 * @author yangrr
 * @date 2020-10-20 11:37:09
 */
@Data
@TableName("sys_sequence")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "序列表")
public class SysSequence extends Model<SysSequence> {
private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 序列名称
     */
    @ApiModelProperty(value="序列名称")
    private String seqName;
    /**
     * 序列标识
     */
    @ApiModelProperty(value="序列标识")
    private String seqCode;
    /**
     * 当前值
     */
    @ApiModelProperty(value="当前值")
    private Long currentVal;
    /**
     * 增长值
     */
    @ApiModelProperty(value="增长值")
    private Long incrementVal;

}
