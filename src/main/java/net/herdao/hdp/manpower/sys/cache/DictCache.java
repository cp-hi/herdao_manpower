package net.herdao.hdp.manpower.sys.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DictCache
 * @Description DictCache
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/7 16:34
 * @Version 1.0
 */
@Component
public class DictCache {

    private static SysDictItemService sysDictItemService;

    @Autowired
    public void setSysDictItemService(SysDictItemService dictItemService) {
        DictCache.sysDictItemService = dictItemService;
    }

    private static Map<String, BiMap<String, String>> dictList = new HashMap<>();

    public static String getDictLabel(String type, String val) {
        BiMap<String, String> biMap = dictList.get(type);
        if (null != biMap) return biMap.get(val);
        List<SysDictItem> dictItemList = sysDictItemService.list(Wrappers
                .<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
        if (0 == dictItemList.size()) return null;
        biMap = HashBiMap.create();
        for (SysDictItem item : dictItemList)
            biMap.put(item.getValue(), "1".equals(item.getDelFlag()) ? "--" : item.getLabel());
        dictList.put(type, biMap);
        return biMap.get(val);
    }

    public static String getDictVal(String type, String label) {
        BiMap<String, String> biMap = dictList.get(type);
        if (null != biMap) return biMap.inverse().get(label);
        List<SysDictItem> dictItemList = sysDictItemService.list(Wrappers
                .<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
        if (0 == dictItemList.size()) return null;
        biMap = HashBiMap.create();
        for (SysDictItem item : dictItemList)
            biMap.put(item.getValue(), item.getLabel());
        dictList.put(type, biMap);
        return biMap.inverse().get(label);
    }
}
