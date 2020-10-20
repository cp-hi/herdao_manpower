package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staff.*;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 员工表编辑
 *
 * @author yangrr
 * @date 2020-10-20 18:10:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffedit" )
@Api(value = "staffedit", tags = "员工表编辑")
public class StaffEditController {

    private final StaffService staffService;

    private final UserService userService;

    @ApiOperation(value = "停用员工", notes = "通过工号停用员工")
    @GetMapping("/stop/{staffCode}" )
    public R<Boolean> stopByCode(@PathVariable("staffCode" ) String staffCode) {
        boolean isOk = userService.update(new UpdateWrapper<User>()
                .set("IS_STOP", 1)
                .eq("LOGIN_CODE",staffCode)
        );
        return R.ok(isOk);
    }

    @ApiOperation(value = "员工详情-个人信息", notes = "个人信息")
    @SysLog("个人信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffInfoDTO staffInfo) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffInfo, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-在职信息", notes = "在职信息")
    @SysLog("在职信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffJobInfoDTO staffJobInfo) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffJobInfo, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-其他个人信息", notes = "其他个人信息")
    @SysLog("其他个人信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffInfoOtherDTO staffInfoOther) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffInfoOther, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-紧急联系人", notes = "紧急联系人")
    @SysLog("紧急联系人" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffEmergencyDTO staffEmergency) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffEmergency, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-社保信息", notes = "社保信息")
    @SysLog("社保信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffSecurityDTO staffSecurity) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffSecurity, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-公积金信息", notes = "公积金信息")
    @SysLog("公积金信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffFundDTO staffFund) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffFund, staff);
        return R.ok(staffService.updateById(staff));
    }

    @ApiOperation(value = "员工详情-薪资信息", notes = "薪资信息")
    @SysLog("薪资信息" )
    @PutMapping("/quickedit" )
    public R<Boolean> updateById(@RequestBody StaffSalaryDTO staffSalary) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffSalary, staff);
        return R.ok(staffService.updateById(staff));
    }

}
