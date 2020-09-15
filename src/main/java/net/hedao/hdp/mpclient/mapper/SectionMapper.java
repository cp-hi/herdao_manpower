package net.hedao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.hedao.hdp.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SectionMapper extends BaseMapper<Section> {
    List<Map> sectionList();
    Boolean chkDuplicateSectionCode(Section section);
    Boolean chkDuplicateSectionName(Section section);
}
