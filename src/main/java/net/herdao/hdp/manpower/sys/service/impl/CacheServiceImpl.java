package net.herdao.hdp.manpower.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.sys.service.CacheService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheServiceImpl
 * @Description CacheServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/12/3 18:25
 * @Version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Resource(name = "entityNamesCache")
    Cache<String, Map<Long, String>> entityNamesCache;

    @Resource(name = "dictCache")
    Cache<String, BiMap<String, String>> dictCache;

    @Resource(name = "groupCache")
    Cache<String, Group> groupCache;


    @Autowired
    SysDictItemService sysDictItemService;

    @Autowired
    GroupService groupService;


    @Override
    public void setEntityNames(Class entityClass, Map<Long, String> entityNames) {
        Map<Long, String> origin = entityNamesCache.getIfPresent(entityClass.getName());
        if (null == origin) origin = new HashMap<>();
        origin.putAll(entityNames);
        entityNamesCache.put(entityClass.getName(), origin);
    }

    @Override
    public Map<Long, String> getEntityNames(Class entityClass) {
        Map<Long, String> origin = entityNamesCache.getIfPresent(entityClass.getName());
        if (null == origin) {
            origin = new HashMap<>();
            entityNamesCache.put(entityClass.getName(), origin);
        }
        return origin;
    }


    //region 集团

    public Group getGroupByName(String groupName, boolean need, StringBuffer buffer) {
        Group group = groupCache.getIfPresent(groupName);
        if (null == group) {
            group = groupService.getOne(Wrappers.<Group>query().lambda().eq(Group::getGroupName, groupName));
            groupCache.put(groupName, group);
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
    public Group getGroupByName(String groupName, boolean need) {
        StringBuffer buffer = new StringBuffer();
        Group group = getGroupByName(groupName, need, buffer);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(buffer))
            throw new Exception(buffer.toString());
        return group;
    }

    //endregion

    //region 字典

    public String getDictLabel(String type, String val) {
        BiMap<String, String> biMap = dictCache.getIfPresent(type);
        if (null != biMap) return biMap.get(val);
        List<SysDictItem> dictItemList = sysDictItemService.list(Wrappers
                .<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
        if (0 == dictItemList.size()) return null;
        biMap = HashBiMap.create();
        for (SysDictItem item : dictItemList)
            biMap.put(item.getValue(), "1".equals(item.getDelFlag()) ? "--" : item.getLabel());
        dictCache.put(type, biMap);
        return biMap.get(val);
    }

    public String getDictVal(String type, String label) {
        BiMap<String, String> biMap = dictCache.getIfPresent(type);
        if (null != biMap) return biMap.inverse().get(label);
        List<SysDictItem> dictItemList = sysDictItemService.list(Wrappers
                .<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
        if (0 == dictItemList.size()) return null;
        biMap = HashBiMap.create();
        for (SysDictItem item : dictItemList)
            biMap.put(item.getValue(), item.getLabel());
        dictCache.put(type, biMap);
        return biMap.inverse().get(label);
    }

    public String getDictVal(String type, String label, StringBuffer buffer) {
        String val = getDictVal(type, label);
        if (StringUtils.isBlank(val))
            buffer.append("；不存在此字典：" + label);
        return val;
    }

    public String getDictLabel(String type, String val, StringBuffer buffer) {
        String label = getDictLabel(type, val);
        if (StringUtils.isBlank(label))
            buffer.append("；不存在此字典值：" + val);
        return label;
    }

    //endregion
}
