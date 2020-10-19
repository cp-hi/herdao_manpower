package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.section.SectionDTO;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SectionServiceImpl
 * @Description SectionServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 19:46
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section> implements SectionService {

    @Override
    public List<Map> sectionList(Long groupId) {
        return baseMapper.sectionList(  groupId);
    }

    @Override
    public IPage<SectionDTO> page(Page<SectionDTO> page, Section Section) {

        return baseMapper.query(page,   Section);
    }

    @Override
    public void saveVerify(Section section) {
        if (baseMapper.chkDuplicateSectionCode(section))
            throw new RuntimeException("板块编码重复了");
        if (baseMapper.chkDuplicateSectionName(section))
            throw new RuntimeException("板块名称重复了");
    }
}
