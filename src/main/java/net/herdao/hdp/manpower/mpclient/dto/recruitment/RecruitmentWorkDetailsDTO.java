
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTitle;

import java.util.List;

/**
 * 简历详情-工作情况
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "简历详情-工作情况")
public class RecruitmentWorkDetailsDTO {

    /**
     * 人才简历-个人基本情况-详情
     */
    @ApiModelProperty(value="人才简历-人才获奖")
    private List<RecruitmentAwardsDTO> recruitmentAwardsDTO;

    /**
     * 人才简历-个人基本情况-详情
     */
    @ApiModelProperty(value="人才简历-人才工作经历")
    private List<RecruitmentWorkExperienceDTO> workExperienceDTO;

    /**
     * 人才简历-职称及职业资格
     */
    @ApiModelProperty(value="人才简历-职称及职业资格")
    private List<RecruitmentTitleDTO> recruitmentTitleDTO;

    /**
     * 人才简历-培训经历
     */
    @ApiModelProperty(value="人才简历-培训经历")
    private List<RecruitmentTrainDTO> recruitmentTrainDTO;

    /**
     * 人才简历-人才活动
     */
    @ApiModelProperty(value="人才简历-人才活动")
    List<RecruitmentActivitiDTO> recruitmentActivitiDTO;

}
