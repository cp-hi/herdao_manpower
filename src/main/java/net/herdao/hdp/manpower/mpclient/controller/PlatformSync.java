package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
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

import java.util.ArrayList;
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
    @GetMapping("/a")
    public R a(){
        //同步组织
        List<Organization> list = organizationService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });

        return R.ok();
    }
    @GetMapping("/b")
    public R b(){
        //同步集团
        List<Group> list = groupService.list(Wrappers.lambdaQuery());
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        return R.ok();
    }
    @GetMapping("/c")
    public R c(){
        //同步职级
        List<JobLevel> list = jobLevelService.list(Wrappers.<JobLevel>lambdaQuery());
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        return R.ok();
    }
    @GetMapping("/d")
    public R d(){
        //同步岗位
        List<Post> list = postService.list();
        postsaveOrUpdateBatchSync(list);
        return R.ok();
    }
    @GetMapping("/e")
    public R e(){
        //同步用户
        List<User> list = userService.list();
        list.forEach(e->{
            saveOrUpdateSync(e);
        });
        return R.ok();
    }
    @GetMapping("/f")
    public R f(){
        //同步用户组织岗位
        List<Userpost> list = userpostService.list();
//        saveOrUpdateBatchSync(list);
        return R.ok();
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
        sysGroup.setDelFlag(group.getDelFlag()==null||group.getDelFlag()?"1":"0");
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
    private void postsaveOrUpdateBatchSync(List<Post> users) {
        List<SysStation> userDTOS = new ArrayList<>();
        users.forEach(user->{
            SysStation convert = convert(user);
            userDTOS.add(convert);
        });
        for(int i=0;i<userDTOS.size();){
            List<SysStation> sysUserOrgStations1 = userDTOS.subList(i, i + 10);
            remoteStationService.saveOrUpdateBatch(sysUserOrgStations1);
            i=i+10;
        }
    }
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
    private void usersaveOrUpdateBatchSync(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user->{
            UserDTO convert = convert(user);
            userDTOS.add(convert);
        });
        for(int i=0;i<userDTOS.size();){
            List<UserDTO> sysUserOrgStations1 = userDTOS.subList(i, i + 10);
            remoteUserService.saveOutsideUserBatch(sysUserOrgStations1);
            i=i+10;
        }
    }
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
//    private void saveOrUpdateBatchSync(List<Userpost> userposts) {
//        List<SysUserOrgStationDTO> sysUserOrgStations = new ArrayList<>();
//        userposts.forEach(e->{
//            sysUserOrgStations.add(convert(e));
//        });
//        for(int i=0;i<sysUserOrgStations.size();){
//            List<SysUserOrgStationDTO> sysUserOrgStations1 = sysUserOrgStations.subList(i, i + 13);
//            System.out.println(JSON.toJSON(sysUserOrgStations1));
//            remoteUserDeptStationService.saveOrUpdateBatch(sysUserOrgStations1);
//            i=i+13;
//        }
//    }
    private void saveOrUpdateSync(Userpost userpost) {
//        SysUserOrgStation convert = convert(userpost);
//        R<Long> r = remoteUserDeptStationService.saveOrUpdate(convert);
//        RemoteCallUtils.checkData(r);
    }
//    private SysUserOrgStationDTO convert(Userpost userpost){
//        SysUserOrgStationDTO sysUserOrgStation = new SysUserOrgStationDTO();
//        sysUserOrgStation.setCpId(userpost.getId());
//        sysUserOrgStation.setCpUserId(userpost.getUserId());
//        sysUserOrgStation.setCpDeptId(userpost.getOrgDeptId());
//        sysUserOrgStation.setCpStationId(userpost.getPostId());
//        sysUserOrgStation.setType(userpost.getOfficePostType());
//        return sysUserOrgStation;
//    }

}
