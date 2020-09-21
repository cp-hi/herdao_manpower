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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author liang
 * @date 2020-09-18 19:46:22
 */
@Data
@TableName("mp_excel_operate_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class ExcelOperateRecord extends Model<ExcelOperateRecord> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 字段
     */
    @ApiModelProperty(value="字段")
    private String filed;
    /**
     * 0:导入，1:导出
     */
    @ApiModelProperty(value="0:导入，1:导出")
    private String type;
    /**
     * 错误描述
     */
    @ApiModelProperty(value="错误描述")
    private String error;
    /**
     * 操作批次（随机生成)
     */
    @ApiModelProperty(value="操作批次（随机生成)")
    private String operateId;
    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private LocalDateTime createTime;
    /**
     * 操作用戶id
     */
    @ApiModelProperty(value="操作用戶id")
    private String operateUserId;
    }
