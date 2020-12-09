

package net.herdao.hdp.manpower.mpclient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import net.herdao.hdp.manpower.mpclient.dto.organization.OrgModifyRecordDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;


/**
 * 组织变更记录
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgmodifyrecord")
@Api(value = "orgmodifyrecord", tags = "组织变更记录管理")
public class OrgModifyRecordController {

    private final OrgModifyRecordService orgModifyRecordService;

    /**
     * 分页查询
     *
     * @param page            分页对象
     * @param orgModifyRecord
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_view')")
    public R getOrgModifyRecordPage(Page page, OrgModifyRecord orgModifyRecord) {
        return R.ok(orgModifyRecordService.page(page, Wrappers.query(orgModifyRecord)));
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_view')")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(orgModifyRecordService.getById(id));
    }

    /**
     * 新增
     *
     * @param orgModifyRecord
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_add')")
    public R save(@RequestBody OrgModifyRecord orgModifyRecord) {
        return R.ok(orgModifyRecordService.save(orgModifyRecord));
    }

    /**
     * 修改
     *
     * @param orgModifyRecord
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_edit')")
    public R updateById(@RequestBody OrgModifyRecord orgModifyRecord) {
        return R.ok(orgModifyRecordService.updateById(orgModifyRecord));
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(orgModifyRecordService.removeById(id));
    }

    /**
     * 组织变更记录
     *
     * @param operatorId
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "组织变更记录", notes = "组织变更记录")
    @GetMapping("/getOrgChangeRecordPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgCode", value = "组织编码"),
            @ApiImplicitParam(name = "searchText", value = "模糊查询内容")
    })
    public R<Page<OrgModifyRecordDTO>> getOrgChangeRecordPage(Page page, String orgCode, String searchText) {
        return R.ok(orgModifyRecordService.getPage(page, orgCode, searchText));
    }


}
