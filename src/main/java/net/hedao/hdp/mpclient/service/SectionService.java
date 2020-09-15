package net.hedao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.hedao.hdp.mpclient.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends IService<Section> {
    List<Map> sectionList();

    void addOrUpdate(Section section) throws Exception;
}
