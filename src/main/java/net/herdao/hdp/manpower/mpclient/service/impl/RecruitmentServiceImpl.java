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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.collection.CollectionUtil;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentMobileProgressVO;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentMobileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esms.MessageData;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.constant.CacheConstants;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.constant.enums.LoginTypeEnum;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.message.config.SmsTemplate;
import net.herdao.hdp.common.message.constant.SmsConstant;
import net.herdao.hdp.common.security.service.HdpUser;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentEducation;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentMapper;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import net.herdao.hdp.middle.api.feign.RemoteWorkflowService;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {

    private final RedisTemplate redisTemplate;

    private final SmsTemplate smsTemplate;

    private final RecruitmentEducationService recruitmentEducationService;
    
    private RemoteWorkflowService remoteWorkflowService;

    private RecruitmentFamilyStatusService recruitmentFamilyStatusService;

    private RecruitmentWorkexperienceService recruitmentWorkexperienceService;

    private RecruitmentAwardsService recruitmentAwardsService;

    private RecruitmentTitleService recruitmentTitleService;

    private RecruitmentTrainService recruitmentTrainService;

    private RecruitmentActivitiService recruitmentActivitiService;

    @Override
    public Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, String orgId, String searchText) {
        Page<RecruitmentDTO> list = this.baseMapper.findRecruitmentPage(page, orgId, searchText);
        return list;
    }

    @Override
    //@OperationEntity(operation = "人才表单更新",module="人才简历", clazz = RecruitmentUpdateFormDTO.class)
    public R<RecruitmentUpdateFormDTO> updateRecruitment(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        //校检手机号码
        if (checkMobile(recruitmentUpdateFormVO)){
            return R.failed("当前手机号码已被占用，请重新检查手机号码!");
        }

        //校检姓名+手机号码
        if (checkTalentNameAndMobile(recruitmentUpdateFormVO)){
            return R.failed("候选人#姓名#已存在!");
        }

        //校检邮箱
        if (checkEmail(recruitmentUpdateFormVO)){
            return R.failed("当前个人邮箱已被占用，请重新检查手机号码!");
        }

        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(recruitmentUpdateFormVO,recruitment);
        if (ObjectUtil.isNotNull(recruitmentUpdateFormVO.getOrgId())){
            recruitment.setOrgId(Long.parseLong(recruitmentUpdateFormVO.getOrgId()));
        }

        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            recruitment.setModifierTime(LocalDateTime.now());
            recruitment.setModifierCode(sysUser.getUsername());
            recruitment.setModifierName(sysUser.getAliasName());
        }

        super.updateById(recruitment);

        BeanUtils.copyProperties(recruitment, recruitmentUpdateFormVO);
        return R.ok(recruitmentUpdateFormVO,"快速编辑-保存成功");
    }

    @Override
    //@OperationEntity(operation = "人才表单新增",module="人才简历", clazz = RecruitmentAddFormDTO.class)
    public R saveRecruitment(RecruitmentAddFormDTO recruitmentAddFormDTO) {
        //先校检再新增
        RecruitmentUpdateFormDTO updateFormVO=new RecruitmentUpdateFormDTO();
        BeanUtils.copyProperties(recruitmentAddFormDTO,updateFormVO);
        //校检手机号码
        if (checkMobile(updateFormVO)){
            return R.failed("当前手机号码已被占用，请重新检查手机号码!");
        }
        //校检姓名+手机号码
        if (checkTalentNameAndMobile(updateFormVO)){
            return R.failed("候选人已存在!");
        }

        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(recruitmentAddFormDTO,recruitment);
        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            recruitment.setCreatorTime(LocalDateTime.now());
            recruitment.setCreatorCode(sysUser.getUsername());
            recruitment.setCreatorName(sysUser.getAliasName());
        }
        super.save(recruitment);

        BeanUtils.copyProperties(recruitment, recruitmentAddFormDTO);
        return R.ok(recruitmentAddFormDTO,"新增候选人成功");
    }

    /**
     * 校检手机号码
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkMobile(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isNotEmpty(recruitmentUpdateFormVO.getMobile())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getMobile,recruitmentUpdateFormVO.getMobile());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    /**
     * 校检姓名+手机号码
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkTalentNameAndMobile(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isAllNotEmpty(recruitmentUpdateFormVO.getTalentName(),recruitmentUpdateFormVO.getMobile())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getTalentName,recruitmentUpdateFormVO.getTalentName());
            queryWrapper.eq( Recruitment::getMobile,recruitmentUpdateFormVO.getMobile());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    /**
     * 校检邮箱
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkEmail(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isNotEmpty(recruitmentUpdateFormVO.getEmail())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getEmail,recruitmentUpdateFormVO.getEmail());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    @Override
    public RecruitmentUpdateFormDTO fetchResumeTop(Long id) {
        RecruitmentUpdateFormDTO entity = this.baseMapper.fetchResumeTop(id);
        return entity;
    }

    @Override
    //@OperationEntity(operation = "修改更新人才简历-个人基本情况",module="人才简历", clazz = RecruitmentDetailsDTO.class)
    public RecruitmentEditBaseInfoDTO updateBaseInfo(RecruitmentEditBaseInfoDTO dto) {
        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(dto,recruitment);
        recruitment.setCharacteristics(dto.getCharacteristics());
        if (ObjectUtil.isNotNull(dto.getIsAcceptAssignment())){
            if (dto.getIsAcceptAssignment()){
                recruitment.setIsAcceptAssignment(1);
            }
            if (!dto.getIsAcceptAssignment()){
                recruitment.setIsAcceptAssignment(0);
            }
        }
        if (ObjectUtil.isNotNull(dto.getIsRelativeCompany())){
            if (dto.getIsRelativeCompany()){
                recruitment.setIsRelativeCompany(1);
            }
            if (!dto.getIsRelativeCompany()){
                recruitment.setIsRelativeCompany(0);
            }
        }
        if (ObjectUtil.isNotNull(dto.getHeight())){
            recruitment.setHeight(Integer.parseInt(dto.getHeight()));
        }
        if (ObjectUtil.isNotNull(dto.getWeight())){
            recruitment.setWeight(Integer.parseInt(dto.getWeight()));
        }
        if (ObjectUtil.isNotNull(dto.getBirthday())){
            recruitment.setBirthday(LocalDateTimeUtils.convert2LocalDateTime(dto.getBirthday()));
        }

        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            recruitment.setModifierTime(LocalDateTime.now());
            recruitment.setModifierCode(sysUser.getUsername());
            recruitment.setModifierName(sysUser.getAliasName());
        }

        super.updateById(recruitment);
        BeanUtils.copyProperties(recruitment,dto);
        return dto;
    }

    @Override
    public RecruitmentJobIntentDTO fetchResumeJobIntent(Long id) {
        RecruitmentJobIntentDTO result = this.baseMapper.fetchResumeJobIntent(id);
        return result;
    }

    @Override
    //@OperationEntity(operation = "人才简历-从业情况与求职意向-更新",module="人才简历", clazz = RecruitmentJobIntentDTO.class)
    public RecruitmentJobIntentDTO updateRecruitmentJobIntent(RecruitmentJobIntentDTO dto) {
        Recruitment recruitment=new Recruitment();

        BeanUtils.copyProperties(dto,recruitment);
        if (ObjectUtil.isNotNull(dto.getOrgId())){
            recruitment.setOrgId(dto.getOrgId());
        }
        if (ObjectUtil.isNotNull(dto.getMinimumLevelincome())){
            recruitment.setMinimumLevelincome(new BigDecimal(dto.getMinimumLevelincome()));
        }
        if (ObjectUtil.isNotNull(dto.getMinimumLevelincome())){
            recruitment.setMinimumLevelincome(new BigDecimal(dto.getMinimumLevelincome()));
        }
        if (ObjectUtil.isNotNull(dto.getExpectedLevelincome())){
            recruitment.setExpectedLevelincome(new BigDecimal(dto.getExpectedLevelincome()));
        }

        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            recruitment.setModifierTime(LocalDateTime.now());
            recruitment.setModifierCode(sysUser.getUsername());
            recruitment.setModifierName(sysUser.getAliasName());
        }
        super.updateById(recruitment);
        BeanUtils.copyProperties(recruitment,dto);

        return dto;
    }

    @Override
    public RecruitmentEmployeeDTO fetchEmploy(String recruitmentId) {
        RecruitmentEmployeeDTO entity = this.baseMapper.fetchEmploy(recruitmentId);
        return entity;
    }

    @Override
    public RecruitmentPersonDTO fetchRecruitmentPerson(Long id) {
        RecruitmentPersonDTO recruitmentPersonDTO = this.baseMapper.fetchRecruitmentPerson(id);
        return recruitmentPersonDTO;
    }

    @Override
    public RecruitmentIntentDTO fetchRecruitmentIntent(Long id) {
        RecruitmentIntentDTO recruitmentIntentDTO = this.baseMapper.fetchRecruitmentIntent(id);
        return recruitmentIntentDTO;
    }

    /**
     * 更新和获取最高教育详情
     * @param id 人才ID
     * @return
     */
    public RecruitmentTopEduDTO updateRecruitmentTopEdu(Long id) {
        //获取人才教育表的最高学历信息
        LambdaQueryWrapper<RecruitmentEducation> eduQueryWrapper = Wrappers.lambdaQuery();
        eduQueryWrapper.eq( RecruitmentEducation::getRecruitmentId,id).orderByDesc(RecruitmentEducation::getPeriod);
        List<RecruitmentEducation> eduList = recruitmentEducationService.list(eduQueryWrapper);

        //获取人才表的最高教育信息
        RecruitmentTopEduDTO recruitmentTopEdu = this.baseMapper.fetchRecruitmentTopEdu(id);

        //如果人才教育表的最高学历信息不为空 则进行赋值和更新操作
        if (ObjectUtil.isNotEmpty(eduList)){
            RecruitmentEducation education = eduList.get(0);

            //把“人才教育表的最高学历信息”赋值到“人才表的最高教育信息”
            if (ObjectUtil.isNotEmpty(education)){
                BeanUtils.copyProperties(education,recruitmentTopEdu);
                recruitmentTopEdu.setBeginDate(LocalDateTimeUtils.convert2Long(education.getPeriod()));
                recruitmentTopEdu.setEndDate(LocalDateTimeUtils.convert2Long(education.getTodate()));
                recruitmentTopEdu.setHighestEducation(education.getEducationQua());
                recruitmentTopEdu.setEducationDegree(education.getDegree());
                recruitmentTopEdu.setGraduated(education.getSchoolName());
            }

            //更新人才表的最高学历教育信息
            Recruitment recruitment=new Recruitment();
            if (ObjectUtil.isNotEmpty(recruitmentTopEdu)){
                BeanUtils.copyProperties(recruitmentTopEdu,recruitment);
                super.updateById(recruitment);
            }
        }

        return recruitmentTopEdu;
    }

    @Override
    public R<Recruitment> recruitmentLogin(String mobile, String code) {
        String key = CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile;
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        if (!redisTemplate.hasKey(key)) {
            return  R.failed("验证码不合法");
        }

        String redisCode = (String)redisTemplate.opsForValue().get(key);

        if (StrUtil.isBlank(redisCode)) {
            redisTemplate.delete(key);
            return  R.failed("验证码不合法");
        }

        if (!StrUtil.equals(redisCode, code)) {
            return  R.failed("验证码不合法");
        }

        List<Recruitment> recruitments = this.baseMapper.selectList(Wrappers.<Recruitment>query().lambda().eq(Recruitment::getMobile, mobile));
        Recruitment recruitment=new Recruitment();
        if(recruitments.isEmpty() || recruitments.size() != 1){
            return  R.failed("手机号不合法");
        }

        if (ObjectUtil.isNotEmpty(recruitments)){
            recruitment = recruitments.get(0);
        }

        return R.ok(recruitment);
    }

    @Override
    public RecruitmentOtherInfo fetchRecruitmentOtherInfo(Long id) {
        RecruitmentOtherInfo recruitmentOtherInfo = this.baseMapper.fetchRecruitmentOtherInfo(id);
        return recruitmentOtherInfo;
    }

    @Override
    public RecruitmentBaseInfo fetchRecruitmentBaseInfo(Long id) {
        RecruitmentBaseInfo recruitmentBaseInfo = this.baseMapper.fetchRecruitmentBaseInfo(id);
        return recruitmentBaseInfo;
    }

    @Override
    public RecruitmentEditOtherInfoDTO updateOtherInfo(RecruitmentEditOtherInfoDTO otherInfo) {
        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(otherInfo,recruitment);
        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            recruitment.setModifierTime(LocalDateTime.now());
            recruitment.setModifierCode(sysUser.getUsername());
            recruitment.setModifierName(sysUser.getAliasName());
        }
        if (ObjectUtil.isNotNull(otherInfo.getResumeAccessTime())){
            recruitment.setResumeAccessTime(LocalDateTimeUtils.convert2LocalDateTime(otherInfo.getResumeAccessTime()));
        }

        super.updateById(recruitment);
        BeanUtils.copyProperties(recruitment,otherInfo);

        return otherInfo;
    }

    @Override
    public R<Boolean> sendSmsCode(String mobile) {
        List<Recruitment> recruitments = this.baseMapper.selectList(Wrappers.<Recruitment>query().lambda().eq(Recruitment::getMobile, mobile));
        if(recruitments.isEmpty() || recruitments.size() != 1){
            log.info("手机号未注册:{}", mobile);
            return R.ok(Boolean.FALSE, "手机号未注册");
        }

        Object codeObj = redisTemplate.opsForValue()
                .get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);

        if (codeObj != null) {
            log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
            return R.ok(Boolean.FALSE, "验证码发送过频繁");
        }

        String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
        log.debug("手机号生成验证码成功:{},{}", mobile, code);
        redisTemplate.opsForValue().set(
                CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code,
                SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
        MessageData message = new MessageData(mobile, String.format(SmsConstant.MP_TEMPLATE,code));
        R result = smsTemplate.doSendSingleSmsForSingle(message);
        log.info("执行短信通知结果: {}，消息体: {}", result.getMsg(), message);
        return result;
    }
    
    /**
     * 
     * @param recordId  表单业务ID
     * @param flowType 流程类型（录用流程）
     * @return
     */
    @Override
    public R generateWorkflow(String recordId,String flowType,String contentUrl){
    	
    	HdpUser user = SecurityUtils.getUser();
    	
    	String userId = user.getUsername();
    	Long deptId = user.getDeptId();
    	
    	//String parameterJson = "{ CreateUser:\"22\", ReferenceUser:\"22\", Org_Code:\"011\", Condition:\"\"}";
    	String parameterJson = "{\"CreateUser\":\""+userId+"\", \"ReferenceUser\":\""+userId+"\", \"Org_Code\":\"011\", \"Condition\":\"\"}";
    	if(StringUtils.isEmpty(contentUrl)) {
    		contentUrl = "www.baidu.com";
    	}
    	String contentFK = recordId;
    	String flowContent = flowType;
    	return remoteWorkflowService.generateWorkflow(parameterJson, contentUrl, contentFK, flowContent);

    }

    @Override
    public R saveOrUpdateRecruitment(RecruitmentBaseInfoMobileDTO dto) {
        if (ObjectUtil.isNotNull(dto)){
            //先校检
            RecruitmentUpdateFormDTO updateFormVO=new RecruitmentUpdateFormDTO();
            BeanUtils.copyProperties(dto,updateFormVO);
            //校检手机号码
            if (checkMobile(updateFormVO)){
                return R.failed("当前手机号码已被占用，请重新检查手机号码!");
            }
            //校检姓名+手机号码
            if (checkTalentNameAndMobile(updateFormVO)){
                return R.failed("候选人已存在!");
            }

            Recruitment recruitment=new Recruitment();
            BeanUtils.copyProperties(dto,recruitment);
            SysUser sysUser = SysUserUtils.getSysUser();

            //新增
            if (ObjectUtil.isNull(dto.getId())){
                if (ObjectUtil.isNotNull(sysUser)){
                    recruitment.setCreatorTime(LocalDateTime.now());
                    recruitment.setCreatorCode(sysUser.getUsername());
                    recruitment.setCreatorName(sysUser.getAliasName());
                }
                super.save(recruitment);
            }

            //修改
            if (ObjectUtil.isNotNull(dto.getId())){
                if (ObjectUtil.isNotNull(sysUser)){
                    recruitment.setModifierTime(LocalDateTime.now());
                    recruitment.setModifierCode(sysUser.getUsername());
                    recruitment.setModifierName(sysUser.getAliasName());
                }
                super.updateById(recruitment);
            }
        }

        return R.ok(dto,"新增或修改成功！");
    }

    @Override
    public RecruitmentMobileProgressVO fetchMobileInfoProgress(Long id) {
        //总字段数
        int total=0;
        //字段填写完成数
        int finishCount=0;

        //获取匹配字段
        Field[] targetFields = RecruitmentMobileVO.class.getDeclaredFields();
        //家庭状况
        List<RecruitmentFamilyDTO> familyList = recruitmentFamilyStatusService.fetchResumeFamilyList(id);
        //教育经历
        List<RecruitmentEduDTO> recruitmentEduList = recruitmentEducationService.fetchResumeEduList(id);
        //工作经历
        List<RecruitmentWorkExperienceDTO> workList = recruitmentWorkexperienceService.findWorkExperienceList(id);
        //个人基本信息
        RecruitmentBaseInfo baseInfo = this.fetchRecruitmentBaseInfo(id);

        for (Field field : targetFields) {
            String targetName = field.getName();

            //统计家庭情况的命中数
            if (CollectionUtil.isNotEmpty(familyList)){
                for (RecruitmentFamilyDTO familyDTO : familyList) {
                    Map<String, Integer> hitMap = getHitCount(familyDTO, targetName);
                    finishCount+=hitMap.get("hitCount");
                    total+=hitMap.get("fieldCount");
                }
            }

            //统计教育经历的命中数
            if (CollectionUtil.isNotEmpty(recruitmentEduList)){
                for (RecruitmentEduDTO eduDTO : recruitmentEduList) {
                    Map<String, Integer> hitMap = getHitCount(eduDTO, targetName);
                    finishCount+=hitMap.get("hitCount");
                    total+=hitMap.get("fieldCount");
                }
            }

            //统计工作经历的命中数
            if (CollectionUtil.isNotEmpty(workList)){
                for (RecruitmentWorkExperienceDTO workExperienceDTO : workList) {
                    Map<String, Integer> hitMap = getHitCount(workExperienceDTO, targetName);
                    finishCount+=hitMap.get("hitCount");
                    total+=hitMap.get("fieldCount");
                }
            }

            //统计个人基本信息的命中数
            if (ObjectUtil.isNotNull(baseInfo)){
                Map<String, Integer> hitMap = getHitCount(baseInfo, targetName);
                finishCount+=hitMap.get("hitCount");
                total+=hitMap.get("fieldCount");
            }
        }

        //创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        //设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        //统计填写进度
        String progress = numberFormat.format((float)finishCount/(float)total*100)+"%";
        String status="";
        if (finishCount==total){
            status="已完成";
        }else{
            status="未完成";
        }

        RecruitmentMobileProgressVO result=new RecruitmentMobileProgressVO();
        result.setTotal(total);
        result.setFinishCount(finishCount);
        result.setProgress(progress);
        result.setStatus(status);
        return result;
    }

    /**
     * 获取填报命中数
     * @param obj
     * @param targetName
     */
    private Map<String,Integer> getHitCount(Object obj, String targetName){
        //命中数
        int hitCount=0;
        //字段数
        int fieldCount=0;
        //返回map
        Map<String,Integer> resultMap=new HashMap<>();

        try {
            //得到所有属性
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String originalName = field.getName();
                if (originalName.equals(targetName)) {
                    fieldCount++;
                    //获取属性值
                    Object originalValue = field.get(obj);
                    if (ObjectUtil.isNotNull(originalValue)) {
                        hitCount++;
                    }
                }
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }

        resultMap.put("hitCount",hitCount);
        resultMap.put("fieldCount",fieldCount);
        return resultMap;
    }

    @Override
    public RecruitmentWorkDetailsDTO fetchResumeWorkDetailsByMobile(Long id) {
        RecruitmentWorkDetailsDTO result=new RecruitmentWorkDetailsDTO();

        //获奖情况
        List<RecruitmentAwardsDTO> awardsList = recruitmentAwardsService.fetchResumeAwardsList(id);
        result.setRecruitmentAwardsDTO(awardsList);

        //工作经历
        List<RecruitmentWorkExperienceDTO> workList = recruitmentWorkexperienceService.findWorkExperienceList(id);
        result.setWorkExperienceDTO(workList);

        //职称及职业资格
        List<RecruitmentTitleDTO> titleList = recruitmentTitleService.findRecruitmentTitleList(id);
        result.setRecruitmentTitleDTO(titleList);

        //培训经历
        List<RecruitmentTrainDTO> trainList = recruitmentTrainService.findRecruitmentTrainList(id);
        result.setRecruitmentTrainDTO(trainList);

        //人才活动
        List<RecruitmentActivitiDTO> activitiList = recruitmentActivitiService.findRecruitmentActivitiList(id);
        result.setRecruitmentActivitiDTO(activitiList);
        return result;
    }

    @Override
    public RecruitmentDetailsDTO fetchRecruitmentDetailsDTO(Long id) {
        RecruitmentDetailsDTO result = new RecruitmentDetailsDTO();

        //个人基本情况
        RecruitmentPersonDTO personDTO = this.baseMapper.fetchRecruitmentPerson(id);
        if (ObjectUtil.isNotNull(personDTO)&&ObjectUtil.isNotNull(personDTO.getBirthdayLocal())){
            personDTO.setBirthday(LocalDateTimeUtils.convert2Long(personDTO.getBirthdayLocal()));
        }

        //从业情况与求职意向
        RecruitmentIntentDTO intentDTO = this.baseMapper.fetchRecruitmentIntent(id);
        if (ObjectUtil.isNotNull(intentDTO)){
            if(ObjectUtil.isNotNull(intentDTO.getInductionTimeLocal())){
                intentDTO.setInductionTime(LocalDateTimeUtils.convert2Long(intentDTO.getInductionTimeLocal()));
            }
            if(ObjectUtil.isNotNull(intentDTO.getWorkDateLocal())){
                intentDTO.setWorkdate(LocalDateTimeUtils.convert2Long(intentDTO.getInductionTimeLocal()));
            }
        }

        //工作经历
        List<RecruitmentWorkExperienceDTO> workList = recruitmentWorkexperienceService.findWorkExperienceList(id);
        if (CollectionUtil.isNotEmpty(workList)){
            for (RecruitmentWorkExperienceDTO dto : workList) {
                if (ObjectUtil.isNotNull(dto.getToDateLocal())){
                    dto.setTodate(LocalDateTimeUtils.convert2Long(dto.getToDateLocal()));
                }
                if (ObjectUtil.isNotNull(dto.getPeriodLocal())){
                    dto.setPeriod(LocalDateTimeUtils.convert2Long(dto.getPeriodLocal()));
                }
            }
        }

        //家庭情况
        List<RecruitmentFamilyDTO> familyDTOList = recruitmentFamilyStatusService.fetchResumeFamilyList(id);

        //获奖资格
        List<RecruitmentAwardsDTO> recruitmentAwardsList = recruitmentAwardsService.fetchResumeAwardsList(id);
        if (CollectionUtil.isNotEmpty(recruitmentAwardsList)){
            for (RecruitmentAwardsDTO dto : recruitmentAwardsList) {
                if (ObjectUtil.isNotNull(dto)){
                    dto.setAwardsTime(LocalDateTimeUtils.convert2Long(dto.getAwardsTimeLocal()));
                }
            }
        }

        //更新和获取最高教育详情
        RecruitmentTopEduDTO topEduDTO = updateRecruitmentTopEdu(id);

        result.setRecruitmentPersonDTO(personDTO);
        result.setRecruitmentIntentDTO(intentDTO);
        result.setRecruitmentWorkexperienceDTO(workList);
        result.setRecruitmentTopEduDTO(topEduDTO);
        result.setRecruitmentFamilyDTO(familyDTOList);
        result.setRecruitmentAwardsDTO(recruitmentAwardsList);
        return result;
    }

    @Override
    public RecruitmentWorkDetailsDTO fetchResumeWorkDetails(Long id) {
        RecruitmentWorkDetailsDTO result=new RecruitmentWorkDetailsDTO();

        //获奖情况
        List<RecruitmentAwardsDTO> awardsList = recruitmentAwardsService.fetchResumeAwardsList(id);
        result.setRecruitmentAwardsDTO(awardsList);

        //工作经历
        List<RecruitmentWorkExperienceDTO> workList = recruitmentWorkexperienceService.findWorkExperienceList(id);
        result.setWorkExperienceDTO(workList);

        //职称及职业资格
        List<RecruitmentTitleDTO> titleList = recruitmentTitleService.findRecruitmentTitleList(id);
        result.setRecruitmentTitleDTO(titleList);

        //培训经历
        List<RecruitmentTrainDTO> trainList = recruitmentTrainService.findRecruitmentTrainList(id);
        result.setRecruitmentTrainDTO(trainList);

        //人才活动
        List<RecruitmentActivitiDTO> activitiList = recruitmentActivitiService.findRecruitmentActivitiList(id);
        result.setRecruitmentActivitiDTO(activitiList);

        return result;
    }

    @Override
    public RecruitmentEditDetailsDTO fetchResumeEditDetails(Long id) {
        RecruitmentEditDetailsDTO result=new RecruitmentEditDetailsDTO();

        //个人基本信息
        RecruitmentBaseInfo baseInfo = this.baseMapper.fetchRecruitmentBaseInfo(id);
        if (ObjectUtil.isNotNull(baseInfo)){
            if (ObjectUtil.isNotNull(baseInfo.getBirthdayLocal())){
                baseInfo.setBirthday(LocalDateTimeUtils.convert2Long(baseInfo.getBirthdayLocal()));
            }

            RecruitmentEditBaseInfoDTO editBaseInfo=new RecruitmentEditBaseInfoDTO();
            BeanUtils.copyProperties(baseInfo,editBaseInfo);
            result.setRecruitmentEditBaseInfoDTO(editBaseInfo);
        }

        //其他个人信息
        RecruitmentOtherInfo otherInfo = this.baseMapper.fetchRecruitmentOtherInfo(id);
        if (ObjectUtil.isNotNull(otherInfo)){
            if (ObjectUtil.isNotNull(otherInfo.getResumeAccessTimeLocal())){
                otherInfo.setResumeAccessTime(LocalDateTimeUtils.convert2Long(otherInfo.getResumeAccessTimeLocal()));
            }

            RecruitmentEditOtherInfoDTO editOtherInfo=new RecruitmentEditOtherInfoDTO();
            BeanUtils.copyProperties(otherInfo,editOtherInfo);
            result.setRecruitmentEditOtherInfoDTO(editOtherInfo);
        }

        //家庭状况
        List<RecruitmentFamilyDTO> familyList = recruitmentFamilyStatusService.fetchResumeFamilyList(id);
        List<RecruitmentEditFamilyDTO> editFamilyList=new ArrayList<RecruitmentEditFamilyDTO>();
        if (ObjectUtil.isNotEmpty(familyList)){
            familyList.forEach(e->{
                if (ObjectUtil.isNotNull(e)){
                    RecruitmentEditFamilyDTO editFamilyDTO=new RecruitmentEditFamilyDTO();
                    BeanUtils.copyProperties(e,editFamilyDTO);
                    editFamilyList.add(editFamilyDTO);
                    result.setRecruitmentEditFamilyDTO(editFamilyList);
                }
            });
        }

        //教育经历
        List<RecruitmentEduDTO> recruitmentEduList = recruitmentEducationService.fetchResumeEduList(id);
        List<RecruitmentEditEduDTO> editEduList=new ArrayList<RecruitmentEditEduDTO>();
        if (ObjectUtil.isNotEmpty(recruitmentEduList)){
            recruitmentEduList.forEach(e->{
                if (ObjectUtil.isNotNull(e)){
                    RecruitmentEditEduDTO editEduDTO=new RecruitmentEditEduDTO();
                    BeanUtils.copyProperties(e,editEduDTO);
                    editEduList.add(editEduDTO);
                    result.setRecruitmentEditEduDTO(editEduList);
                }
            });
        }

        return result;
    }

    @Override
    public RecruitmentEditDetailsDTO fetchResumeEditDetailsByMobile(Long id) {
        RecruitmentEditDetailsDTO result=new RecruitmentEditDetailsDTO();

        //教育经历
        List<RecruitmentEduDTO> recruitmentEduList = recruitmentEducationService.fetchResumeEduList(id);
        List<RecruitmentEditEduDTO> editEduList=new ArrayList<RecruitmentEditEduDTO>();
        if (ObjectUtil.isNotEmpty(recruitmentEduList)){
            recruitmentEduList.forEach(e->{
                if (ObjectUtil.isNotNull(e)){
                    RecruitmentEditEduDTO editEduDTO=new RecruitmentEditEduDTO();
                    BeanUtils.copyProperties(e,editEduDTO);
                    editEduDTO.setTodate(LocalDateTimeUtils.convert2Long(e.getPeriodLocal()));
                    editEduDTO.setPeriod(LocalDateTimeUtils.convert2Long(e.getPeriodLocal()));
                    editEduList.add(editEduDTO);
                    result.setRecruitmentEditEduDTO(editEduList);
                }
            });
        }

        //家庭状况
        List<RecruitmentFamilyDTO> familyList = recruitmentFamilyStatusService.fetchResumeFamilyList(id);
        List<RecruitmentEditFamilyDTO> editFamilyList=new ArrayList<RecruitmentEditFamilyDTO>();
        if (ObjectUtil.isNotEmpty(familyList)){
            familyList.forEach(e->{
                if (ObjectUtil.isNotNull(e)){
                    RecruitmentEditFamilyDTO editFamilyDTO=new RecruitmentEditFamilyDTO();
                    BeanUtils.copyProperties(e,editFamilyDTO);
                    editFamilyList.add(editFamilyDTO);
                    result.setRecruitmentEditFamilyDTO(editFamilyList);
                }
            });
        }

        //其他个人信息
        RecruitmentOtherInfo otherInfo = this.baseMapper.fetchRecruitmentOtherInfo(id);
        if (ObjectUtil.isNotNull(otherInfo)){
            RecruitmentEditOtherInfoDTO editOtherInfo=new RecruitmentEditOtherInfoDTO();
            BeanUtils.copyProperties(otherInfo,editOtherInfo);
            result.setRecruitmentEditOtherInfoDTO(editOtherInfo);
        }

        //个人基本信息
        RecruitmentBaseInfo baseInfo = this.baseMapper.fetchRecruitmentBaseInfo(id);
        if (ObjectUtil.isNotNull(baseInfo)){
            if (ObjectUtil.isNotNull(baseInfo.getBirthdayLocal())){
                baseInfo.setBirthday(LocalDateTimeUtils.convert2Long(baseInfo.getBirthdayLocal()));
            }
            RecruitmentEditBaseInfoDTO editBaseInfo=new RecruitmentEditBaseInfoDTO();
            BeanUtils.copyProperties(baseInfo,editBaseInfo);
            result.setRecruitmentEditBaseInfoDTO(editBaseInfo);
        }

        return result;
    }
}
