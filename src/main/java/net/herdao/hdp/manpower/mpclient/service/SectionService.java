package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends  EntityService<Section> {
    List<Map> sectionList(Long groupId);

//    Integer deleteAll();

    @Override
    Section getEntity(Long id);
}
