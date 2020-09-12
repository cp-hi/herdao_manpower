package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Section;

public interface SectionMapper extends BaseMapper<Section> {
    Boolean chkDuplicateSectionCode(Section section);
    Boolean chkDuplicateSectionName(Section section);
}
