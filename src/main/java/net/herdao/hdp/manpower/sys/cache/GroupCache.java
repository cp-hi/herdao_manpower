package net.herdao.hdp.manpower.sys.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.BiMap;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GroupCache
 * @Description GroupCache
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/11 18:32
 * @Version 1.0
 */
@Component
public class GroupCache {

    private static GroupService groupService;

    @Autowired
    public void setGroupService(GroupService service) {
        GroupCache.groupService = service;
    }

    private static Map<String, Group> groupMap = new HashMap<>();


    public static Group getGroupByName(String groupName, boolean need, StringBuffer buffer) {
        Group group = groupMap.get(groupName);
        if (null == group) {
            group = groupService.getOne(Wrappers.<Group>query().lambda().eq(Group::getGroupName, groupName));
            groupMap.put(groupName, group);
        }
        String errMsg = "";
        if (!need && null != group)  //不需要它但它不为空
            //添加分号，因为批量导入需要所有错误信息
            errMsg = "；已存在此集团" + groupName;
        if (need && null == group)  //需要它但它为空
            errMsg = "；不存在此集团" + groupName;
        buffer.append(errMsg);
        return group;
    }

    @SneakyThrows
    public static Group getGroupByName(String groupName, boolean need) {
        StringBuffer buffer = new StringBuffer();
        Group group = getGroupByName(groupName, need, buffer);
        if (StringUtils.isNotBlank(buffer))
            throw new Exception(buffer.toString());
        return group;
    }

}
