
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

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
     * 人才简历-个人基本情况 其他个人信息 从业情况与求职意向
     * @param dto
     * @return
     */
    RecruitmentDetailsDTO updateBaseInfo(RecruitmentDetailsDTO dto);

    /**
     * 人才简历-从业情况与求职意向
     * @param id 主键ID
     * @return
     */
    RecruitmentJobDTO fetchResumeJob(Long id);

    /**
     * 人才简历-从业情况与求职意向-修改更新
     * @param dto 从业情况与求职意向
     * @return
     */
    RecruitmentJobDTO updateRecruitmentJob(@RequestBody RecruitmentJobDTO dto);

    /**
     * 人才简历-录用情况-列表分页
     * @param recruitmentId 人才ID
     * @return RecruitmentEmployeeDTO
     */
    RecruitmentEmployeeDTO fetchEmploy(String recruitmentId);

    /**
     * 获取人才简历-个人基本情况-详情
     * @param id 主键ID
     * @return RecruitmentDetailsDTO
     */
    RecruitmentPersonDTO fetchRecruitmentPerson(Long id);

    /**
     * 获取人才简历-从业情况与求职意向-详情
     * @param id 主键ID
     * @return RecruitmentIntentDTO
     */
    RecruitmentIntentDTO fetchRecruitmentIntent(Long id);

    /**
     * 获取人才简历-最高教育经历-详情
     * @param id 主键ID
     * @return RecruitmentTopEduDTO
     */
    RecruitmentTopEduDTO fetchRecruitmentTopEdu(@Param("id") Long id);

}
