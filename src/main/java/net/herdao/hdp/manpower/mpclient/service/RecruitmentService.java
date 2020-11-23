
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import org.apache.ibatis.annotations.Param;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
public interface RecruitmentService extends IService<Recruitment> {
    /**
     * 人才表分页
     * @param page 分页对象
     * @param recruitmentDTO 人才DTO
     * @param searchText 关键字
     * @return
     */
    Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, RecruitmentDTO recruitmentDTO, String searchText);

    /**
     * @param recruitment
     * @return
     */
    @Override
    boolean saveOrUpdate(Recruitment recruitment);
}
