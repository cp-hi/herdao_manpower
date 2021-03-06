package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveUpdateDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffentrypostapprove" )
@Api(value = "staffentrypostapprove", tags = "录用审批表管理")
public class StaffEntrypostApproveController {

    private final StaffEntrypostApproveService staffEntrypostApproveService;

    /**
     * 录用审批-发起录用-保存
     * @param dto 录用审批表
     * @return R
     */
    @ApiOperation(value = "录用审批-发起录用-保存", notes = "录用审批-发起录用-保存")
    @PostMapping("/saveApprove")
    public R<EntryApproveAddDTO> saveApprove(@RequestBody EntryApproveAddDTO dto) {
        EntryApproveAddDTO result = staffEntrypostApproveService.saveApprove(dto);
        return R.ok(result,"新增保存成功");
    }

    /**
     * 录用审批-删除
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "录用审批-删除", notes = "录用审批-删除")
    @DeleteMapping("/del/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffEntrypostApproveService.removeById(id));
    }

    /**
     * 录用审批-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    @ApiOperation(value = "录用审批-列表")
    @GetMapping("/findApprovePage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
        @ApiImplicitParam(name="status",value="状态：1 填报中，2 审批中，3 已审批",required = true),
    })
    public R<Page<EntryApproveDTO>> findApprovePage(Page<EntryApproveDTO> page, String orgId, String searchText,String status) {
        Page<EntryApproveDTO> pageResult = staffEntrypostApproveService.findApprovePage(page,orgId,searchText,status);
        return R.ok(pageResult);
    }

    /**
     * 录用审批-导出
     * @param response
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     */
    @ApiOperation(value = "录用审批-导出", notes = "录用审批-导出")
    @GetMapping("/exportApprove")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
        @ApiImplicitParam(name="status",value="状态：1 填报中，2 审批中，3 已审批"),
    })
    @SneakyThrows
    public R exportApprove(HttpServletResponse response, String orgId, String searchText, String status) {
        List<EntryApproveDTO> list = staffEntrypostApproveService.exportApprove(orgId, searchText, status);
        ExcelUtils.export2Web(response, "录用审批表", "录用审批表", EntryApproveDTO.class, list);
        return R.ok("导出成功");
    }

    /**
     * 录用审批-流程审批-详情
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "录用审批-流程审批-详情", notes = "录用审批-流程审批-详情")
    @GetMapping("/findApproveDetails")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID"),
    })
    public R<EntryApproveFormDTO> findApproveDetails(Long id) {
        EntryApproveFormDTO result = staffEntrypostApproveService.findApproveDetails(id);
        return R.ok(result);
    }

    /**
     * 录用审批-填报中-修改-通过主键ID获取详情
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "录用审批-填报中-修改-通过主键ID获取详情", notes = "录用审批-填报中-修改-通过主键ID获取详情")
    @GetMapping("/findEntryJobEditInfoById")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID"),
    })
    public R<EntryApproveUpdateDTO> findEntryJobEditInfoById(Long id) {
        EntryApproveUpdateDTO result = staffEntrypostApproveService.findEntryJobEditInfoById(id);
        return R.ok(result);
    }

    /**
     * 录用审批-填报中-修改更新
     * @return R
     */
    @ApiOperation(value = "录用审批-填报中-修改更新", notes = "录用审批-填报中-修改更新")
    @PostMapping("/updateEntryJobEditInfo")
    public R<EntryApproveUpdateDTO> updateEntryJobEditInfo(@RequestBody EntryApproveUpdateDTO dto) {
        StaffEntrypostApprove approve=new StaffEntrypostApprove();
        BeanUtils.copyProperties(dto,approve);
        approve.setRecruitmentId(dto.getUserId());
        //状态：1 填报中，2 审批中，3 已审批
        approve.setStatus("1");
        approve.setPostId(Long.parseLong(dto.getPostId()));
        approve.setOrgId(dto.getOrgId());

        SysUser sysUser = SysUserUtils.getSysUser();
        if (ObjectUtil.isNotNull(sysUser)){
            approve.setModifierTime(LocalDateTime.now());
            approve.setModifierCode(sysUser.getUsername());
            approve.setModifierName(sysUser.getAliasName());
        }

        if (ObjectUtil.isNotNull(dto.getIsAppointment())){
            //true 1 , false 0
            if (dto.getIsAppointment()){
                approve.setIsAppointment(1);
            }
            if (!dto.getIsAppointment()){
                approve.setIsAppointment(0);
            }
        }

        staffEntrypostApproveService.updateById(approve);
        BeanUtils.copyProperties(approve,dto);
        return R.ok(dto,"修改更新成功");
    }

    /**
     * 更新录用审批表的fileId
     * @return R
     */
    @ApiOperation(value = "更新录用审批表的fileId", notes = "更新录用审批表的fileId")
    @PostMapping("/updateFileId")
    public R updateFileId(@RequestBody EntryApproveUpdateDTO dto) {

        return R.ok(dto,"修改更新成功");
    }

}
