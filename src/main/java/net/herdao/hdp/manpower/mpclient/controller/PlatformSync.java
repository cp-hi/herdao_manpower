package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserDTO;
import net.herdao.hdp.admin.api.entity.*;
import net.herdao.hdp.admin.api.feign.*;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.sys.utils.RemoteCallUtils;
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

    private final RemoteDeptService remoteDeptService;
    private final RemoteGroupService remoteGroupService;
    private final RemoteGradeService remoteGradeService;
    private final RemoteStationService remoteStationService;
    private final RemoteUserService remoteUserService;
    private final RemoteUserDeptStationService remoteUserDeptStationService;
    @GetMapping("/aa")
    public R sync(){
        //同步组织
        List<Organization> list = organizationService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        //同步集团
        List<Group> list1 = groupService.list(Wrappers.lambdaQuery());
        list1.forEach(e->{
            saveOrUpdateSync(e);
        });
        //同步职级
        jobLevelService.list(Wrappers.<JobLevel>lambdaQuery()).forEach(e->{
            saveOrUpdateSync(e);
        });
        //同步岗位
        List<Post> list2 = postService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        //同步用户
        List<User> list3 = userService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        //同步用户组织岗位
        List<Userpost> list4 = userpostService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        return R.ok("同步完成");
    }
    //组织
    private void saveOrUpdateSync(Organization organization) {
        SysDept dept = convert(organization);
        R<Long> r = remoteDeptService.saveOrUpdate(dept);
        RemoteCallUtils.checkData(r);
    }
    private SysDept convert(Organization organization){
        SysDept dept = new SysDept();
        dept.setCpDeptId(ObjectUtil.isNotNull(organization.getId())?String.valueOf(organization.getId()):null);
        dept.setName(organization.getOrgName());
        dept.setCode(organization.getOrgCode());
        dept.setLevel(ObjectUtil.isNotNull(organization.getOrgTreeLevel())?String.valueOf(organization.getOrgTreeLevel()):null);
        dept.setCpParentId(ObjectUtil.isNotNull(organization.getParentId())?String.valueOf(organization.getParentId()):null);
        dept.setType(ObjectUtil.isNotNull(organization.getOrgType())?String.valueOf(organization.getOrgType()):null);
        dept.setVmFlag(organization.getIsVirtual()?"1":"0");
        dept.setFullName(organization.getOrgFullname());
        dept.setOrgChargeWorkId(organization.getOrgChargeWorkId());
        dept.setStationId(organization.getPostId());
        return dept;
    }
    //集团
    private void saveOrUpdateSync(Group group) {
        SysGroup sysGroup = convert(group);
        R<Long> r = remoteGroupService.saveOrUpdate(sysGroup);
        RemoteCallUtils.checkData(r);
    }
    private SysGroup convert(Group group){
        SysGroup sysGroup = new SysGroup();
        sysGroup.setName(group.getGroupName());
        sysGroup.setCode(group.getGroupCode());
        sysGroup.setCpId(group.getId());
        sysGroup.setDelFlag(group.getDelFlag()?"1":"0");
        sysGroup.setCpDeptId(group.getOrgId());
        return sysGroup;
    }
    //职级
    public void saveOrUpdateSync(JobLevel jobLevel) {
        SysGrade grade = convert(jobLevel);
        R<Long> longR = remoteGradeService.saveOrUpdate(grade);
        RemoteCallUtils.checkData(longR);
    }
    private SysGrade convert(JobLevel jobLevel){
        SysGrade grade = new SysGrade();
        grade.setCpId(jobLevel.getId());
        grade.setName(jobLevel.getJobLevelName());
        grade.setCode(jobLevel.getJobLevelCode());
        grade.setCpGroupId(jobLevel.getGroupId());
        grade.setSort(jobLevel.getSortNo());
        return grade;
    }
    //岗位
    private void saveOrUpdateSync(Post post) {
        SysStation sysStation = convert(post);
        R<Long> r = remoteStationService.saveOrUpdate(sysStation);
        RemoteCallUtils.checkData(r);
    }
    private SysStation convert(Post post){
        SysStation station = new SysStation();
        station.setCpId(post.getId());
        station.setCpGroupId(post.getGroupId());
        station.setName(post.getPostName());
        station.setCode(post.getPostCode());
        station.setCpGroupId(post.getGroupId());
        station.setCpBeginGrade(post.getJobLevelId1());
        station.setCpEndGrade(post.getJobLevelId2());
        station.setSingleGrade(post.getSingleJobLevle()?"1":"0");
        return station;
    }
    //用户
    private void saveOrUpdateSync(User user) {
        UserDTO convert = convert(user);
        R<Long> r = remoteUserService.saveOutsideUser(convert);
        RemoteCallUtils.checkData(r);
    }
    private UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        Staff one = staffService.getOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getUserId, user.getId()), false);
        if(ObjectUtil.isNotNull(one)){
            userDTO.setPhone(one.getEmergencyPhone());
            userDTO.setEmail(one.getEmail());
        }
        userDTO.setCpUserId(String.valueOf(user.getId()));
        userDTO.setUsername(user.getLoginCode());
        userDTO.setCpUsername(user.getLoginCode());
        userDTO.setAliasName(user.getUserName());
        userDTO.setCpDeptId(ObjectUtil.isNotNull(user.getOrgId())?String.valueOf(user.getOrgId()):null);
        userDTO.setCpTenantId(ObjectUtil.isNotNull(user.getTenantId())?String.valueOf(user.getTenantId()):null);
        return userDTO;
    }
    //用户组织岗位
    private void saveOrUpdateSync(Userpost userpost) {
        SysUserOrgStation convert = convert(userpost);
        R<Long> r = remoteUserDeptStationService.saveOrUpdate(convert);
        RemoteCallUtils.checkData(r);
    }
    private SysUserOrgStation convert(Userpost userpost){
        SysUserOrgStation sysUserOrgStation = new SysUserOrgStation();
        sysUserOrgStation.setCpId(userpost.getId());
        sysUserOrgStation.setCpUserId(userpost.getUserId());
        sysUserOrgStation.setCpDeptId(userpost.getOrgDeptId());
        sysUserOrgStation.setCpStationId(userpost.getPostId());
        sysUserOrgStation.setType(userpost.getOfficePostType());
        return sysUserOrgStation;
    }
}
