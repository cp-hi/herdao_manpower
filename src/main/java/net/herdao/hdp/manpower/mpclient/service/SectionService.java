package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.section.SectionDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends EntityService<Section> {
    IPage<SectionListDTO> page(Page<SectionListDTO> page, Section section);

    List<Map> sectionList(Long groupId);
}
