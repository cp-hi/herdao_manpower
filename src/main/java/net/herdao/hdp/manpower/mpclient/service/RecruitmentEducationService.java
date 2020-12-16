

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEduDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentEducation;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人才教育情况
 *
 * @author Andy
 * @date 2020-11-27 08:57:26
 */
public interface RecruitmentEducationService extends IService<RecruitmentEducation> {

    /**
     * 人才简历-教育情况-分页列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return RecruitmentEduDTO
     */
    Page<RecruitmentEduDTO> fetchResumeEduPage(Page page, Long recruitmentId);

    /**
     * 人才简历-教育情况-新增保存
     * @param dto 人才教育
     * @return RecruitmentEduDTO
     */
    RecruitmentEduDTO saveEdu(RecruitmentEduDTO dto);

    /**
     * 人才简历-教育情况-修改更新
     * @param dto 人才教育
     * @return RecruitmentEduDTO
     */
    RecruitmentEduDTO updateEdu(RecruitmentEduDTO dto);

    /**
     * 人才简历-教育情况-list
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentEduDTO> fetchResumeEduList(Long recruitmentId);

    /**
     * 人才简历-教育情况-新增或保存
     * @param dto 人才教育
     * @return RecruitmentEduDTO
     */
    RecruitmentEduDTO saveOrUpdateEducation(RecruitmentEduDTO dto);

}
