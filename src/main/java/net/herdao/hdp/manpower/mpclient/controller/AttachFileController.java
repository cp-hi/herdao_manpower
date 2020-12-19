package net.herdao.hdp.manpower.mpclient.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.attachFile.AttachFileSituationDTO;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;


/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/attachFile" )
@Api(value = "attachFile", tags = "通用附件表管理")
public class AttachFileController {

    private final  AttachFileService attachFileService;

    /**
     * 通用附件表
     * @param page 分页对象
     * @param attachFile 通用附件表
     * @return
     */
    @ApiOperation(value = "通用附件表", notes = "通用附件表")
    @GetMapping("/getAttachFilePage" )
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_view')" )
    public R<Page<AttachFile>> getAttachFilePage(Page page, AttachFile attachFile) {
        Page<AttachFile> pageResult = attachFileService.page(page, Wrappers.query(attachFile));
        return R.ok(pageResult);
    }

    /**
     * 通过id查询通用附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询附件详情", notes = "通过id查询附件详情")
    @PostMapping("/getAttachFileById" )
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_view')" )
    public R<AttachFile> getAttachFileById(Long id) {
        AttachFile file = attachFileService.getById(id);
        return R.ok(file);
    }

    /**
     * 修改通用附件表
     * @param attachFile 通用附件表
     * @return R
     */
    @ApiOperation(value = "修改通用附件表", notes = "修改通用附件表")
    @PostMapping("/updateFileById")
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_edit')" )
    public R<AttachFile> updateById(@RequestBody AttachFile attachFile) {
       attachFileService.updateById(attachFile);
        return R.ok(attachFile);
    }

    /**
     * 	通过id删除通用附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "删除附件", notes = "通过id删除通用附件表")
    @PostMapping("/del")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_del')" )
    public R removeById(Long id) {
        return R.ok(attachFileService.removeById(id));
    }

    /**
     * 获取-个人简历-简历附件;入职登记详情页面-简历附件
     * @return R
     */
    @ApiOperation(value = "获取-个人简历-简历附件;入职登记详情页面-简历附件", notes = "获取-个人简历-简历附件;入职登记详情页面-简历附件")
    @GetMapping("/fetchResumeAttachFileInfo")
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_edit')" )
    public R<List<AttachFileSituationDTO>> fetchResumeAttachFileInfo() {
        List<AttachFileSituationDTO> list = attachFileService.fetchResumeAttachFileInfo();
        return R.ok(list);
    }

    /**
     * 获取-入职登记详情-入职附件
     * @return R
     */
    @ApiOperation(value = "获取-入职登记详情-入职附件", notes = "获取-入职登记详情-入职附件")
    @GetMapping("/fetchEntryAttachFileInfo")
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_edit')" )
    public R<List<AttachFileSituationDTO>> fetchEntryAttachFileInfo() {
        List<AttachFileSituationDTO> list = attachFileService.fetchEntryAttachFileInfo();
        return R.ok(list);
    }
    
    
    
    
    
    
    
    
    
    /**
     * 新增通用附件表
     * @param attachFile 通用附件表
     * @return R
     */
    @ApiOperation(value = "新增通用附件表", notes = "新增通用附件表")
    @SysLog("新增通用附件表" )
    @PostMapping("/add")
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_add')" )
    public R<AttachFile> save(@RequestBody AttachFileAddDTO attachFile) {
        attachFileService.saveAttachFile(attachFile);
        return R.ok();
    }
    
    /**
     * 	通过FileId删除通用附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "fileId删除附件", notes = "通过文件id删除通用附件表")
    @PostMapping("/del/{fileId}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "fileId", value = "文件id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_del')" )
    public R delByFileId(@PathVariable String fileId) {
        return R.ok(attachFileService.deleteByFileId(fileId));
    }
}
