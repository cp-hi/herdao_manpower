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

package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才职称资格DTO
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
@Data
@ApiModel(value = "人才职称资格DTO")
public class RecruitmentTitleDTO {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;

    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
    private String title;

    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String qualification;

    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String awardsUnite;

    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String certificateNo;

    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date certificateTime;

    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualificationUnit;

}
