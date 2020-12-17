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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
public interface AttachFileService extends IService<AttachFile> {


    /**
     * 上传后绑定数据
     * @param attachFile 通用附件表
     * @return R
     */
    void bindDataAfterUploading(List<AttachFileDTO> attachFile);


    /*    *
     * 通过id查询通用文件表数据
     * @param id id   业务表ID (例如：人才表的主键ID)
     * @param  moduleTyp   字典类型(文件所属字典类型 例如: 体检报告:MEDICAL_REPORT)
     * @return R
     * */
    List<AttachFileInfoDTO> getAttachFileById(Long id,String moduleTyp);
}
