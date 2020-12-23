package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.dto.DeptDTO;
import net.herdao.hdp.admin.api.dto.UserDTO;
import net.herdao.hdp.admin.api.entity.SysGrade;
import net.herdao.hdp.admin.api.entity.SysGroup;
import net.herdao.hdp.admin.api.entity.SysStation;
import net.herdao.hdp.admin.api.entity.SysUserDeptStation;
import net.herdao.hdp.admin.api.feign.RemoteManpowerDataSyncService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.mapper.UserMapper;
import net.herdao.hdp.manpower.mpclient.service.PlatformSyncService;
import net.herdao.hdp.manpower.sys.utils.RemoteCallUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PlatformSyncServiceImpl implements PlatformSyncService {
    private final StaffMapper staffMapper;
    private final UserMapper userMapper;
    private final RemoteManpowerDataSyncService remoteManpowerDataSyncService;

    @Override
    public void saveOrUpdateUserPost(Userpost userpost) {
        List<UserDTO> l = new ArrayList<>();
        //l.add(convertU(userpost));
        //R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateUserBatch(l,SecurityConstants.FROM_IN);
        //RemoteCallUtils.checkData(r);
    }

    @Override
    public void saveOrUpdateUserPostBatch(List<Userpost> userposts) {
        List<UserDTO> l = convertUserPost(userposts);
        if(CollectionUtil.isEmpty(l)){
            return;
        }
        for(int i=0;i<l.size();){
            List<UserDTO> list2=l.subList(0,i+1000<l.size()?i+1000:l.size());
            //R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateUserBatch(list2, SecurityConstants.FROM_IN);
            //RemoteCallUtils.checkData(r);
            i+=1000;
        }

    }


    /**
     * 用户
     */
    @Override
    public void saveOrUpdateUserBatch(List<User> users){
        List<UserDTO> l = convertUser(users);
        if(CollectionUtil.isEmpty(l)){
            return;
        }
        for(int i=0;i<l.size();){
            List<UserDTO> list2=l.subList(0,i+1000<l.size()?i+1000:l.size());
            R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateUserBatch(JSON.toJSONString(list2));
            RemoteCallUtils.checkData(r);
            i+=1000;
        }

    }
    /**
     * 用户
     */
    @Override
    public void saveOrUpdateUser(User up){
        List<UserDTO> l = new ArrayList<>();
        //l.add(convertU(up));
        //R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateUserBatch(l,SecurityConstants.FROM_IN);
        //RemoteCallUtils.checkData(r);
    }
    /**
     * 用户
     */
    @Override
    public void isStopUser(Long userId,Boolean isStop){
        List<Long> l= new ArrayList<>();
        l.add(userId);
        R<Boolean> r = remoteManpowerDataSyncService.isStopUserBatch(JSON.toJSONString(l),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 用户
     * @param userIds
     */
    @Override
    public void isStopUserBatch(List<Long> userIds,Boolean isStop){
        R<Boolean> r = remoteManpowerDataSyncService.isStopUserBatch(JSON.toJSONString(userIds),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 部门
     * @param organizations
     */
    @Override
    public void saveOrUpdateDeptBatch(List<Organization> organizations){
        List<DeptDTO> deptDTOS = convertOrg(organizations);
        if(CollectionUtil.isEmpty(deptDTOS)){
            return;
        }
        for(int i=0;i<deptDTOS.size();){
            List<DeptDTO> list2=deptDTOS.subList(0,i+10<deptDTOS.size()?i+10:deptDTOS.size());
            R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateDeptBatch(JSON.toJSONString(list2));
            RemoteCallUtils.checkData(r);
            i+=10;
        }

    }
    @Override
    public void saveOrUpdateDept(Organization organization){
        List<DeptDTO> deptDTOS = new ArrayList<>();
        deptDTOS.add(convert(organization));
        R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateDeptBatch(JSON.toJSONString(deptDTOS));
        RemoteCallUtils.checkData(r);
    }

    /**
     * 部门
     */
    @Override
    public void isStopDept(Long id,Boolean isStop){
        List<Long> l= new ArrayList<>();
        l.add(id);
        R<Boolean> r = remoteManpowerDataSyncService.isStopDeptBatch(JSON.toJSONString(l),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 部门
     * @param ids
     */
    @Override
    public void isStopDeptBatch(List<Long> ids,Boolean isStop){
        R<Boolean> r = remoteManpowerDataSyncService.isStopDeptBatch(JSON.toJSONString(ids),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 集团
     */
    @Override
    public void saveOrUpdateGroup(Group group){
        List<SysGroup> sysGroups = new ArrayList<>();
        sysGroups.add(convert(group));
        R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateGroupBatch(JSON.toJSONString(sysGroups));
        RemoteCallUtils.checkData(r);
    }
    /**
     * 集团
     */
    @Override
    public void saveOrUpdateGroupBatch(List<Group> groups){
        List<SysGroup> sysGroups = convertGroup(groups);
        if(CollectionUtil.isEmpty(sysGroups)){
            return;
        }
        for(int i=0;i<sysGroups.size();){
            List<SysGroup> list2=sysGroups.subList(0,i+1000<sysGroups.size()?i+1000:sysGroups.size());
            R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateGroupBatch(JSON.toJSONString(list2));
            RemoteCallUtils.checkData(r);
            i+=1000;
        }

    }
    /**
     * 集团
     */
    @Override
    public void isStopGroup(Long id,Boolean isStop){
        List<Long> l= new ArrayList<>();
        l.add(id);
        R<Boolean> r = remoteManpowerDataSyncService.isStopGroupBatch(JSON.toJSONString(l),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 集团
     */
    @Override
    public void isStopGroupBatch(List<Long> ids,Boolean isStop){
        R<Boolean> r = remoteManpowerDataSyncService.isStopGroupBatch(JSON.toJSONString(ids),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 职级
     */
    @Override
    public void saveOrUpdateGrade(JobLevel jobLevel){
        List<SysGrade> list1 = new ArrayList<>();
        list1.add(convert(jobLevel));
        R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateGradeBatch(JSON.toJSONString(list1));
        RemoteCallUtils.checkData(r);
    }
    /**
     * 职级
     */
    @Override
    public void saveOrUpdateGradeBatch(List<JobLevel> list){
        List<SysGrade> list1 = convertJobLevel(list);
        if(CollectionUtil.isEmpty(list1)){
            return;
        }
        for(int i=0;i<list1.size();){
            List<SysGrade> list2=list1.subList(0,i+1000<list1.size()?i+1000:list1.size());
            R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateGradeBatch(JSON.toJSONString(list2));
            RemoteCallUtils.checkData(r);
            i+=1000;
        }
    }
    /**
     * 职级
     */
    @Override
    public void isStopGrade(Long id,Boolean isStop){
        List<Long> l= new ArrayList<>();
        l.add(id);
        R<Boolean> r = remoteManpowerDataSyncService.isStopGroupBatch(JSON.toJSONString(l),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 职级
     */
    @Override
    public void isStopGradeBatch(List<Long> ids,Boolean isStop){
        R<Boolean> r = remoteManpowerDataSyncService.isStopGroupBatch(JSON.toJSONString(ids),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 岗位
     */
    @Override
    public void saveOrUpdateStation(Post post){
        List<SysStation> l = new ArrayList<>();
        l.add(convert(post));
        R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateStationBatch(JSON.toJSONString(l));
        RemoteCallUtils.checkData(r);
    }
    /**
     * 岗位
     */
    @Override
    public void saveOrUpdateStationBatch(List<Post> list){
        List<SysStation> l = convertPost(list);
        if(CollectionUtil.isEmpty(l)){
            return;
        }
        for(int i=0;i<l.size();){
            List<SysStation> list2=l.subList(0,i+1000<l.size()?i+1000:l.size());
            R<Boolean> r = remoteManpowerDataSyncService.saveOrUpdateStationBatch(JSON.toJSONString(list2));
            RemoteCallUtils.checkData(r);
            i+=1000;
        }

    }
    /**
     * 岗位
     */
    @Override
    public void isStopStation(Long id,Boolean isStop){
        List<Long> l= new ArrayList<>();
        l.add(id);
        R<Boolean> r = remoteManpowerDataSyncService.isStopStationBatch(JSON.toJSONString(l),isStop);
        RemoteCallUtils.checkData(r);
    }
    /**
     * 岗位
     */
    @Override
    public void isStopStationBatch(List<Long> ids,Boolean isStop){
        R<Boolean> r = remoteManpowerDataSyncService.isStopStationBatch(JSON.toJSONString(ids),isStop);
        RemoteCallUtils.checkData(r);
    }
    private List<DeptDTO> convertOrg(List<Organization> organizations){
        List<DeptDTO> deptDTOS = new ArrayList<>();
        organizations.forEach(e->{
            deptDTOS.add(convert(e));
        });
        return deptDTOS;
    }
    private DeptDTO convert(Organization organization){
        DeptDTO dept = new DeptDTO();
        dept.setCpDeptId(ObjectUtil.isNotNull(organization.getId())?String.valueOf(organization.getId()):null);
        dept.setName(organization.getOrgName());
        dept.setCode(organization.getOrgCode());
        dept.setLevel(ObjectUtil.isNotNull(organization.getOrgTreeLevel())?String.valueOf(organization.getOrgTreeLevel()):null);
        dept.setCpParentId(ObjectUtil.isNotNull(organization.getParentId())?String.valueOf(organization.getParentId()):null);
        dept.setType(ObjectUtil.isNotNull(organization.getOrgType())?String.valueOf(organization.getOrgType()):null);
        dept.setVmFlag(ObjectUtil.isNotNull(organization.getIsVirtual())&&organization.getIsVirtual()?"1":"0");
        dept.setFullName(organization.getOrgFullname());
        dept.setOrgChargeWorkId(organization.getOrgChargeWorkId());
        dept.setCpStationId(organization.getPostId());
        dept.setUsername(ObjectUtil.isNotNull(organization.getOrgChargeWorkNo())?organization.getOrgChargeWorkNo():null);
        return dept;
    }
    private List<SysGroup> convertGroup(List<Group> groups){
        List<SysGroup> sysGroups = new ArrayList<>();
        groups.forEach(e->{
            sysGroups.add(convert(e));
        });
        return sysGroups;
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
    private List<SysGrade> convertJobLevel(List<JobLevel> jobLevel){
        List<SysGrade> sysGrades = new ArrayList<>();
        jobLevel.forEach(e->{
            sysGrades.add(convert(e));
        });
        return sysGrades;
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
    private List<SysStation> convertPost(List<Post> posts){
        List<SysStation> sysStations = new ArrayList<>();
        posts.forEach(e->{
            sysStations.add(convert(e));
        });
        return sysStations;
    }
    private SysStation convert(Post post){
        SysStation station = new SysStation();
        station.setCpId(post.getId());
        station.setCpGroupId(post.getGroupId());
        station.setName(post.getPostName());
        station.setCode(post.getPostCode());
        station.setCpGroupId(post.getGroupId());
        return station;
    }
    private List<UserDTO> convertUser(List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        Map<Long,Staff> map=new HashMap<>();
        staffMapper.selectList(Wrappers.<Staff>lambdaQuery().in(Staff::getUserId,
                users.stream().map(User::getId).collect(Collectors.toList()))).forEach(e->{
                    map.put(e.getUserId(),e);
        });
        users.forEach(e->{
            userDTOS.add(convertU(e,map));
        });
        return userDTOS;
    }

    private UserDTO convertU(User user,Map<Long,Staff> map){
        UserDTO userDTO = new UserDTO();
        Staff one=null;
        if(ObjectUtil.isNull(map)){
             one = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getUserId, user.getId()));
        }
        if(ObjectUtil.isNotNull(map)){
            Staff staff = map.get(user.getId());
            if(ObjectUtil.isNotNull(staff)){
                userDTO.setPhone(staff.getEmergencyPhone());
                userDTO.setEmail(staff.getEmail());
                userDTO.setStaffNo(staff.getStaffCode());
            }
        }else{
            if(ObjectUtil.isNotNull(one)){
                userDTO.setPhone(one.getEmergencyPhone());
                userDTO.setEmail(one.getEmail());
                userDTO.setStaffNo(one.getStaffCode());
            }
        }
        userDTO.setCpUserId(String.valueOf(user.getId()));
        userDTO.setUsername(user.getLoginCode());
        userDTO.setCpUsername(user.getLoginCode());
        userDTO.setAliasName(user.getUserName());
        userDTO.setCpTenantId(ObjectUtil.isNotNull(user.getTenantId())?String.valueOf(user.getTenantId()):null);
        userDTO.setCpDeptId(ObjectUtil.isNotNull(user.getOrgDeptId())?String.valueOf(user.getOrgDeptId()):null);
        return userDTO;
    }
    private List<UserDTO> convertUserPost(List<Userpost> userposts){
        Map<Long,Staff> map1=new HashMap<>();
        staffMapper.selectList(Wrappers.<Staff>lambdaQuery().in(Staff::getUserId,
                userposts.stream().map(Userpost::getUserId).collect(Collectors.toList()))).forEach(e->{
            map1.put(e.getUserId(),e);
        });
        Map<Long,User> map2=new HashMap<>();
        userMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getId,
                userposts.stream().map(Userpost::getUserId).collect(Collectors.toList()))).forEach(e->{
            map2.put(e.getId(),e);
        });
        List<UserDTO> userDTOS = new ArrayList<>();
        userposts.forEach(e->{
            userDTOS.add(convertU(e,map1,map2));
        });
        return userDTOS;
    }
    private UserDTO convertU(Userpost up,Map<Long,Staff> map1,Map<Long,User> map2){
        UserDTO userDTO = new UserDTO();
        Staff one=null;
        User user=null;
        if(ObjectUtil.isNull(map1)){
            one = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getUserId, userDTO.getUserId()));
        }
        if(ObjectUtil.isNull(map2)){
            user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, up.getUserId()));
        }
        if(ObjectUtil.isNotNull(map1)){
            Staff staff = map1.get(up.getUserId());
            if(ObjectUtil.isNotNull(staff)){
                userDTO.setPhone(staff.getEmergencyPhone());
                userDTO.setEmail(staff.getEmail());
                userDTO.setStaffNo(staff.getStaffCode());
            }
        }else{
            if(ObjectUtil.isNotNull(one)){
                userDTO.setPhone(one.getEmergencyPhone());
                userDTO.setEmail(one.getEmail());
                userDTO.setStaffNo(one.getStaffCode());
            }
        }
        userDTO.setCpUserId(String.valueOf(up.getUserId()));
        if(ObjectUtil.isNotNull(map2)){
            User user1 = map2.get(up.getUserId());
            if(ObjectUtil.isNotNull(user1)){
                userDTO.setUsername(user.getLoginCode());
                userDTO.setCpUsername(user.getLoginCode());
                userDTO.setAliasName(user.getUserName());
            }
        }
        if(ObjectUtil.isNotNull(user)){
            userDTO.setUsername(user.getLoginCode());
            userDTO.setCpUsername(user.getLoginCode());
            userDTO.setAliasName(user.getUserName());
            //userDTO.setCpStationId();
        }
        userDTO.setCpTenantId(ObjectUtil.isNotNull(user.getTenantId())?String.valueOf(user.getTenantId()):null);
        userDTO.setCpDeptId(ObjectUtil.isNotNull(user.getOrgDeptId())?String.valueOf(user.getOrgDeptId()):null);
        return userDTO;
    }
    private SysUserDeptStation convert(Userpost userpost){
        SysUserDeptStation sysUserOrgStation = new SysUserDeptStation();
        sysUserOrgStation.setCpId(userpost.getId());
        sysUserOrgStation.setCpUserId(userpost.getUserId());
        sysUserOrgStation.setCpDeptId(userpost.getOrgDeptId());
        sysUserOrgStation.setCpStationId(userpost.getPostId());
        sysUserOrgStation.setType(userpost.getOfficePostType());
        return sysUserOrgStation;
    }
}
