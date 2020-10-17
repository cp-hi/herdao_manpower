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
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.mapper.StaffRewardsPulishmentsMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.utils.RegexUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffRpVO;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Service
public class StaffRewardsPulishmentsServiceImpl extends ServiceImpl<StaffRewardsPulishmentsMapper, StaffRewardsPulishments> implements StaffRewardsPulishmentsService {
    @Autowired
    private SysDictItemService itemService;

    @Override
    public Page<StaffRpDTO> findStaffRpPage(Page<StaffRpDTO> page, String searchText) {
        Page<StaffRpDTO> list = this.baseMapper.findStaffRpPage(page, searchText);
        return list;
    }

    @Override
    public List<StaffRpDTO> findStaffRp(String searchText) {
        List<StaffRpDTO> list = this.baseMapper.findStaffRp(searchText);
        return list;
    }

    @Override
    public void saveVerify(StaffRewardsPulishments rewardsPulishments) {

    }

    @Override
    public void importVerify(StaffRewardsPulishments rewardsPulishments, int type) {
        //新增校检
        if (type == 0){
            checkAdd((StaffRpVO) rewardsPulishments);
        }

        //编辑校检
        if (type == 1){
             checkUpdate((StaffRpVO) rewardsPulishments);
        }
    }

    /**
     * 新增校检
     * @param dto
     */
    private void checkAdd(StaffRpVO dto) {
        String errorMsg="";

        if (null == dto.getStaffCode()){
            errorMsg+="员工工号不能为空，";
        }

        if (null == dto.getChoiceName()){
            errorMsg+="奖励/惩罚不能为空，";
        }else{
            if (!dto.getChoiceName().equals("奖励") && !dto.getChoiceName().equals("惩罚") ){
                errorMsg+="奖励/惩罚字段只能填写：奖励 或者 惩罚”，";
            }

            //奖励/惩罚 0:奖励 1:惩罚
            if (!dto.getChoiceName().equals("奖励")  ){
                dto.setChoice(false);
            }
            if (!dto.getChoiceName().equals("惩罚")  ){
                dto.setChoice(true);
            }
        }

        SysDictItem dictItem = itemService.getOne(
                new QueryWrapper<SysDictItem>()
                        .eq("type", "YGJCLB")
                        .eq("label", dto.getType())
                        .eq("del_flag", 0)
        );

        boolean isNumber = RegexUtils.isNumber(dto.getAmount());
        if (!isNumber){
            errorMsg+="请填写整的奖金金额（奖金金额为正整数）:"+dto.getAmount();
        }


        if (null==dictItem){
            errorMsg+="员工奖惩类别不存在或已停用:"+dto.getType();
        }else {
            dto.setType(dictItem.getValue());
        }

        if (!errorMsg.isEmpty()){
            throw new RuntimeException(errorMsg);
        }
    }


    /**
     * 新增校检
     * @param dto
     */
    private void checkUpdate(StaffRpVO dto) {
        String errorMsg="";

        if (null == dto.getStaffCode()){
            errorMsg+="员工工号不能为空，";
        }

        if (null == dto.getChoiceName()){
            errorMsg+="奖励/惩罚不能为空，";
        }else{
            if (!dto.getChoiceName().equals("奖励") && !dto.getChoiceName().equals("惩罚") ){
                errorMsg+="奖励/惩罚字段只能填写：奖励 或者 惩罚”，";
            }

            //奖励/惩罚 0:奖励 1:惩罚
            if (!dto.getChoiceName().equals("奖励")  ){
                dto.setChoice(false);
            }
            if (!dto.getChoiceName().equals("惩罚")  ){
                dto.setChoice(true);
            }
        }

        SysDictItem dictItem = itemService.getOne(
                new QueryWrapper<SysDictItem>()
                        .eq("type", "YGJCLB")
                        .eq("label", dto.getType())
                        .eq("del_flag", 0)
        );

        if (null==dictItem){
            errorMsg+="员工奖惩类别不存在或已停用:"+dto.getType();
        }else {
            dto.setType(dictItem.getValue());
        }

        if (!errorMsg.isEmpty()){
            throw new RuntimeException(errorMsg);
        }
    }
}
