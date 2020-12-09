package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentActiviti;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTrain;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTrainService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 人才培训经历表
 *
 * @author Andy
 * @date 2020-12-02 20:12:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentTrain" )
@Api(value = "recruitmentTrain", tags = "简历库-人才培训经历表管理")
public class RecruitmentTrainController {

    private final  RecruitmentTrainService recruitmentTrainService;

    /**
     * 人才培训经历-详情
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才培训经历-详情", notes = "人才培训经历-详情")
    @GetMapping("/fetchRecruitmentTrainById" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID",required = true),
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_view')" )
    public R<RecruitmentTrain> fetchRecruitmentTrainById(Long id) {
        RecruitmentTrain recruitmentTrain = recruitmentTrainService.getById(id);
        return R.ok(recruitmentTrain);
    }

    /**
     * 人才培训经历-新增保存
     * @param recruitmentTrain 人才培训经历表
     * @return R
     */
    @ApiOperation(value = "人才培训经历-新增保存", notes = "人才培训经历-新增保存")
    @PostMapping("/saveTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_add')" )
    public R<RecruitmentTrain> saveTrain(@RequestBody RecruitmentTrain recruitmentTrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTrain.setCreatorTime(LocalDateTime.now());
        recruitmentTrain.setCreatorCode(sysUser.getUsername());
        recruitmentTrain.setCreatorName(sysUser.getAliasName());
        recruitmentTrainService.save(recruitmentTrain);
        return R.ok(recruitmentTrain);
    }

    /**
     * 人才培训经历-修改更新
     * @param recruitmentTrain 人才培训经历表
     * @return R
     */
    @ApiOperation(value = "人才培训经历-修改更新", notes = "人才培训经历-修改更新")
    @PostMapping("/updateTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_edit')" )
    public R<RecruitmentTrain> updateTrain(@RequestBody RecruitmentTrain recruitmentTrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTrain.setModifierTime(LocalDateTime.now());
        recruitmentTrain.setModifierCode(sysUser.getUsername());
        recruitmentTrain.setModifierName(sysUser.getAliasName());
        recruitmentTrainService.updateById(recruitmentTrain);
        return R.ok();
    }

    /**
     * 人才培训经历-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才培训经历-删除", notes = "人才培训经历-删除")
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentTrainService.removeById(id));
    }

}
