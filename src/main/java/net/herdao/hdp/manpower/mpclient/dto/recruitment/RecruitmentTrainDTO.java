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
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 人才培训经历DTO
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
@Data
@ApiModel(value = "人才培训经历DTO")
public class RecruitmentTrainDTO {
    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 开始培训时间
     */
    @ApiModelProperty(value="开始培训时间")
    private Date beginDate;
    /**
     * 结束培训时间
     */
    @ApiModelProperty(value="结束培训时间")
    private Date endDate;
    /**
     * 培训内容
     */
    @ApiModelProperty(value="培训内容")
    private String content;
    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    private String certificateName;
    /**
     * 证书编号
     */
    @ApiModelProperty(value="证书编号")
    private String certificateNo;

    /**
     * 组织者
     */
    @ApiModelProperty(value="组织者")
    private String organizer;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;

}
