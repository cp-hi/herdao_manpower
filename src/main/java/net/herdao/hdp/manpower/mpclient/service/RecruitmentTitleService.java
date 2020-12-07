package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTitleDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTitle;

import java.util.List;

/**
 * 人才职称资格
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
public interface RecruitmentTitleService extends IService<RecruitmentTitle> {
    /**
     * 简历详情-职称及职业资格
     * @param recruitmentId 人才ID
     * @return RecruitmentTitleDTO
     */
    List<RecruitmentTitleDTO> findRecruitmentTitleList(Long recruitmentId);
}
