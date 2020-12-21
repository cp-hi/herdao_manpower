package net.herdao.hdp.manpower.mpclient.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollectionUtil;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentMobileProgressVO;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentMobileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.annotation.Inner;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.GenerateWorkflowDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentActivitiDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAddFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentBaseInfo;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentBaseInfoMobileDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDetailsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEditBaseInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEditDetailsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEditEduDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEditFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEditOtherInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEduDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEmployeeDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentIntentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentJobIntentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentJobIntentResultDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentOtherInfo;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentPersonDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTitleDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTopEduDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTrainDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentWorkDetailsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.WorkflowNotifyDTO;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentActivitiService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentAwardsService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentEducationService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentFamilyStatusService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTitleService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTrainService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentWorkexperienceService;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.QrCodeUtils;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.ModuleVO;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitment")
@Api(value = "recruitment", tags = "人才表管理")
@Slf4j
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    private final OperationLogService operationLogService;

    private final RecruitmentWorkexperienceService recruitmentWorkexperienceService;

    private final RecruitmentFamilyStatusService recruitmentFamilyStatusService;

    private final RecruitmentAwardsService recruitmentAwardsService;

    private final RecruitmentEducationService recruitmentEducationService;

    private final  RecruitmentTitleService recruitmentTitleService;

    private final  RecruitmentTrainService recruitmentTrainService;

    private final  RecruitmentActivitiService recruitmentActivitiService;

    private final StaffEntrypostApproveService staffEntrypostApproveService;

    /**
     * 人才管理-快速编辑
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才管理-快速编辑", notes = "人才管理-快速编辑")
    @GetMapping("/quickEdit")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "id", value = "主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_view')" )
    public R<RecruitmentUpdateFormDTO> quickEdit(Long id) {
        Recruitment recruitment = recruitmentService.getById(id);
        RecruitmentUpdateFormDTO formVO = new RecruitmentUpdateFormDTO();
        BeanUtils.copyProperties(recruitment, formVO);
        formVO.setOrgId(String.valueOf(recruitment.getOrgId()));
        return R.ok(formVO);
    }

     /**
     * 快速编辑-修改更新
     *
     * @param recruitmentUpdateFormVO 人才表
     * @return R
     */
    @ApiOperation(value = "快速编辑-修改更新", notes = "快速编辑-修改更新")
    @PostMapping("/updateRecruitment")
    public R<RecruitmentUpdateFormDTO> updateRecruitment(@Validated @RequestBody RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
         return recruitmentService.updateRecruitment(recruitmentUpdateFormVO);
    }

    /**
     * 快速编辑-修改更新-手机端
     * @param recruitmentUpdateFormVO 人才表
     * @return R
     */
    @ApiOperation(value = "快速编辑-修改更新-手机端", notes = "快速编辑-修改更新-手机端")
    @PostMapping("/updateRecruitmentByMobile")
    public R<RecruitmentUpdateFormDTO> updateRecruitmentByMobile(@Validated @RequestBody RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        return recruitmentService.updateRecruitment(recruitmentUpdateFormVO);
    }

    /**
     * 人才管理-添加候选人-新增保存
     *
     * @param recruitmentAddFormDTO 人才表
     * @return R
     */
    @ApiOperation(value = "人才管理-添加候选人-新增保存", notes = "人才管理-添加候选人-新增保存")
    @PostMapping("/saveRecruitment")
    public R saveRecruitment(@Validated @RequestBody RecruitmentAddFormDTO recruitmentAddFormDTO) {
        return recruitmentService.saveRecruitment(recruitmentAddFormDTO);
    }

    /**
     * 人才库列表
     *
     * @param page       分页对象
     * @param orgId      组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "人才库列表;批量邀请更新简历信息列表", notes = "人才库列表;批量邀请更新简历信息列表")
    @GetMapping("/findRecruitmentPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页对象", required = true),
            @ApiImplicitParam(name = "orgId", value = "组织ID"),
            @ApiImplicitParam(name = "searchText", value = "关键字搜索"),
    })
    public R<Page<RecruitmentDTO>> findRecruitmentPage(Page page, String orgId, String searchText) {
        Page<RecruitmentDTO> recruitmentPage = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        return R.ok(recruitmentPage);
    }

    /**
     * 人才管理-删除候选人
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = " 人才管理-删除候选人", notes = " 人才管理-删除候选人")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentService.removeById(id));
    }

    /**
     * 人才简历-顶部
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-顶部", notes = "人才简历-顶部")
    @GetMapping("/fetchResumeTop")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键id")
    })
    public R<RecruitmentUpdateFormDTO> fetchResumeTop(Long id) {
        RecruitmentUpdateFormDTO entity = recruitmentService.fetchResumeTop(id);
        return R.ok(entity);
    }

    /**
     * 人才简历-顶部-手机端
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-顶部-手机端", notes = "人才简历-顶部-手机端")
    @GetMapping("/fetchResumeTopByMobile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    public R<RecruitmentUpdateFormDTO> fetchResumeTopByMobile(Long id) {
        RecruitmentUpdateFormDTO entity = recruitmentService.fetchResumeTop(id);
        return R.ok(entity);
    }

    /**
     * 简历详情-求职意向-详情
     * @param id
     * @return R
     */
    @ApiOperation(value = "简历详情-求职意向-详情", notes = "简历详情-求职意向-详情向")
    @GetMapping("/fetchResumeJobIntent")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public R<RecruitmentJobIntentResultDTO> fetchResumeJobIntent(Long id) {
        RecruitmentJobIntentDTO intent = recruitmentService.fetchResumeJobIntent(id);
        RecruitmentJobIntentResultDTO result=new RecruitmentJobIntentResultDTO();
        result.setRecruitmentJobIntentDTO(intent);
        return R.ok(result);
    }

    /**
     * 简历详情-求职意向-修改更新
     * @param dto 人才简历-求职意向
     * @return R
     */
    @ApiOperation(value = "简历详情-求职意向-修改更新", notes = "简历详情-求职意向-修改更新")
    @PostMapping("/updateRecruitmentJobIntent")
    public R<RecruitmentJobIntentDTO> updateRecruitmentJobIntent(@RequestBody RecruitmentJobIntentDTO dto) {
        RecruitmentJobIntentDTO result = recruitmentService.updateRecruitmentJobIntent(dto);
        return R.ok(result);
    }

    /**
     * 获取人才管理操作日志
     *
     * @param page         分页对象
     * @param operationLog 操作记录
     * @param searchText   关键字搜索
     * @return
     */
    @ApiOperation(value = "获取人才管理操作日志")
    @GetMapping("/getOperateLogPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页对象"),
            @ApiImplicitParam(name = "operationLog", value = "操作记录"),
            @ApiImplicitParam(name = "searchText", value = "关键字搜索"),
    })
    public R getOperateLogPage(Page page, OperationLog operationLog, String searchText) {
        Page<OperationLog> pageResult = operationLogService.findOperationLog(page, operationLog, searchText);
        return R.ok(pageResult);
    }

    /**
     * 人才管理-导出Excel
     * @param response
     * @param orgId
     * @param searchText
     * @return
     */
    @ApiOperation(value = "人才管理-导出Excel", notes = "人才管理-导出Excel")
    @GetMapping("/exportRecruitment")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orgId", value = "组织ID"),
        @ApiImplicitParam(name = "searchText", value = "关键字搜索"),
    })
    @SneakyThrows
    public R<RecruitmentDTO> exportRecruitment(HttpServletResponse response, String orgId, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<RecruitmentDTO> pageResult = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        ExcelUtils.export2Web(response, "人才管理表", "人才管理表", RecruitmentDTO.class, pageResult.getRecords());
        RecruitmentDTO dto = new RecruitmentDTO();
        return R.ok(dto);
    }

    /**
     * 人才管理-邀请更新简历信息-导出Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "人才管理-邀请更新简历信息-导出Excel", notes = "人才管理-邀请更新简历信息-导出Excel")
    @GetMapping("/exportInviteRecruitment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "组织ID"),
            @ApiImplicitParam(name = "searchText", value = "关键字搜索"),
    })
    @SneakyThrows
    public R<RecruitmentDTO> exportInviteRecruitment(HttpServletResponse response, String orgId, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<RecruitmentDTO> pageResult = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        ExcelUtils.export2Web(response, "人才管理表", "人才管理表", RecruitmentDTO.class, pageResult.getRecords());
        RecruitmentDTO dto = new RecruitmentDTO();
        return R.ok(dto);
    }

    /**
     * 人才下拉框-前端调用
     *
     * @param searchText 关键字
     * @return
     */
    @ApiOperation(value = "人才下拉框-前端调用", notes = "人才下拉框-前端调用")
    @GetMapping("/findRecruitmentList")
    @ApiImplicitParam(name = "searchText", value = "关键字")
    public R<List<Recruitment>> findRecruitmentList(String searchText) {
        Recruitment recruitment = new Recruitment();
        QueryWrapper<Recruitment> wrapper = Wrappers.query(recruitment);

        if (ObjectUtil.isNotEmpty(searchText)) {
            wrapper.like("CONCAT(talent_name,mobile)", searchText);
        }

        List<Recruitment> list = recruitmentService.list(wrapper);
        return R.ok(list);
    }

    /**
     * 人才简历-录用情况-列表分页
     *
     * @param recruitmentId 人才ID
     * @return R
     */
    @ApiOperation(value = "人才简历-录用情况-列表分页", notes = "人才简历-录用情况-列表分页")
    @GetMapping("/fetchEmploy")
    @ApiImplicitParam(name = "recruitmentId", value = "人才ID")
    public R<RecruitmentEmployeeDTO> fetchEmploy(String recruitmentId) {
        RecruitmentEmployeeDTO entity = recruitmentService.fetchEmploy(recruitmentId);
        return R.ok(entity);
    }

    /**
     * 人才管理-人才简历
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才管理-人才简历", notes = "人才管理-人才简历")
    @GetMapping("/fetchResumeDetails")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentDetailsDTO> fetchResumeDetails(Long id) {
        RecruitmentDetailsDTO result = new RecruitmentDetailsDTO();

        RecruitmentPersonDTO personDTO = recruitmentService.fetchRecruitmentPerson(id);
        RecruitmentIntentDTO intentDTO = recruitmentService.fetchRecruitmentIntent(id);
        List<RecruitmentWorkExperienceDTO> workList = recruitmentWorkexperienceService.findWorkExperienceList(id);

        //获取最高学历
        RecruitmentTopEduDTO topEduDTO = recruitmentService.fetchRecruitmentTopEdu(id);

        List<RecruitmentFamilyDTO> familyDTOList = recruitmentFamilyStatusService.fetchResumeFamilyList(id);
        List<RecruitmentAwardsDTO> recruitmentAwardsList = recruitmentAwardsService.fetchResumeAwardsList(id);

        result.setRecruitmentPersonDTO(personDTO);
        result.setRecruitmentIntentDTO(intentDTO);
        result.setRecruitmentWorkexperienceDTO(workList);
        result.setRecruitmentTopEduDTO(topEduDTO);
        result.setRecruitmentFamilyDTO(familyDTOList);
        result.setRecruitmentAwardsDTO(recruitmentAwardsList);
        return R.ok(result);
    }

    /**
     * 候选人H5手机验证码登录
     * @param mobile 手机号码
     * @param code
     * @return
     */
    @ApiOperation(value = "候选人H5手机验证码登录", notes = "候选人H5手机验证码登录")
    @GetMapping("/recruitmentLogin")
    public R<Recruitment> recruitmentLogin(String mobile, String code){
        return recruitmentService.recruitmentLogin(mobile, code);
    }

    /**
     * 简历详情-基础信息-个人基本信息
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "简历详情-基础信息-个人基本信息", notes = "简历详情-基础信息-个人基本信息")
    @GetMapping("/fetchResumeEditDetails")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentEditDetailsDTO> fetchResumeEditDetails(Long id) {
        RecruitmentEditDetailsDTO result=new RecruitmentEditDetailsDTO();

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
        RecruitmentOtherInfo otherInfo = recruitmentService.fetchRecruitmentOtherInfo(id);
        if (ObjectUtil.isNotNull(otherInfo)){
            RecruitmentEditOtherInfoDTO editOtherInfo=new RecruitmentEditOtherInfoDTO();
            BeanUtils.copyProperties(otherInfo,editOtherInfo);
            result.setRecruitmentEditOtherInfoDTO(editOtherInfo);
        }

        //个人基本信息
        RecruitmentBaseInfo baseInfo = recruitmentService.fetchRecruitmentBaseInfo(id);
        if (ObjectUtil.isNotNull(baseInfo)){
            RecruitmentEditBaseInfoDTO editBaseInfo=new RecruitmentEditBaseInfoDTO();
            BeanUtils.copyProperties(baseInfo,editBaseInfo);
            result.setRecruitmentEditBaseInfoDTO(editBaseInfo);
        }

        return R.ok(result);
    }

    /**
     * 简历详情-基础信息-个人基本信息-手机端
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "简历详情-基础信息-个人基本信息-手机端", notes = "简历详情-基础信息-个人基本信息-手机端")
    @GetMapping("/fetchResumeEditDetailsByMobile")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentEditDetailsDTO> fetchResumeEditDetailsByMobile(Long id) {
        RecruitmentEditDetailsDTO result=new RecruitmentEditDetailsDTO();

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
        RecruitmentOtherInfo otherInfo = recruitmentService.fetchRecruitmentOtherInfo(id);
        if (ObjectUtil.isNotNull(otherInfo)){
            RecruitmentEditOtherInfoDTO editOtherInfo=new RecruitmentEditOtherInfoDTO();
            BeanUtils.copyProperties(otherInfo,editOtherInfo);
            result.setRecruitmentEditOtherInfoDTO(editOtherInfo);
        }

        //个人基本信息
        RecruitmentBaseInfo baseInfo = recruitmentService.fetchRecruitmentBaseInfo(id);
        if (ObjectUtil.isNotNull(baseInfo)){
            RecruitmentEditBaseInfoDTO editBaseInfo=new RecruitmentEditBaseInfoDTO();
            BeanUtils.copyProperties(baseInfo,editBaseInfo);
            result.setRecruitmentEditBaseInfoDTO(editBaseInfo);
        }

        return R.ok(result);
    }

    /**
     * 简历详情-个人基本情况-修改更新
     * @param baseInfo 个人基本情况
     * @return R
     */
    @ApiOperation(value = "简历详情-个人基本情况-修改更新", notes = "简历详情-个人基本情况-修改更新")
    @PostMapping("/updateBaseInfo")
    public R<RecruitmentEditBaseInfoDTO> updateBaseInfo(@RequestBody RecruitmentEditBaseInfoDTO baseInfo) {
        RecruitmentEditBaseInfoDTO result = recruitmentService.updateBaseInfo(baseInfo);
        return R.ok(result,"修改更新成功");
    }

    /**
     * 简历详情-其他个人信息-修改更新
     * @param otherInfo 人才简历-其他个人信息
     * @return R
     */
    @ApiOperation(value = "简历详情-其他个人信息-修改更新", notes = "简历详情-其他个人信息-修改更新")
    @PostMapping("/updateOtherInfo")
    public R<RecruitmentEditOtherInfoDTO> updateOtherInfo(@RequestBody RecruitmentEditOtherInfoDTO otherInfo) {
        RecruitmentEditOtherInfoDTO result = recruitmentService.updateOtherInfo(otherInfo);
        return R.ok(result,"修改更新成功");
    }

    @ApiOperation(value = "手机验证码", notes = "手机验证码")
    @GetMapping("/sendSmsCode/{mobile}")
    @ApiImplicitParam(name = "mobile", value = "手机号码")
    public R<Boolean> sendSmsCode(@PathVariable String mobile) {
        return recruitmentService.sendSmsCode(mobile);
    }

    /**
     * 简历详情-工作情况-手机端
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况-手机端", notes = "简历详情-工作情况-手机端")
    @GetMapping("/fetchResumeWorkDetailsByMobile")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentWorkDetailsDTO> fetchResumeWorkDetailsByMobile(Long id) {
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

        return R.ok(result);
    }

    /**
     * 简历详情-工作情况
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况", notes = "简历详情-工作情况")
    @GetMapping("/fetchResumeWorkDetails")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentWorkDetailsDTO> fetchResumeWorkDetails(Long id) {
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

        return R.ok(result);
    }

    /**
     * 	发起流程
     */
    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/generateWorkflow")
    public R generateWorkflow(@RequestBody GenerateWorkflowDTO dto) {
    	return recruitmentService.generateWorkflow(dto.getRecordId(),dto.getFlowType(),dto.getContentUrl());
    }
    
    
    /**
     * 	流程回调 更新业务表单的状态 
     */
    @Inner(false)
    @ApiOperation(value = "流程回调 更新业务表单的状态 ", notes = "流程回调 更新业务表单的状态 ")
    @PostMapping("/workflowNotify")
    public R workflowNotify(@RequestBody WorkflowNotifyDTO dto) {
    	
    	//TODO 根据流程类型找到 对应的 需要修改的表单
    	//TODO 更新表单的状态 
    	String flowType = dto.getFlowType();
    	if("jy201606".equals(flowType)) {
    		staffEntrypostApproveService.modifyStatus(dto.getRecordId(), dto.getStatus());
    		return R.ok();
    	}
    	
    	return R.failed("没有找到相关编码");
    }

    /**
     * 获取-批量邀请更新简历页面-二维码
     * @return R
     */
    @ApiOperation(value = "获取-批量邀请更新简历页面-二维码", notes = "获取-批量邀请更新简历页面-二维码")
    @GetMapping("/fetchBatchInviteResumeQrCode")
    public R fetchBatchInviteResumeQrCode() {
        Integer tenantId = SecurityUtils.getUser().getTenantId();
        String result="";
        if (ObjectUtil.isNotNull(tenantId)){
            //手机端极速入职页面地址
            String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
            result = QrCodeUtils.createBase64QrCode(address);
        }

        return R.ok(result);
    }

    /**
     * 下载-批量邀请更新简历页面-二维码
     * @return R
     */
    @ApiOperation(value = "下载-批量邀请更新简历页面-二维码", notes = "下载-批量邀请更新简历页面-二维码")
    @GetMapping("/downloadInviteResumeQrCode")
    public R downloadInviteResumeQrCode(HttpServletResponse response) {
        QrCodeUtils.downloadQrCode(response);
        return R.ok("下载二维码成功！");
    }



    /**
     * 获取-入职登记记录-发送邀请确认-页面内容（内含二维码）
     * @return R
     */
    @ApiOperation(value = "获取-入职登记记录-发送邀请确认-页面内容", notes = "获取-入职登记记录-发送邀请确认-页面内容")
    @GetMapping("/fetchRegisterEmail")
    public R<ModuleVO> fetchConfirmInviteResumeEmail() {
        ModuleVO moduleVO=new ModuleVO();
        Integer tenantId = SecurityUtils.getUser().getTenantId();
        if (ObjectUtil.isNotNull(tenantId)){
            //手机端极速入职页面地址
            String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
            String code = QrCodeUtils.createBase64QrCode(address);
            moduleVO.setCode(code);
        }

        //todo:调用系统模板接口，获取模板配置信息。

        return R.ok(moduleVO);
    }

    /**
     * 获取-候选人简历补充邀请确认-页面内容（内含二维码）
     * @return R
     */
    @ApiOperation(value = "获取-候选人简历补充邀请确认-页面内容", notes = "获取-候选人简历补充邀请确认-页面内容")
    @GetMapping("/fetchCandidate")
    public R<ModuleVO> fetchConfirmSupplementResumeEmail() {
        ModuleVO moduleVO=new ModuleVO();
        Integer tenantId = SecurityUtils.getUser().getTenantId();
        if (ObjectUtil.isNotNull(tenantId)){
            //手机端极速入职页面地址
            String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
            String code = QrCodeUtils.createBase64QrCode(address);
            moduleVO.setCode(code);
        }

        //todo:调用系统模板接口，获取模板配置信息。
        return R.ok(moduleVO);
    }

    /**
     * 手机端-基本信息-个人信息-新增或修改
     * @param dto 人才表
     * @return R
     */
    @ApiOperation(value = "手机端-基本信息-个人信息-新增或修改", notes = "手机端-基本信息-个人信息-新增或修改")
    @PostMapping("/saveOrUpdateRecruitment")
    public R saveOrUpdateRecruitment(@Validated @RequestBody RecruitmentBaseInfoMobileDTO dto) {
        R result = recruitmentService.saveOrUpdateRecruitment(dto);
        return R.ok(result);
    }

    /**
     * 获取-手机端个人信息-填写进度
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "获取-手机端个人信息-填写进度", notes = "获取-手机端个人信息-填写进度")
    @GetMapping("/fetchMobileInfoProgress")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentMobileProgressVO> fetchMobileInfoProgress(Long id)  {
        RecruitmentMobileProgressVO reslult = recruitmentService.fetchMobileInfoProgress(id);
        return R.ok(reslult);
    }



}
