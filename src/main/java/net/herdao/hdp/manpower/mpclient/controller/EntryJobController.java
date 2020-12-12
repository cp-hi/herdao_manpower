package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEmployeeDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 入职管理
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/entryJob" )
@Api(value = "entryJob", tags = "入职审批管理")
public class EntryJobController {

    private final StaffEntrypostApproveService staffEntrypostApproveService;

    private final RecruitmentService recruitmentService;

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
     * 入职管理-邀请入职登记-导出Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "入职管理-邀请入职登记-导出Excel", notes = "入职管理-邀请入职登记-导出Excel")
    @GetMapping("/exportInviteEntry")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    @SneakyThrows
    public R<EntryDTO> exportInviteEntry(HttpServletResponse response, String orgId, String searchText) {
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
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    public R<Page<EntryRegisterDTO>> findEntryRegisterPage(Page<EntryRegisterDTO> page, String orgId, String entryCheckStatus, String searchText) {
        Page<EntryRegisterDTO> pageResult = staffEntrypostApproveService.findEntryRegisterPage(page, orgId, entryCheckStatus, searchText);
        return R.ok(pageResult);
    }

    /**
     * 入职登记记录-导出Excel
     * @param response
     * @param orgId 组织ID
     * @param entryCheckStatus 入职登记状态 (1:已提交，2：已提交，3：已确认）
     * @param searchText 关键字搜索
     * @return R
     */
    @ApiOperation(value = "入职登记记录-导出Excel", notes = "入职登记记录-导出Excel")
    @GetMapping("/exportEntryRegisterPage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="entryCheckStatus",value="入职登记状态 (1:未提交，2：已提交，3：已确认）"),
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    @SneakyThrows
    public R<EntryRegisterDTO> exportEntryRegisterPage(HttpServletResponse response,  String orgId, String entryCheckStatus, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page<EntryRegisterDTO> pageResult = staffEntrypostApproveService.findEntryRegisterPage(page, orgId, entryCheckStatus, searchText);
        String excelName="入职登记记录";
        String sheetName="入职登记记录";
        String key1="1";
        String key2="2";
        String key3="3";
        if (ObjectUtil.isNotEmpty(entryCheckStatus)){
            if (key1.equals(entryCheckStatus)){
                excelName+="-未提交";
                sheetName+="-未提交";
            }
            if (key2.equals(entryCheckStatus)){
                excelName+="-已提交";
                sheetName+="-已提交";
            }
            if (key3.equals(entryCheckStatus)){
                excelName+="-已确认";
                sheetName+="-已确认";
            }
        }
        ExcelUtils.export2Web(response, excelName, sheetName, EntryRegisterDTO.class, pageResult.getRecords());
        EntryRegisterDTO dto=new EntryRegisterDTO();
        return R.ok(dto);
    }

    /**
     * 入职管理-办理入职-获取个人信息和入职信息详情
     * @param recruitmentId 人才ID
     * @return R
     */
    @ApiOperation(value = "入职管理-办理入职-获取个人信息和入职信息详情", notes = "入职管理-办理入职-获取个人信息和入职信息详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name="recruitmentId",value="人才表主键ID",required = true),
        @ApiImplicitParam(name="id",value="审批表主键ID",required = true)
    })
    @GetMapping("/findEntryInfo")
    public R<EntryInfoDTO> findEntryInfo(String recruitmentId,String id) {
        EntryPersonInfoDTO entryPersonInfo = staffEntrypostApproveService.findEntryPersonInfo(recruitmentId);
        EntryJobDTO entryJobInfo = staffEntrypostApproveService.findEntryJobInfo(id);

        EntryInfoDTO entryInfo=new EntryInfoDTO();
        entryInfo.setEntryPersonInfoDTO(entryPersonInfo);
        entryInfo.setEntryJobDTO(entryJobInfo);

        return R.ok(entryInfo);
    }

    /**
     * 入职登记详情-确认入职登记
     * @param recruitmentId 人才表主键id
     * @param approveId 审批录用表主键id
     * @param certificateType 证件类型
     * @param certificateNo 证件号码
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="recruitmentId",value="人才表主键id",required = true),
            @ApiImplicitParam(name="approveId",value="审批录用表主键id",required = true),
            @ApiImplicitParam(name="certificateType",value="证件类型"),
            @ApiImplicitParam(name="certificateNo",value="证件号码")
    })
    @ApiOperation(value = "入职登记详情-确认入职登记", notes = "入职登记详情-确认入职登记")
    @PostMapping("/confirmEntry")
    public R<StaffEntrypostApprove> confirmEntry(Long recruitmentId,String approveId,String certificateType,String certificateNo) {
        StaffEntrypostApprove approve = staffEntrypostApproveService.confirmEntry(recruitmentId, approveId, certificateType, certificateNo);
        return R.ok(approve);
    }


}
