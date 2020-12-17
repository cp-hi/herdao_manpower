package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.*;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.QrCodeUtils;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.EntryJobVO;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.ModuleVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


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
    public R<Page<EntryDTO>> findEntryPage(Page<EntryDTO> page, String orgId, String searchText) {
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
     * 办理入职-确认入职
     * @param entryJobVO 入职VO
     * @return
     */
    @ApiOperation(value = "办理入职-确认入职", notes = "办理入职-确认入职")
    @PostMapping("/confirmEntry")
    public R<StaffEntrypostApprove> confirmEntry(@RequestBody EntryJobVO entryJobVO) {
        StaffEntrypostApprove approve = staffEntrypostApproveService.confirmEntry(entryJobVO);
        return R.ok(approve);
    }

    /**
     * 确认入职登记
     * @param id 主键id
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="入职表(审批表)主键id",required = true)
    })
    @ApiOperation(value = "确认入职登记", notes = "确认入职登记")
    @PostMapping("/confirmEntryRegister")
    public R<StaffEntrypostApprove> confirmEntryRegister(String id) {
        StaffEntrypostApprove approve = staffEntrypostApproveService.getById(id);
        //修改更新入职登记状态
        if (ObjectUtil.isNotNull(approve)){
            //入职登记状态 (1:未提交，2：已提交，3：已确认）
            approve.setEntryCheckStatus("3");
            //状态：1 填报中，2 审批中，3 已审批
            approve.setStatus("3");
            staffEntrypostApproveService.updateById(approve);
        }

        return R.ok(approve,"确认入职登记成功");
    }

    /**
     * 提醒重新登记
     * @param id 主键id
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="入职表(审批表)主键id",required = true)
    })
    @ApiOperation(value = "提醒重新登记", notes = "提醒重新登记")
    @PostMapping("/remindRegister")
    public R<StaffEntrypostApprove> remindRegister(String id) {
        // TODO: 要与小明沟通 进一步确定与完善业务代码
        return R.ok();
    }

    /**
     * 邀请入职登记-确认邮件内容（内含二维码）
     * @return R
     */
    @ApiOperation(value = "批量邀请更新简历-确认邮件内容", notes = "批量邀请更新简历-确认邮件内容")
    @PostMapping("/confirmRegisterListEmail")
    @ApiImplicitParams({
        @ApiImplicitParam(name="recruitmentIds",value="人才表主键id数组",required = true),
    })
    public R<ModuleVO> confirmRegisterListEmail(Long[] recruitmentIds) {
        ModuleVO moduleVO=new ModuleVO();
        Integer tenantId = SecurityUtils.getUser().getTenantId();
        if (ObjectUtil.isNotNull(tenantId)){
            //手机端极速入职页面地址
            String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
            String code = QrCodeUtils.createBase64QrCode(address);
            moduleVO.setCode(code);
        }

        if (ObjectUtil.isNotEmpty(recruitmentIds)){
            int length = recruitmentIds.length;
            String title="您已选中"+length+"名待入职员工，请在下方编辑邮件内容，确认后将为你批量发送邀请邮件。";
            moduleVO.setTitle(title);

            List<Recruitment> recruitmentList = recruitmentService.listByIds(Arrays.asList(recruitmentIds));
            if (ObjectUtil.isNotEmpty(recruitmentList)){
                List<Map<String,String>> nameEmailList=new ArrayList<>();
                recruitmentList.forEach(r->{
                    if(ObjectUtil.isNotNull(r)){
                        Map<String,String> nameEmailMap=new HashMap<>();
                        nameEmailMap.put(r.getTalentName(),r.getEmail());
                        nameEmailList.add(nameEmailMap);
                    }
                });
                moduleVO.setNameEmailList(nameEmailList);
            }
        }

        //todo:调用系统模板接口，获取模板配置信息。

        return R.ok(moduleVO);
    }

    /**
     * 入职登记记录-发送请确认页面（内含二维码）
     * @return R
     */
    @ApiOperation(value = "入职登记记录-发送请确认页面", notes = "入职登记记录-发送请确认页面")
    @GetMapping("/fetchConfirmRegisterEmail")
    public R<ModuleVO> confirmRegisterEmail() {
        ModuleVO moduleVO=new ModuleVO();
        Integer tenantId = SecurityUtils.getUser().getTenantId();
        if (ObjectUtil.isNotNull(tenantId)){
            //手机端极速入职页面地址
            String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
            String code = QrCodeUtils.createBase64QrCode(address);
            moduleVO.setCode(code);
        }

        //todo:调用系统模板接口，获取模板配置信息。

        return R.ok(moduleVO);
    }

}
