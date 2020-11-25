
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAddFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentBaseDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
public interface RecruitmentService extends HdpService<Recruitment> {
    /**
     * 人才表分页
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字
     * @return
     */
    Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, String orgId, String searchText);

    /**
     * 修改更新人才
     * @param recruitmentUpdateFormVO 人才实体
     * @return
     */
    R<RecruitmentUpdateFormDTO> updateRecruitment(RecruitmentUpdateFormDTO recruitmentUpdateFormVO);

    /**
     * 新增人才
     * @param recruitmentAddFormDTO 人才实体
     * @return
     */
    R<RecruitmentAddFormDTO> saveRecruitment(RecruitmentAddFormDTO recruitmentAddFormDTO);

    /**
     * 人才简历-顶部
     * @param id 主键ID
     * @return
     */
    RecruitmentUpdateFormDTO fetchResumeTop(Long id);

    /**
     * 人才简历-个人基本情况 从业情况与求职意向
     * @param id 主键ID
     * @return
     */
    RecruitmentBaseDTO fetchResumeBaseSituation(Long id);


}
