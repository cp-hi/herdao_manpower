package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends EntityService<Section> {
    IPage<Section> page(Page<Section> page, String searchTxt);

    List<Map> sectionList(Long groupId);
}
