package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public void okCreateJobLevel(Long okJobLevleSysId) {
        OKJobLevleSysDTO jobLevleSys = findDetail(okJobLevleSysId);
        jobLevleSys.getOkJobGradeDTOList().forEach(okJobGrade -> {
            JobGrade jobGrade = okJobGrade;
            jobGrade.setId(null);
            jobGradeService.saveVerify(jobGrade);//TODO 校验
            jobGradeService.save(jobGrade);
            List<? extends JobLevel> jobLevels = okJobGrade.getOkJobLevelDTOList();
            jobLevels.forEach(jobLevel -> {
                jobLevel.setId(null);
                jobLevel.setJobGradeId(jobGrade.getId());
                jobLevelService.saveVerify(jobLevel);  // TODO 校验
                jobLevelService.saveOrUpdate(jobLevel);
            });
        });
    }
}
