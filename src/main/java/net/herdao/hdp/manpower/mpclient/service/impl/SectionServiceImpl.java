package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostBatchAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.SectionDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionBatchAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionBatchUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
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

    final GroupService groupService;

    @Override
    public List<Map> sectionList(Long groupId) {
        return baseMapper.sectionList(groupId);
    }

    @Override
    public IPage page(Page page, Section Section) {
        return baseMapper.query(page, Section);
    }

    @Override
    public void saveVerify(Section section) {
        if (baseMapper.chkDuplicateSectionCode(section))
            throw new RuntimeException("板块编码重复了");
        if (baseMapper.chkDuplicateSectionName(section))
            throw new RuntimeException("板块名称重复了");
    }

    @Override
    public void importVerify(Section section, Object excelObj, int type) {
        boolean add = (0 == type);
        if (add) addSection(section, excelObj);
        else updateSection(section, excelObj);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(section);
    }

    private void addSection(Section section, Object excelObj) {
        SectionBatchAddDTO excel = (SectionBatchAddDTO) excelObj;
        getSectionByName(excel.getSectionName(), true);
        Group group = groupService.getGroupByName(excel.getGroupName());
        section.setGroupId(group.getId());
    }

    private void updateSection(Section section, Object excelObj) {
        SectionBatchUpdateDTO excel = (SectionBatchUpdateDTO) excelObj;
        Section tmp = getSectionByName(excel.getSectionName(), false);
        Group group = groupService.getGroupByName(excel.getGroupName());
        section.setGroupId(group.getId());
        section.setId(tmp.getId());
    }

    private Section getSectionByName(String sectionName, boolean add) {
        Section section = this.baseMapper.selectOne(new QueryWrapper<Section>()
                .eq("section_NAME", sectionName));
        if (add && null != section)
            throw new RuntimeException("已存在此板块：" + section.getSectionName());
        if (!add && null == section)
            throw new RuntimeException("不存在此板块：" + section.getSectionName());
        return section;
    }
}
