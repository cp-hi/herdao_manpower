
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
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@RestController
@RequestMapping("/userpost" )
@Api(value = "userpost", tags = "用户岗位管理")
public class UserpostController extends HdpBaseController {

    @Autowired
    private UserpostService userpostService;

    @Override
    public HdpService getHdpService() {
        return userpostService;
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param userpost 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('generator_userpost_view')" )
    public R page(Page page, Userpost userpost) {
        return R.ok(userpostService.page(page, Wrappers.query(userpost)));
    }

    /**
     * 现任职情况分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "现任职情况分页", notes = "现任职情况分页")
    @GetMapping("/findUserPostNowPage")
    @OperationEntity(operation = "现任职情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    public R findUserPostNowPage(Page page,UserpostDTO userpostDTO, String searchText) {
        return R.ok(userpostService.findUserPostNowPage(page,userpostDTO, searchText));
    }

    /**
     * 导出现任职情况Excel
     * @param  response
     * @param searchText
     * @return R
     */
    @ApiOperation(value = "导出现任职情况Excel", notes = "导出现任职情况Excel")
    @GetMapping("/exportStaffNowJob")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    @SneakyThrows
    public void exportStaffNowJob(HttpServletResponse response,UserpostDTO userpostDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage pageResult = userpostService.findUserPostNowPage(page, userpostDTO, searchText);
        ExcelUtils.export2Web(response, "现任职情况", "现任职情况表", UserpostDTO.class,pageResult.getRecords());
    }

}
