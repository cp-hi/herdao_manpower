package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.section.SectionDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SectionMapper extends BaseMapper<Section> {
    List<Map> sectionList(Long groupId);
    IPage<SectionListDTO> query(Page<SectionListDTO> page, @Param("section") Section section);

    Boolean chkDuplicateSectionCode(Section section);
    Boolean chkDuplicateSectionName(Section section);
    List<String>  getSectionCodesByGroupId(Long groupId);
}
