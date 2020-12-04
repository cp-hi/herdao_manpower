package net.herdao.hdp.manpower.sys.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.entity.Group;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheService
 * @Description CacheService
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/12/3 18:24
 * @Version 1.0
 */
public interface CacheService {

    void setEntityNames(Class entityClass, Map<Long, String> entityNames);

    Map<Long, String> getEntityNames(Class entityClass);

    //region
      Group getGroupByName(String groupName, boolean need, StringBuffer buffer) ;
    Group getGroupByName(String groupName, boolean need);
    //endregion

    //region 字典
    String getDictLabel(String type, String val);

    String getDictVal(String type, String label);

    String getDictVal(String type, String label, StringBuffer buffer);

    String getDictLabel(String type, String val, StringBuffer buffer);
    //endregion
}
