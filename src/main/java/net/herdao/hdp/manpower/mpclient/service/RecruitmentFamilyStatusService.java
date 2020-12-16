package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAddFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFamilyDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentFamilyStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人才家庭情况
 *
 * @author Andy
 * @date 2020-11-26 15:28:43
 */
public interface RecruitmentFamilyStatusService extends IService<RecruitmentFamilyStatus> {

    /**
     * 个人简历-家庭情况
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentFamilyDTO> fetchResumeFamilyList(Long recruitmentId);

    /**
     * 个人简历-家庭情况-分页列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    Page<RecruitmentFamilyDTO> fetchResumeFamilyPage(Page page, Long recruitmentId);

    /**
     * 个人简历-家庭情况-新增
     * @param familyDTO 人才家庭成员实体
     * @return
     */
    RecruitmentFamilyDTO saveFamily(RecruitmentFamilyDTO familyDTO);

    /**
     * 个人简历-家庭情况-更新
     * @param familyDTO 人才家庭成员实体
     * @return
     */
    RecruitmentFamilyDTO updateFamily(RecruitmentFamilyDTO familyDTO);

    /**
     * 个人简历-家庭情况-新增或保存
     * @param familyDTO 人才家庭成员实体
     * @return
     */
    RecruitmentFamilyDTO saveOrUpdateFamily(RecruitmentFamilyDTO familyDTO);

}
