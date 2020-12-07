

package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentWorkexperienceService;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentworkexperience")
@Api(value = "recruitmentworkexperience", tags = "人才工作情况表管理")
public class RecruitmentWorkexperienceController {

    private final RecruitmentWorkexperienceService recruitmentWorkexperienceService;

    /**
     * 简历详情-工作情况-工作经历-编辑-获取详情
     * @param id id
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况-工作经历-编辑-获取详情", notes = "简历详情-工作情况-工作经历-编辑-获取详情")
    @GetMapping("/fetchWorkExperienceById")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public R<RecruitmentWorkExperienceDTO> fetchWorkExperienceById(Long id) {
        RecruitmentWorkexperience result = recruitmentWorkexperienceService.getById(id);
        RecruitmentWorkExperienceDTO dto = new RecruitmentWorkExperienceDTO();
        BeanUtils.copyProperties(result, dto);
        return R.ok(dto);
    }

    /**
     * 简历详情-工作情况-工作经历-新增保存
     * @param dto 人才工作经历表
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况-工作经历-新增保存", notes = "简历详情-工作情况-工作经历-新增保存")
    @PostMapping("/saveWorkExperience")
    public R<RecruitmentWorkExperienceDTO> saveWorkExperience(@RequestBody RecruitmentWorkExperienceDTO dto) {
        RecruitmentWorkExperienceDTO result = recruitmentWorkexperienceService.saveWorkExperience(dto);
        return R.ok(result);
    }

    /**
     * 简历详情-工作情况-工作经历-修改更新
     * @param dto 人才工作经历表
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况-工作经历-修改更新", notes = "简历详情-工作情况-工作经历-修改更新")
    @PostMapping("/updateWorkExperience")
    public R<RecruitmentWorkExperienceDTO> updateWorkExperience(@RequestBody RecruitmentWorkExperienceDTO dto) {
        RecruitmentWorkExperienceDTO result = recruitmentWorkexperienceService.updateWorkExperience(dto);
        return R.ok(result);
    }

    /**
     * 简历详情-工作情况-工作经历-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "简历详情-工作情况-工作经历-删除", notes = "简历详情-工作情况-工作经历-删除")
    @DeleteMapping("/del/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentWorkexperienceService.removeById(id));
    }



}
