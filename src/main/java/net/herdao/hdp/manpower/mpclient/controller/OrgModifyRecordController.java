

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.OrgModifyRecord;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.service.OrgModifyRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 组织变更记录
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgmodifyrecord")
@Api(value = "orgmodifyrecord", tags = "管理")
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
    @SysLog("新增")
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
    @SysLog("修改")
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
    @SysLog("通过id删除")
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
    @ApiOperation(value = "组织变更记录查询", notes = "组织变更记录查询")
    @GetMapping("/getOrgChangeRecordPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchText", value = "关键字搜索"),
            @ApiImplicitParam(name = "operatorId", value = "操作人ID(当前登录用户ID)")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_orgmodifyrecord_view')" )
    public R getOrgChangeRecordPage(Page page, String operatorId, String searchText) {
        OrgModifyRecord entity = new OrgModifyRecord();
        QueryWrapper<OrgModifyRecord> wrapper = Wrappers.query(entity);

        if (StringUtils.isNotBlank(operatorId)) {
            entity.setOperatorId(operatorId);
            wrapper.eq("operator_id", operatorId);
        }

        if (searchText != null && !"".equals(searchText)) {
            wrapper.like("CONCAT(operator_id)", searchText);
        }

        Page<OrgModifyRecord> pageResult = orgModifyRecordService.page(page, wrapper);
        return R.ok(pageResult);
    }


}
