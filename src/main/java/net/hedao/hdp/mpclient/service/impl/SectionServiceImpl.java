package net.hedao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.hedao.hdp.mpclient.entity.Section;
import net.hedao.hdp.mpclient.mapper.SectionMapper;
import net.hedao.hdp.mpclient.service.SectionService;
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
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section> implements SectionService {

 public    List<Map> sectionList(){
        return baseMapper.sectionList();
    }

    public void addOrUpdate(Section section) throws Exception {
        if (baseMapper.chkDuplicateSectionCode(section))
            throw new Exception("岗位编码重复了");
        if (baseMapper.chkDuplicateSectionName(section))
            throw new Exception("岗位名称重复了");

        if (null == section.getId()) {
            this.save(section);
        } else {
            this.updateById(section);
        }
    }
}
