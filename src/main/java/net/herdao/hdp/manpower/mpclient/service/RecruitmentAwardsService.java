

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentAwards;
import org.apache.ibatis.annotations.Param;

/**
 * 人才获奖情况表
 *
 * @author Andy
 * @date 2020-11-26 18:51:47
 */
public interface RecruitmentAwardsService extends IService<RecruitmentAwards> {

    /**
     * 人才简历-获奖情况-列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    Page<RecruitmentAwardsDTO> fetchResumeAwardsPage(Page page, Long recruitmentId);

    /**
     * 人才简历-获奖情况-新增或修改
     * @param dto
     * @return
     */
    RecruitmentAwardsDTO saveOrUpdate(RecruitmentAwardsDTO dto);

}
