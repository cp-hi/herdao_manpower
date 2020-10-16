
package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;

import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@RestController
@RequestMapping("/userpost" )
@Api(value = "userpost", tags = "用户岗位")
public class UserpostController extends BaseController<Userpost, Userpost> {
    @Autowired
    private UserpostService userpostService;

    @Autowired
    public void setEntityService(UserpostService userpostService) {
        super.entityService = userpostService;
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
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findUserPostNowPage(Page page, String searchText) {
        Page pageResult = userpostService.findUserPostNowPage(page, searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出现任职情况Excel
     * @param  response
     * @param searchText
     * @return R
     */
    @ApiOperation(value = "导出现任职情况Excel", notes = "导出现任职情况Excel")
    @SysLog("导出现任职情况Excel" )
    @PostMapping("/exportStaffNowJob")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    public R exportStaffNowJob(HttpServletResponse response, String searchText) {
        try {
            List<UserpostDTO> list = userpostService.findUserPostNow(searchText);
            ExcelUtils.export2Web(response, "现任职情况", "现任职情况表", UserpostDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }


}
