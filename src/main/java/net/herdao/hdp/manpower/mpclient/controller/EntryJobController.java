package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * 入职管理
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/entryJob" )
@Api(value = "entryJob", tags = "入职管理")
public class EntryJobController {

    private final StaffEntrypostApproveService staffEntrypostApproveService;

    /**
     * 入职管理-待入职-列表分页
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "入职管理-待入职-列表分页")
    @GetMapping("/findEntryPage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R<Page<EntryDTO>> findEntryPage(Page<EntryDTO> page, String orgId, String searchText,String status) {
        Page<EntryDTO> pageResult = staffEntrypostApproveService.findEntryPage(page, orgId, searchText);
        return R.ok(pageResult);
    }

    /**
     * 入职管理-待入职-导出Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "入职管理-待入职-导出Excel", notes = "入职管理-待入职-导出Excel")
    @GetMapping("/exportEntry")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    @SneakyThrows
    public R<EntryDTO> exportEntry(HttpServletResponse response, String orgId, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<EntryDTO> pageResult = staffEntrypostApproveService.findEntryPage(page, orgId, searchText);
        ExcelUtils.export2Web(response, "入职管理待入职表", "入职管理待入职表", EntryDTO.class, pageResult.getRecords());
        EntryDTO dto=new EntryDTO();
        return R.ok(dto);
    }

    /**
     * 入职管理-最近入职-列表分页
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "入职管理-最近入职-列表分页")
    @GetMapping("/findInJobPage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R<Page<EntryDTO>> findInJobPage(Page<EntryDTO> page, String orgId, String searchText) {
        Page<EntryDTO> pageResult = staffEntrypostApproveService.findInJobPage(page, orgId, searchText);
        return R.ok(pageResult);
    }

    /**
     * 入职管理-已入职-导出Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "入职管理-已入职-导出Excel", notes = "入职管理-已入职-导出Excel")
    @GetMapping("/exportInJob")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    @SneakyThrows
    public R<EntryDTO> exportInJob(HttpServletResponse response, String orgId, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<EntryDTO> pageResult = staffEntrypostApproveService.findInJobPage(page, orgId, searchText);
        ExcelUtils.export2Web(response, "入职管理待已入职表", "入职管理待已入职表", EntryDTO.class, pageResult.getRecords());
        EntryDTO dto=new EntryDTO();
        return R.ok(dto);
    }

    /**
     * 入职管理-待入职-列表分页
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "入职管理-邀请入职登记")
    @GetMapping("/findEntryInvitePage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R<Page<EntryDTO>> findEntryInvitePage(Page<EntryDTO> page, String orgId, String searchText) {
        Page<EntryDTO> pageResult = staffEntrypostApproveService.findEntryInvitePage(page, orgId, searchText);
        return R.ok(pageResult);
    }

    /**
     * 入职登记记录-未提交 已提交 已确认-列表分页
     * @param page 分页对象
     * @param orgId 组织ID
     * @param entryCheckStatus 入职登记记录 (1:已提交，2：已提交，3：已确认）
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "入职登记记录-未提交 已提交 已确认-列表分页")
    @GetMapping("/findEntryRegisterPage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="entryCheckStatus",value="入职登记记录 (1:已提交，2：已提交，3：已确认）"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    public R<Page<EntryRegisterDTO>> findEntryRegisterPage(Page<EntryRegisterDTO> page, String orgId, String entryCheckStatus, String searchText) {
        Page<EntryRegisterDTO> pageResult = staffEntrypostApproveService.findEntryRegisterPage(page, orgId, entryCheckStatus, searchText);
        return R.ok(pageResult);
    }
}
