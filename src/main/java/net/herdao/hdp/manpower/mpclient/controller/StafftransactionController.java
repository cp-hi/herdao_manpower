package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffTrans.StafftransDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StafftransactionService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * 异动情况
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@RestController
@RequestMapping("/stafftransaction" )
@Api(value = "stafftransaction", tags = "异动情况")
public class StafftransactionController extends HdpBaseController  {
    @Autowired
    private StafftransactionService stafftransactionService;

    @Override
    public HdpService getHdpService() {
        return stafftransactionService;
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftransaction 异动情况
     * @return
     */
    @GetMapping("/page" )
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_view')" )
    public R page(Page page, Stafftransaction stafftransaction) {
        return R.ok(stafftransactionService.page(page, Wrappers.query(stafftransaction)));
    }

    /**
     * 员工异动情况分页
     * @param page 分页对象
     * @param searchText 搜索关键字
     * @return
     */
    @ApiOperation(value = "员工异动情况分页", notes = "员工异动情况分页")
    @GetMapping("/findStaffTransPage")
    @OperationEntity(operation = "员工异动情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字"),
        @ApiImplicitParam(name="staffId",value="员工ID")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTransPage(Page page, StafftransDTO stafftransDTO,String searchText) {
        return R.ok(stafftransactionService.findStaffTransPage(page,stafftransDTO, searchText));
    }

    /**
     * 导出员工异动情况Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工异动情况Excel", notes = "导出员工异动情况Excel")
    @GetMapping("/exportTrans")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字"),
        @ApiImplicitParam(name="staffId",value="员工ID")
    })
    @SneakyThrows
    public void exportTrans(HttpServletResponse response, StafftransDTO stafftransDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage pageResult = stafftransactionService.findStaffTransPage(page, stafftransDTO, searchText);
        ExcelUtils.export2Web(response, "员工异动情况表", "员工异动情况表", StafftransDTO.class, pageResult.getRecords());
    }


}
