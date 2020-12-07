
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTrain;

import java.util.List;

/**
 * 人才培训表
 *
 * @author Andy
 * @date 2020-12-02 20:12:55
 */
public interface RecruitmentTrainService extends IService<RecruitmentTrain> {
    /**
     * 简历详情-人才培训-list
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentTrainDTO> findRecruitmentTrainList(Long recruitmentId);
}
