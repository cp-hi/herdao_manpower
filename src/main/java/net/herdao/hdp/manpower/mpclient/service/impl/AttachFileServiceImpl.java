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

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileSituationDTO;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.mapper.AttachFileMapper;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileDTO;
import net.herdao.hdp.manpower.mpmobile.dto.AttachFileInfoDTO;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     *
     * @param attachFile 通用附件表
     * @return R
     */
    @Override
    public void bindDataAfterUploading(List<AttachFileDTO> attachFile) {
        //多张图片上传后绑定保存
        if (attachFile != null) {
            attachFile.forEach(item -> {
                        AttachFile file = new AttachFile();
                        BeanUtils.copyProperties(item, file);
                        AttachFile attach = this.baseMapper.selectOne(new QueryWrapper<AttachFile>().lambda().
                                eq(AttachFile::getBizId, item.getBizId()).eq(AttachFile::getModuleType, item.getModuleType()));
                        SysUser sysUser = SysUserUtils.getSysUser();
                        //不为空 修改
                        if (attach != null) {
                            if (ObjectUtil.isNotNull(sysUser)) {
                                file.setModifierTime(LocalDateTime.now());
                                file.setModifierCode(sysUser.getUsername());
                                file.setModifierName(sysUser.getAliasName());
                            }
                            this.baseMapper.update(file, new LambdaUpdateWrapper<AttachFile>().
                                    eq(AttachFile::getBizId, item.getBizId()).eq(AttachFile::getModuleType, item.getModuleType()));
                            return;
                        }

                        if (ObjectUtil.isNotNull(sysUser)) {
                            file.setCreatorTime(LocalDateTime.now());
                            file.setCreatorCode(sysUser.getUsername());
                            file.setCreatorName(sysUser.getAliasName());
                        }
                        //否则  新增
                        file.setFileType(item.getExtend());
                        this.baseMapper.insert(file);
                    }
            );
        }


    }


    /**
     * 通过id查询通用文件表数据
     *
     * @param id         id   业务表ID (例如：人才表的主键ID)
     * @param moduleType 字典类型(文件所属字典类型 例  如:   体检报告:MEDICAL_REPORT)
     * @return R
     */
    @Override
    public List<AttachFileInfoDTO> getAttachFileById(Long id, String moduleType) {
        List<AttachFile> attachfile = this.baseMapper.selectList(new QueryWrapper<AttachFile>().select("file_id").lambda().
                eq(AttachFile::getBizId,
                        String.valueOf(id)).like(AttachFile::getModuleType, moduleType));
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
            BeanUtils.copyProperties(record, attachFileDTO);
            list.add(attachFileDTO);
        }
        return list;
    }

    @Override
    public List<AttachFileSituationDTO> fetchResumeAttachFileInfo() {
        return this.baseMapper.fetchResumeAttachFileInfo();
    }

    @Override
    public List<AttachFileSituationDTO> fetchEntryAttachFileInfo() {
        return this.baseMapper.fetchEntryAttachFileInfo();
    }


}
