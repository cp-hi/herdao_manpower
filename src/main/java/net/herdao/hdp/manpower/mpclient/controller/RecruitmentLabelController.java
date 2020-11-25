package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentLabel;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentLabelService;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentLabelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 人才标签
 *
 * @author Andy
 * @date 2020-11-25 14:40:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentlabel" )
@Api(value = "recruitmentlabel", tags = "人才标签管理")
public class RecruitmentLabelController {

    private final  RecruitmentLabelService recruitmentLabelService;

    /**
     * 人才简历-删除人才标签
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-删除人才标签", notes = "人才简历-删除人才标签")
    @SysLog("人才简历-删除人才标签" )
    @DeleteMapping("/del/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentLabelService.removeById(id));
    }

    /**
     * 人才简历-新增人才标签
     * @param labelVO 人才标签VO
     * @return R
     */
    @ApiOperation(value = "人才简历-新增人才标签", notes = "人才简历-新增人才标签")
    @SysLog("人才简历-新增人才标签" )
    @PostMapping("/save" )
    public R<RecruitmentLabelVO> save(@Valid @RequestBody RecruitmentLabelVO labelVO) {
        RecruitmentLabel label=new RecruitmentLabel();
        BeanUtils.copyProperties(labelVO,label);
        recruitmentLabelService.save(label);
        BeanUtils.copyProperties(label,labelVO);
        return R.ok(labelVO);
    }
}
