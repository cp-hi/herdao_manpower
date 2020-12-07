

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentActivitiDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentActiviti;

import java.util.List;

/**
 * 人才活动表
 *
 * @author Andy
 * @date 2020-12-02 20:12:40
 */
public interface RecruitmentActivitiService extends IService<RecruitmentActiviti> {
    /**
     * 简历详情-人才活动
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentActivitiDTO> findRecruitmentActivitiList(Long recruitmentId);
}
