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
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.mapper.AttachFileMapper;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
@Service
public class AttachFileServiceImpl extends ServiceImpl<AttachFileMapper, AttachFile> implements AttachFileService {


    @Autowired
    private AttachFileMapper mapper;


    /**
     * 上传后绑定数据
     * @param attachFile 通用附件表
     * @return R
     */
    @Override
    public void bindDataAfterUploading(List<AttachFileDTO> attachFile)   {
        List<AttachFile> list = new ArrayList<>(10);
        attachFile.forEach(item -> {
            AttachFile file = new AttachFile();
            BeanUtils.copyProperties(item,file);
            file.setFileType(item.getExtend());
            file.setCreatorTime(LocalDateTime.now());
            list.add(file);
                }
        );
        this.baseMapper.insertBatch(list);
    }



    /*    *
     * 通过id查询通用文件表数据
     * @param id id   业务表ID (例如：人才表的主键ID)
     * @param  moduleTyp   字典类型(文件所属字典类型 例如: 体检报告:MEDICAL_REPORT)
     * @return R
     * */
    @Override
    public List<AttachFileInfoDTO> getAttachFileById(Long id,String moduleType) {
        List<AttachFile> attachfile = this.baseMapper.selectList(new QueryWrapper<AttachFile>().select("file_id").lambda().
                eq(AttachFile::getBizId,
                        String.valueOf(id)).like(AttachFile::getModuleType,moduleType));
        List<AttachFileInfoDTO> attachFileInfoDTOS = convert2DtoList(attachfile);
        return attachFileInfoDTOS;
    }

    /**
     * 适配数据库中获取的原始数据为传给前端
     *
     * @param attachfile
     * @return
     */
    private List<AttachFileInfoDTO> convert2DtoList(List<AttachFile> attachfile) {
        List<AttachFileInfoDTO> list = new ArrayList<>();
        for (AttachFile record : attachfile) {
            AttachFileInfoDTO attachFileDTO = new AttachFileInfoDTO();
            BeanUtils.copyProperties(record,attachFileDTO);
            list.add(attachFileDTO);
        }
        return list;
    }
}
