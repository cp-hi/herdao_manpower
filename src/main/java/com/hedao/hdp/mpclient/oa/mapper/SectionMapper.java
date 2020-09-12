package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Section;

public interface SectionMapper extends BaseMapper<Section> {
    Boolean chkSectionDuplicateCode(Section section);
    Boolean chkSectionDuplicateName(Section section);
}
