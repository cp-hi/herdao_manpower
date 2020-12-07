
package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEduDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentEducation;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentEducationService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 人才教育情况
 *
 * @author Andy
 * @date 2020-11-27 08:57:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmenteducation" )
@Api(value = "recruitmenteducation", tags = "人才教育情况管理")
public class RecruitmentEducationController {

    private final  RecruitmentEducationService recruitmentEducationService;

    /**
     * 人才简历-教育情况-编辑详情
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-教育情况-编辑详情", notes = "人才简历-教育情况-编辑详情")
    @GetMapping("/fetchDetails/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID")
    })
    public R<RecruitmentEduDTO> getById(@PathVariable("id" ) Long id) {
        RecruitmentEducation education = recruitmentEducationService.getById(id);
        RecruitmentEduDTO dto=new RecruitmentEduDTO();
        BeanUtils.copyProperties(education,dto);
        return R.ok(dto);
    }

    /**
     * 人才简历-教育情况-删除
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "人才简历-教育情况-删除", notes = "人才简历-教育情况-删除")
    @DeleteMapping("/del/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID")
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentEducationService.removeById(id));
    }

    /**
     * 人才简历-教育情况-列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    @ApiOperation(value = "人才简历-教育情况-列表", notes = "人才简历-教育情况-列表")
    @GetMapping("/page" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="recruitmentId",value="人才ID")
    })
    public R<Page<RecruitmentEduDTO>> fetchResumeEduPage(Page page, Long recruitmentId) {
        Page<RecruitmentEduDTO> pageResult = recruitmentEducationService.fetchResumeEduPage(page, recruitmentId);
        return R.ok(pageResult);
    }

    /**
     * 人才简历-教育情况-修改更新
     * @param dto 人才教育情况
     * @return R
     */
    @ApiOperation(value = "人才简历-教育情况-修改更新", notes = "人才简历-教育情况-修改更新")
    @PostMapping("/updateEdu")
    public R<RecruitmentEduDTO> updateEdu(@RequestBody RecruitmentEduDTO dto) {
        RecruitmentEduDTO result = recruitmentEducationService.saveOrUpdate(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-教育情况-新增
     * @param dto 人才教育情况
     * @return R
     */
    @ApiOperation(value = "人才简历-教育情况-新增", notes = "人才简历-教育情况-新增")
    @PostMapping("/saveEdu")
    public R<RecruitmentEduDTO> saveEdu(@RequestBody RecruitmentEduDTO dto) {
        RecruitmentEduDTO result = recruitmentEducationService.saveOrUpdate(dto);
        return R.ok(result);
    }


}
