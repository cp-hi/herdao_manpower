package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Section;

import java.util.List;
import java.util.Map;

public interface SectionService extends IService<Section> {
    List<Map> sectionList();

    void addOrUpdate(Section section) throws Exception;
}
