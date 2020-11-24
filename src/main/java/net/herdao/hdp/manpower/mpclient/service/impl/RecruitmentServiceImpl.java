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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@Service
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {
    @Override
    public Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, RecruitmentDTO recruitmentDTO, String searchText) {
        Page<RecruitmentDTO> list = this.baseMapper.findRecruitmentPage(page, recruitmentDTO, searchText);
        return list;
    }

    @Override
    @OperationEntity(operation = "新增、修改人才库", clazz = Organization.class)
    public boolean saveOrUpdate(Recruitment recruitment) {
        boolean status =false;
        if (null != recruitment){
            SysUser sysUser = SysUserUtils.getSysUser();

            //新增
            if (null == recruitment.getId()){
                recruitment.setCreatorTime(LocalDateTime.now());
                recruitment.setCreatorCode(sysUser.getUsername());
                recruitment.setCreatorName(sysUser.getAliasName());
                status = super.save(recruitment);
            }

            //修改
            if (null != recruitment.getId()){
                recruitment.setModifierTime(LocalDateTime.now());
                recruitment.setModifierCode(sysUser.getUsername());
                recruitment.setModifierName(sysUser.getAliasName());
                status = super.updateById(recruitment);
            }
        }


        return false;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
