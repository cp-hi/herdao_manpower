package com.hedao.hdp.mpclient.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.mpclient.oa.entity.Section;
import com.hedao.hdp.mpclient.oa.mapper.SectionMapper;
import com.hedao.hdp.mpclient.oa.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
