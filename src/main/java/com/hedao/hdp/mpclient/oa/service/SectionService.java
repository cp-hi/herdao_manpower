package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Section;

public interface SectionService extends IService<Section> {
     void addOrUpdate(Section section)throws Exception;
}
