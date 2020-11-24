
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFormVO;
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
     * 修改更新人才表
     * @param recruitmentFormVO 人才实体
     * @return
     */
    RecruitmentFormVO updateRecruitment(RecruitmentFormVO recruitmentFormVO);
}
