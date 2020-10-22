package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.OKJobLevleSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;
import net.herdao.hdp.manpower.mpclient.mapper.OKJobLevleSysMapper;
import net.herdao.hdp.manpower.mpclient.service.OKJobGradeService;
import net.herdao.hdp.manpower.mpclient.service.OKJobLevelService;
import net.herdao.hdp.manpower.mpclient.service.OKJobLevleSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OKJobLevleSysServiceImpl
 * @Description OKJobLevleSysServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 9:27
 * @Version 1.0
 */
@Service
public class OKJobLevleSysServiceImpl extends ServiceImpl<OKJobLevleSysMapper, OKJobLevleSys> implements OKJobLevleSysService {
    @Autowired
    OKJobLevelService okJobLevelService;

    @Autowired
    OKJobGradeService okJobGradeService;

    @Override
    public List<OKJobLevleSys> findAll() {
        return this.baseMapper.findAll();
    }

    @Override
    public OKJobLevleSysDTO findDetail(Long id) {
        return this.baseMapper.findDetail(id);
    }

}
