package net.herdao.hdp.manpower.mpclient.service.impl;

import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import net.herdao.hdp.manpower.sys.cache.GroupCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName SectionServiceImpl
 * @Description SectionServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 19:46
 * @Version 1.0
 */
@Service
public class SectionServiceImpl extends EntityServiceImpl<SectionMapper, Section> implements SectionService {

    @Override
    public List<Map> sectionList(Long groupId) {
        return baseMapper.sectionList(groupId);
    }

    @Override
    public void addEntity(Section section, Object excelObj, StringBuffer buffer) {
        SectionBatchAddVO excel = (SectionBatchAddVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) section.setGroupId(group.getId());
        chkEntityExists(excel.getSectionName(), group.getId(), false,buffer);
    }

    @Override
    public void updateEntity(Section section, Object excelObj, StringBuffer buffer) {
        SectionBatchUpdateVO excel = (SectionBatchUpdateVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) section.setGroupId(group.getId());
        Section tmp = chkEntityExists(excel.getSectionName(), group.getId(), true);
        if (StringUtils.isBlank(buffer)) section.setId(tmp.getId());
    }

    @Override
    public Function<Section, String> getNameMapper() {
        return Section::getSectionName;
    }

    @Override
    public Function<Section, Long> getGroupIdMapper() {
        return Section::getGroupId;
    }
}
