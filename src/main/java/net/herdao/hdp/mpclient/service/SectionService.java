package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends IService<Section> {
    List<Map> sectionList();

    boolean saveOrUpdate(Section section);
}
