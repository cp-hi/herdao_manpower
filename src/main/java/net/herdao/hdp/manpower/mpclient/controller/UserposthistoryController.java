

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
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.UserposthistoryService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * 员工岗位历史表
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@RestController
@RequestMapping("/userposthistory" )
@Api(value = "userposthistory", tags = "员工岗位历史表管理")
public class UserposthistoryController extends HdpBaseController {
    @Autowired
    private UserposthistoryService userposthistoryService;

    @Override
    public HdpService getHdpService() {
        return userposthistoryService;
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param userposthistory 员工岗位历史表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('mpclient_userposthistory_view')" )
    public R page(Page page, Userposthistory userposthistory) {
        return R.ok(userposthistoryService.page(page, Wrappers.query(userposthistory)));
    }

    /**
     * 历史职情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "历史职情况分页", notes = "历史职情况分页")
    @GetMapping("/findUserPostHistoryPage")
    @OperationEntity(operation = "历史职情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findUserPostHistoryPage(Page page,UserpostDTO userpostDTO, String searchText) {
        return R.ok(userposthistoryService.findUserPostHistoryPage(page,userpostDTO, searchText));
    }

    /**
     * 导出历史任职情况Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出历史任职情况Excel", notes = "导出历史任职情况Excel")
    @GetMapping("/exportStaffJobHis")
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    @SneakyThrows
    public void exportStaffJobHis(HttpServletResponse response, UserpostDTO userpostDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage pageResult = userposthistoryService.findUserPostHistoryPage(page, userpostDTO, searchText);
        ExcelUtils.export2Web(response, "历史任职情况", "历史任职情况", UserpostDTO.class, pageResult.getRecords());
    }


}
