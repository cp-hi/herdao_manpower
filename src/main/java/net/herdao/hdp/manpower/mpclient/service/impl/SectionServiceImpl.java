package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import lombok.AllArgsConstructor;
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
public class SectionServiceImpl extends EntityServiceImpl<SectionMapper, Section> implements SectionService {

    final GroupService groupService;

    @Override
    public List<Map> sectionList(Long groupId) {
        return baseMapper.sectionList(groupId);
    }

    @Override
    public void addEntity(Section section, Object excelObj) {
        SectionBatchAddVO excel = (SectionBatchAddVO) excelObj;
        chkEntityExists("SECTION_NAME", excel.getSectionName(), false);
        Group group = groupService.getGroupByName(excel.getGroupName());
        section.setGroupId(group.getId());
    }

    @Override
    public void updateEntity(Section section, Object excelObj) {
        SectionBatchUpdateVO excel = (SectionBatchUpdateVO) excelObj;
        Section tmp = chkEntityExists("SECTION_NAME", excel.getSectionName(), true);
        Group group = groupService.getGroupByName(excel.getGroupName());
        section.setGroupId(group.getId());
        section.setId(tmp.getId());
    }
}
