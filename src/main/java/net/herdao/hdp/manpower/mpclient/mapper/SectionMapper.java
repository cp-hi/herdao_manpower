package net.herdao.hdp.manpower.mpclient.mapper;

import net.herdao.hdp.manpower.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author ljan
 */
@Mapper
public interface SectionMapper extends EntityMapper<Section> {
    List<Map> sectionList(Long groupId);
}
