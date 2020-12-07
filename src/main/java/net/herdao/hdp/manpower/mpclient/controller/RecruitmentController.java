package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.annotation.Inner;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.*;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    private final OperationLogService operationLogService;

    private final RecruitmentWorkexperienceService recruitmentWorkexperienceService;

    private final RecruitmentFamilyStatusService recruitmentFamilyStatusService;

    private final RecruitmentAwardsService recruitmentAwardsService;

    private final RecruitmentEducationService recruitmentEducationService;

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
        return R.ok(formVO);
    }

     /**
     * 修改更新人才表
     *
     * @param recruitmentUpdateFormVO 人才表
     * @return R
     */
    @ApiOperation(value = "快速编辑-修改更新", notes = "快速编辑-修改更新")
    @PostMapping("/updateRecruitment")
    public R<RecruitmentUpdateFormDTO> updateRecruitment(@Validated @RequestBody RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        R<RecruitmentUpdateFormDTO> result = recruitmentService.updateRecruitment(recruitmentUpdateFormVO);
        return result;
    }

    /**
     * 人才管理-添加候选人-新增保存
     *
     * @param recruitmentAddFormDTO 人才表
     * @return R
     */
    @ApiOperation(value = "人才管理-添加候选人-新增保存", notes = "人才管理-添加候选人-新增保存")
    @PostMapping("/saveRecruitment")
    public R<RecruitmentAddFormDTO> saveRecruitment(@Validated @RequestBody RecruitmentAddFormDTO recruitmentAddFormDTO) {
        R<RecruitmentAddFormDTO> result = recruitmentService.saveRecruitment(recruitmentAddFormDTO);
        return result;
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
     * 编辑获取个人简历-个人基本情况 其他个人信息
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "编辑-获取个人简历-个人基本情况 其他个人信息", notes = "编辑个人简历-个人基本情况 其他个人信息 最高教育经历")
    @GetMapping("/fetchDetailsById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    public R<RecruitmentDetailsDTO> fetchDetailsById(Long id) {
        RecruitmentDetailsDTO baseDTO = new RecruitmentDetailsDTO();
        Recruitment recruitment = recruitmentService.getById(id);
        BeanUtils.copyProperties(recruitment, baseDTO);
        return R.ok(baseDTO);
    }

    /**
     * 编辑更新个人简历-个人基本情况 其他个人信息
     *
     * @param baseDTO 人才简历-个人基本情况 其他个人信息 从业情况与求职意向
     * @return R
     */
    @ApiOperation(value = "编辑更新个人简历-个人基本情况 其他个人信息", notes = "编辑更新个人简历-个人基本情况 其他个人信息")
    @PostMapping("/updateBaseInfo")
    public R<RecruitmentDetailsDTO> updateBaseInfo(@RequestBody RecruitmentDetailsDTO baseDTO) {
        RecruitmentDetailsDTO result = recruitmentService.updateBaseInfo(baseDTO);
        return R.ok(result);
    }

    /**
     * 简历编辑-获取人才简历-求职意向
     * @param id
     * @return R
     */
    @ApiOperation(value = "简历编辑-获取人才简历-求职意向", notes = "简历编辑-获取人才简历-求职意向")
    @GetMapping("/fetchResumeJobIntent")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public R<RecruitmentJobIntentDTO> fetchResumeJobIntent(Long id) {
        RecruitmentJobIntentDTO result = recruitmentService.fetchResumeJobIntent(id);
        return R.ok(result);
    }

    /**
     * 简历编辑-求职意向-修改更新
     *
     * @param dto 人才简历-从业情况与求职意向
     * @return R
     */
    @ApiOperation(value = "简历编辑-求职意向-修改更新", notes = "简历编辑-求职意向-修改更新")
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
     * @return R
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
     * 人才简历-简历详情
     *
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-简历详情", notes = "人才简历-简历详情")
    @GetMapping("/fetchResumeDetails")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentDetailsDTO> fetchResumeDetails(Long id) {
        RecruitmentDetailsDTO result = new RecruitmentDetailsDTO();

        RecruitmentPersonDTO personDTO = recruitmentService.fetchRecruitmentPerson(id);
        RecruitmentIntentDTO intentDTO = recruitmentService.fetchRecruitmentIntent(id);
        RecruitmentWorkexperienceDTO workDTO = recruitmentWorkexperienceService.findWorkExperience(id);
        RecruitmentTopEduDTO topEduDTO = recruitmentService.fetchRecruitmentTopEdu(id);
        List<RecruitmentFamilyDTO> familyDTOList = recruitmentFamilyStatusService.fetchResumeFamily(id);
        List<RecruitmentAwardsDTO> recruitmentAwardsList = recruitmentAwardsService.fetchResumeAwardsList(id);

        result.setRecruitmentPersonDTO(personDTO);
        result.setRecruitmentIntentDTO(intentDTO);
        result.setRecruitmentWorkexperienceDTO(workDTO);
        result.setRecruitmentTopEduDTO(topEduDTO);
        result.setRecruitmentFamilyDTO(familyDTOList);
        result.setRecruitmentAwardsDTO(recruitmentAwardsList);
        return R.ok(result);
    }

    /**
     * 候选人H5手机验证码登录
     * @param mobile
     * @param code
     * @return
     */
    @ApiOperation(value = "候选人H5手机验证码登录", notes = "候选人H5手机验证码登录")
    @Inner(value = false)
    @GetMapping("/recruitmentLogin")
    public R<Long> recruitmentLogin(String mobile, String code){
        return recruitmentService.recruitmentLogin(mobile, code);
    }

    /**
     * 编辑人才简历-基础信息-详情
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "编辑人才简历-基础信息-详情", notes = "编辑人才简历-基础信息-详情")
    @GetMapping("/fetchResumeEditDetails")
    @ApiImplicitParam(name = "id", value = "主键id")
    public R<RecruitmentEditDetailsDTO> fetchResumeEditDetails(Long id) {
        RecruitmentEditDetailsDTO result=new RecruitmentEditDetailsDTO();

        //获奖情况
        List<RecruitmentAwardsDTO> recruitmentAwardsList = recruitmentAwardsService.fetchResumeAwardsList(id);
        RecruitmentEditAwardsDTO editAwardsDTO=new RecruitmentEditAwardsDTO();
        List<RecruitmentEditAwardsDTO> editAwardsList=new ArrayList<RecruitmentEditAwardsDTO>();
        if (ObjectUtil.isNotEmpty(recruitmentAwardsList)){
            recruitmentAwardsList.forEach(e->{
                BeanUtils.copyProperties(e,editAwardsDTO);
                editAwardsList.add(editAwardsDTO);
            });
        }
        result.setRecruitmentEditAwardsDTO(editAwardsList);

        //教育经历
        List<RecruitmentEduDTO> recruitmentEduList = recruitmentEducationService.fetchResumeEduList(id);
        RecruitmentEditEduDTO editEduDTO=new RecruitmentEditEduDTO();
        List<RecruitmentEditEduDTO> editEduList=new ArrayList<RecruitmentEditEduDTO>();
        if (ObjectUtil.isNotEmpty(recruitmentEduList)){
            recruitmentEduList.forEach(e->{
                BeanUtils.copyProperties(e,editEduDTO);
                editEduList.add(editEduDTO);
            });
        }
        result.setRecruitmentEditEduDTO(editEduList);

        //家庭状况
        List<RecruitmentFamilyDTO> familyList = recruitmentFamilyStatusService.fetchResumeFamily(id);
        RecruitmentEditFamilyDTO editFamilyDTO=new RecruitmentEditFamilyDTO();
        List<RecruitmentEditFamilyDTO> editFamilyList=new ArrayList<RecruitmentEditFamilyDTO>();
        if (ObjectUtil.isNotEmpty(familyList)){
            familyList.forEach(e->{
                BeanUtils.copyProperties(e,editFamilyDTO);
                editFamilyList.add(editFamilyDTO);
            });
        }
        result.setRecruitmentEditFamilyDTO(editFamilyList);

        //其他个人信息
        RecruitmentOtherInfo otherInfoList = recruitmentService.fetchRecruitmentOtherInfo(id);
        RecruitmentEditOtherInfo editOtherInfo=new RecruitmentEditOtherInfo();
        BeanUtils.copyProperties(otherInfoList,editOtherInfo);
        result.setRecruitmentEditOtherInfo(editOtherInfo);

        //个人基本信息
        RecruitmentBaseInfo baseInfo = recruitmentService.fetchRecruitmentBaseInfo(id);
        RecruitmentEditBaseInfo editBaseInfo=new RecruitmentEditBaseInfo();
        BeanUtils.copyProperties(baseInfo,editBaseInfo);
        result.setRecruitmentEditBaseInfo(editBaseInfo);

        return R.ok(result);
    }

}
