
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.vo.recruitment.RecruitmentMobileProgressVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

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
    R saveRecruitment(RecruitmentAddFormDTO recruitmentAddFormDTO);

    /**
     * 人才简历-顶部
     * @param id 主键ID
     * @return
     */
    RecruitmentUpdateFormDTO fetchResumeTop(Long id);

    /**
     * 修改更新人才简历-个人基本情况
     * @param dto
     * @return
     */
    RecruitmentEditBaseInfoDTO updateBaseInfo(RecruitmentEditBaseInfoDTO dto);

    /**
     * 人才简历-从业情况与求职意向
     * @param id 主键ID
     * @return
     */
    RecruitmentJobIntentDTO fetchResumeJobIntent(Long id);

    /**
     * 人才简历-从业情况与求职意向-修改更新
     * @param dto 从业情况与求职意向
     * @return
     */
    RecruitmentJobIntentDTO updateRecruitmentJobIntent(@RequestBody RecruitmentJobIntentDTO dto);

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
     * 候选人H5登录
     * @param mobile
     * @param code
     * @return
     */
     R<Recruitment> recruitmentLogin(String mobile, String code);

    /**
     * 获取人才简历-其他个人信息-详情
     * @param id 主键ID
     * @return RecruitmentOtherInfo
     */
    RecruitmentOtherInfo fetchRecruitmentOtherInfo(Long id);

    /**
     * 编辑人才简历-个人基本信息-详情
     * @param id 主键ID
     * @return RecruitmentOtherInfo
     */
    RecruitmentBaseInfo fetchRecruitmentBaseInfo(Long id);

    /**
     * 修改更新人才简历-其他信息
     * @param otherInfo 其他信息
     * @return
     */
     RecruitmentEditOtherInfoDTO updateOtherInfo(@RequestBody RecruitmentEditOtherInfoDTO otherInfo);

    /**
     * 候选人H5登录验证码
     * @param mobile
     * @return
     */
    R<Boolean> sendSmsCode(String mobile);

	/**
	 * 
	 * @param recordId  表单业务ID
	 * @param flowType 流程类型（录用流程）
	 * @return
	 */
	R generateWorkflow(String recordId, String flowType,String contentUrl);

    /**
     * 手机端-基本信息-个人信息-新增或修改
     * @param dto 人才表
     * @return
     */
    R saveOrUpdateRecruitment(RecruitmentBaseInfoMobileDTO dto);

    /**
     * 获取-手机端个人信息-填写进度
     * @param id 主键ID
     * @return
     */
    RecruitmentMobileProgressVO fetchMobileInfoProgress(Long id);

    /**
     *简历详情-工作情况-手机端
     * @param id 人才ID
     * @return
     */
    RecruitmentWorkDetailsDTO fetchResumeWorkDetailsByMobile(Long id);

    /**
     * 人才管理-人才简历
     * @param id 人才ID
     * @return
     */
    RecruitmentDetailsDTO fetchRecruitmentDetailsDTO(Long id);

    /**
     *简历详情-工作情况
     * @param id 人才ID
     * @return
     */
    RecruitmentWorkDetailsDTO fetchResumeWorkDetails(Long id);

    /**
     * 简历详情-基础信息-个人基本信息
     * @param id 人才ID
     * @return
     */
    RecruitmentEditDetailsDTO fetchResumeEditDetails(Long id);

    /**
     * 简历详情-基础信息-个人基本信息-手机端
     * @param id 人才ID
     * @return
     */
    RecruitmentEditDetailsDTO fetchResumeEditDetailsByMobile(Long id);
}
