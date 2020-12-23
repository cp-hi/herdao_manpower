package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.*;

import java.util.List;

public interface PlatformSyncService {
    /**
     * 用户
     */
    void saveOrUpdateUserPostBatch(List<Userpost> userposts);
    /**
     * 用户
     */
    void saveOrUpdateUserPost(Userpost userpost);
    /**
     * 用户
     */
    void saveOrUpdateUserBatch(List<User> users);
    /**
     * 用户
     */
    void saveOrUpdateUser(User user);
    /**
     * 用户
     */
    void isStopUser(Long userId,Boolean isStop);
    /**
     * 用户
     * @param userIds
     */
    void isStopUserBatch(List<Long> userIds,Boolean isStop);

    /**
     * 部门
     */
    void saveOrUpdateDeptBatch(List<Organization> organizations);
    /**
     * 部门
     */
    void saveOrUpdateDept(Organization organization);

    /**
     * 部门
     */
    void isStopDept(Long id,Boolean isStop);
    /**
     * 部门
     * @param ids
     */
    void isStopDeptBatch(List<Long> ids,Boolean isStop);
    /**
     * 集团
     */
    void saveOrUpdateGroup(Group group);
    /**
     * 集团
     */
    void saveOrUpdateGroupBatch(List<Group> groups);
    /**
     * 集团
     */
    void isStopGroup(Long id,Boolean isStop);
    /**
     * 集团
     */
    void isStopGroupBatch(List<Long> ids,Boolean isStop);
    /**
     * 职级
     */
    void saveOrUpdateGrade(JobLevel jobLevel);
    /**
     * 职级
     */
    void saveOrUpdateGradeBatch(List<JobLevel> list);
    /**
     * 职级
     */
    void isStopGrade(Long id,Boolean isStop);
    /**
     * 职级
     */
    void isStopGradeBatch(List<Long> ids,Boolean isStop);
    /**
     * 岗位
     */
    void saveOrUpdateStation(Post post);
    /**
     * 岗位
     */
    void saveOrUpdateStationBatch(List<Post> list);
    /**
     * 岗位
     */
    void isStopStation(Long id,Boolean isStop);
    /**
     * 岗位
     */
    void isStopStationBatch(List<Long> ids,Boolean isStop);
}
