package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.OKJobGradeDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.OKJobLevleSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;
import net.herdao.hdp.manpower.mpclient.mapper.OKJobLevleSysMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    JobLevelService jobLevelService;

    @Autowired
    JobGradeService jobGradeService;

    @Override
    public OKJobLevleSysDTO findDetail(Long id) {
        return this.baseMapper.findDetail(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void okCreateJobLevel(Long okJobLevleSysId, Long groupId)
            throws IllegalAccessException {
        OKJobLevleSysDTO jobLevleSys = findDetail(okJobLevleSysId);
        for (OKJobGradeDTO okJobGrade : jobLevleSys.getOkJobGradeDTOList()) {
            JobGrade jobGrade = okJobGrade;
            jobGrade.setId(null);
            jobGrade.setGroupId(groupId);
            jobGradeService.saveVerify(jobGrade);
            jobGradeService.saveEntity(jobGrade);
            List<? extends JobLevel> jobLevels = okJobGrade.getOkJobLevelDTOList();
            for (JobLevel jobLevel : jobLevels) {
                jobLevel.setId(null);
                jobLevel.setGroupId(groupId);
                jobLevel.setJobGradeId(jobGrade.getId());
                jobLevelService.saveVerify(jobLevel);
                jobLevelService.saveEntity(jobLevel);
                System.out.println(jobLevel.getId());
            }
        }
    }

    public IPage page(IPage page, OKJobLevleSys okJobLevleSys) {
        return null;
    }

    public OKJobLevleSys getEntity(Long id) {
        return null;
    }
}
