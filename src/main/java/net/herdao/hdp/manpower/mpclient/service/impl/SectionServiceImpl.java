package net.herdao.hdp.manpower.mpclient.service.impl;

import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    GroupService groupService;

    @Override
    public List<Map> sectionList(Long groupId) {
        return baseMapper.sectionList(groupId);
    }

    @Override
    public void addEntity(Section section, Object excelObj) {
        SectionBatchAddVO excel = (SectionBatchAddVO) excelObj;
        Group group = groupService.selectByName(excel.getGroupName(), true);
        chkEntityExists(excel.getSectionName(), group.getId(), false);
        if(null != group)  section.setGroupId(group.getId());
    }

    @Override
    public void updateEntity(Section section, Object excelObj) {
        SectionBatchUpdateVO excel = (SectionBatchUpdateVO) excelObj;
        Group group = groupService.selectByName(excel.getGroupName(), true);
        Section tmp = chkEntityExists(excel.getSectionName(), group.getId(), true);
        if(null != group)  section.setGroupId(group.getId());
        section.setId(tmp.getId());
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
