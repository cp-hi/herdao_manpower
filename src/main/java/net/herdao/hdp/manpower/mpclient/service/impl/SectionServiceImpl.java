package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    public IPage<Section> page(Page<Section> page, String searchTxt) {
        if (StringUtils.isBlank(searchTxt)) searchTxt = "";
        return baseMapper.query(page, searchTxt);
    }

    @Override
    public boolean saveOrUpdate(Section section) {
        if (baseMapper.chkDuplicateSectionCode(section))
            throw new RuntimeException("板块编码重复了");
        if (baseMapper.chkDuplicateSectionName(section))
            throw new RuntimeException("板块名称重复了");
        return super.saveOrUpdate(section);
    }
}
