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

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileSituationDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentBaseInfo;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
@Mapper
public interface AttachFileMapper extends BaseMapper<AttachFile> {

    /**
     * 上传后绑定数据
     * @param attachFile 通用附件表
     */
    void insertBatch(List<AttachFile> attachFile);

    /**
     * 获取-个人简历-简历附件
     * @return AttachFileSituationDTO
     */
    List<AttachFileSituationDTO> fetchResumeAttachFileInfo();

    /**
     * 获取-入职登记详情-入职附件
     * @return List<AttachFileSituationDTO>
     */
    List<AttachFileSituationDTO> fetchEntryAttachFileInfo();

}
