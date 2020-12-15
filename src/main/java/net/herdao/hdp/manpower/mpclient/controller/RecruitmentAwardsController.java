
package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentAwards;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentAwardsService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 人才获奖情况表
 *
 * @author Andy
 * @date 2020-11-26 18:51:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentawards")
@Api(value = "recruitmentawards", tags = "简历详情-人才获奖情况表管理")
public class RecruitmentAwardsController {

    private final RecruitmentAwardsService recruitmentAwardsService;

    /**
     * 简历详情-获奖情况-获取详情
     * @param id id
     * @return R
     */
    @ApiOperation(value = "简历详情-获奖情况-获取详情", notes = "简历详情-获奖情况-获取详情")
    @GetMapping("/fetchRecruitmentAwardsById")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键id")
    })
    public R<RecruitmentAwardsDTO> fetchRecruitmentAwardsById(Long id) {
        RecruitmentAwards awards = recruitmentAwardsService.getById(id);
        RecruitmentAwardsDTO dto = new RecruitmentAwardsDTO();
        if (ObjectUtil.isNotNull(awards)) {
            BeanUtils.copyProperties(awards, dto);
        }

        return R.ok(dto);
    }

    /**
     * 编辑人才简历-获奖情况-新增保存
     *
     * @param dto 人才获奖情况表
     * @return R
     */
    @ApiOperation(value = "编辑人才简历-获奖情况-新增保存", notes = "编辑人才简历-获奖情况-新增保存")
    @PostMapping("/saveAwards")
    public R<RecruitmentAwardsDTO> saveAwards(@RequestBody RecruitmentAwardsDTO dto) {
        RecruitmentAwardsDTO result = recruitmentAwardsService.saveOrUpdate(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-获奖情况-修改更新
     *
     * @param dto 人才获奖情况表
     * @return R
     */
    @ApiOperation(value = "人才简历-获奖情况-修改更新", notes = "人才简历-获奖情况-修改更新")
    @PostMapping("/updateAwards")
    public R<RecruitmentAwardsDTO> updateAwards(@RequestBody RecruitmentAwardsDTO dto) {
        RecruitmentAwardsDTO result = recruitmentAwardsService.saveOrUpdate(dto);
        return R.ok(result);
    }

    /**
     * 简历详情-获奖情况-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "简历详情-获奖情况-删除", notes = "简历详情-获奖情况-删除")
    @DeleteMapping("/del/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentAwardsService.removeById(id));
    }



}
