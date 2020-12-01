package net.herdao.hdp.manpower.mpclient.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitment" )
@Api(value = "recruitment", tags = "人才表管理")
public class RecruitmentController  {

    private final RecruitmentService recruitmentService;

    private final OperationLogService operationLogService;

    /**
     * 快速编辑
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "快速编辑", notes = "快速编辑")
    @GetMapping("/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_view')" )
    public R<RecruitmentUpdateFormDTO> getById(@PathVariable("id" ) Long id) {
        Recruitment recruitment = recruitmentService.getById(id);
        RecruitmentUpdateFormDTO formVO=new RecruitmentUpdateFormDTO();
        BeanUtils.copyProperties(recruitment,formVO);
        return R.ok(formVO);
    }

    /**
     * 修改更新人才表
     * @param recruitmentUpdateFormVO 人才表
     * @return R
     */
    @ApiOperation(value = "快速编辑-保存", notes = "快速编辑-保存")
    @PostMapping("/updateRecruitment")
    public R<RecruitmentUpdateFormDTO> update(@Validated @RequestBody RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        R<RecruitmentUpdateFormDTO> result = recruitmentService.updateRecruitment(recruitmentUpdateFormVO);
        return result;
    }

    /**
     * 新增候选人-保存
     * @param recruitmentAddFormDTO 人才表
     * @return R
     */
    @ApiOperation(value = "新增候选人-保存", notes = "新增候选人-保存")
    @PostMapping("/saveRecruitment")
    public R<RecruitmentAddFormDTO> saveRecruitment(@Validated @RequestBody RecruitmentAddFormDTO recruitmentAddFormDTO) {
        R<RecruitmentAddFormDTO> result = recruitmentService.saveRecruitment(recruitmentAddFormDTO);
        return result;
    }

    /**
     * 人才库列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "人才库列表;批量邀请更新简历信息", notes = "人才库列表;批量邀请更新简历信息")
    @GetMapping("/findRecruitmentPage")
    @ApiImplicitParams({
         @ApiImplicitParam(name="page",value="分页对象",required = true),
         @ApiImplicitParam(name="orgId",value="组织ID"),
         @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R<Page<RecruitmentDTO>> findRecruitmentPage(Page page, String orgId, String searchText) {
        Page<RecruitmentDTO> recruitmentPage = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        return R.ok(recruitmentPage);
    }

    /**
     * 删除候选人
     * @param id id
     * @return R
     */
    @ApiOperation(value = "删除候选人", notes = "删除候选人")
    @DeleteMapping("/{id}" )
    @ApiImplicitParam(name = "id",value = "主键ID",required = true)
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentService.removeById(id));
    }

    /**
     * 人才简历-顶部
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-顶部", notes = "人才简历-顶部")
    @GetMapping("/fetchResumeTop" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    public R<RecruitmentUpdateFormDTO> fetchResumeTop(Long id) {
        RecruitmentUpdateFormDTO entity = recruitmentService.fetchResumeTop(id);
        return R.ok(entity);
    }

    /**
     * 获取人才简历-个人基本情况 从业情况与求职意向 最高教育经历
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "获取人才简历-个人基本情况 从业情况与求职意向 最高教育经历", notes = "人才简历-个人基本情况 从业情况与求职意向 最高教育经历")
    @GetMapping("/fetchResumeBaseSituation" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    public R<RecruitmentBaseDTO> fetchResumeBaseSituation(Long id) {
        RecruitmentBaseDTO entity = recruitmentService.fetchResumeBaseSituation(id);
        return R.ok(entity);
    }

    /**
     * 编辑个人简历-个人基本情况 其他个人信息
     * @param id id
     * @return R
     */
    @ApiOperation(value = "编辑个人简历-个人基本情况 其他个人信息", notes = "编辑个人简历-个人基本情况 其他个人信息 最高教育经历")
    @GetMapping("/fetchDetails/{id}" )
    public R<RecruitmentBaseDTO> fetchDetails(@PathVariable("id" ) Long id) {
        RecruitmentBaseDTO baseDTO=new RecruitmentBaseDTO();
        Recruitment recruitment = recruitmentService.getById(id);
        BeanUtils.copyProperties(recruitment,baseDTO);
        return R.ok(baseDTO);
    }

    /**
     * 编辑更新个人简历-个人基本情况 其他个人信息
     * @param baseDTO 人才简历-个人基本情况 其他个人信息 从业情况与求职意向
     * @return R
     */
    @ApiOperation(value = "编辑更新个人简历-个人基本情况 其他个人信息", notes = "编辑更新个人简历-个人基本情况 其他个人信息")
    @PostMapping("/updateBaseInfo" )
    public R<RecruitmentBaseDTO> updateBaseInfo(@RequestBody RecruitmentBaseDTO baseDTO) {
        RecruitmentBaseDTO result = recruitmentService.saveOrUpdate(baseDTO);
        return R.ok(result);
    }

    /**
     * 人才简历-从业情况与求职意向
     * @param id
     * @return R
     */
    @ApiOperation(value = "人才简历-从业情况与求职意向", notes = "人才简历-从业情况与求职意向")
    @GetMapping("/fetchResumeJob" )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true)
    })
    public R<RecruitmentJobDTO> fetchResumeJob(Long id) {
        RecruitmentJobDTO result = recruitmentService.fetchResumeJob(id);
        return R.ok(result);
    }

    /**
     * 人才简历-从业情况与求职意向-修改更新
     * @param dto 人才简历-从业情况与求职意向
     * @return R
     */
    @ApiOperation(value = "人才简历-从业情况与求职意向-修改更新", notes = "人才简历-从业情况与求职意向-修改更新")
    @PostMapping("/updateRecruitmentJob" )
    public R<RecruitmentJobDTO> updateRecruitmentJob(@RequestBody RecruitmentJobDTO dto) {
        RecruitmentJobDTO result = recruitmentService.updateRecruitmentJob(dto);
        return R.ok(result);
    }

    /**
     * 获取人才管理操作日志
     * @param page 分页对象
     * @param operationLog 操作记录
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "获取人才管理操作日志")
    @GetMapping("/getOperateLogPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="分页对象"),
            @ApiImplicitParam(name="operationLog",value="操作记录"),
            @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R getOperateLogPage(Page page, OperationLog operationLog, String searchText) {
        Page<OperationLog> pageResult = operationLogService.findOperationLog(page,operationLog,searchText);
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
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    @SneakyThrows
    public void exportRecruitment(HttpServletResponse response, String orgId, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<RecruitmentDTO> pageResult = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        ExcelUtils.export2Web(response, "人才管理表", "人才管理表", StafftrainDTO.class, pageResult.getRecords());
    }

}
