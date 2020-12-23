package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platformSync")
@AllArgsConstructor
public class PlatformSync {
    private final OrganizationService organizationService;
    private final GroupService groupService;
    private final JobLevelService jobLevelService;
    private final PostService postService;
    private final UserService userService;
    private final UserpostService userpostService;
    private final StaffService staffService;

    private final PlatformSyncService platformSyncService;
    @GetMapping("/a")
    public R a(){
        //同步组织
        List<Organization> list = organizationService.list();
        platformSyncService.saveOrUpdateDeptBatch(list);

        return R.ok();
    }
    @GetMapping("/b")
    public R b(){
        //同步集团
        List<Group> list = groupService.list(Wrappers.lambdaQuery());
        platformSyncService.saveOrUpdateGroupBatch(list);
        return R.ok();
    }
    @GetMapping("/c")
    public R c(){
        //同步职级
        List<JobLevel> list = jobLevelService.list(Wrappers.<JobLevel>lambdaQuery());
        platformSyncService.saveOrUpdateGradeBatch(list);
        return R.ok();
    }
    @GetMapping("/d")
    public R d(){
        //同步岗位
        List<Post> list = postService.list();
        platformSyncService.saveOrUpdateStationBatch(list);
        return R.ok();
    }
    @GetMapping("/e")
    public R e(){
        //同步用户
        List<User> list = userService.list(Wrappers.<User>lambdaQuery().eq(User::getLoginCode,607943));
        platformSyncService.saveOrUpdateUserBatch(list);
        return R.ok();
    }
    @GetMapping("/f")
    public R f(){
//        SysUserUtils.getSysUser().getUsername();
//        System.out.println(123);
//        //同步用户组织岗位
//        List<Userpost> list = userpostService.list();
//        saveOrUpdateBatchSync(list);
        return R.ok();
    }



}
