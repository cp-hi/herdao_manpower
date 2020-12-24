
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;

import java.util.List;

/**
 * 人才简历
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历")
public class RecruitmentDetailsDTO {

    /**
     * 人才简历-个人基本情况
     */
    @ApiModelProperty(value="人才简历-个人基本情况")
    private RecruitmentPersonDTO recruitmentPersonDTO;

    /**
     * 人才简历-从业情况与求职意向
     */
    @ApiModelProperty(value="人才简历-从业情况与求职意向")
    private RecruitmentIntentDTO recruitmentIntentDTO;

    /**
     * 人才简历-工作经历
     */
    @ApiModelProperty(value="人才简历-工作经历")
    private List<RecruitmentWorkExperienceDTO> recruitmentWorkexperienceDTO;

    /**
     * 人才简历-最高教育经历
     */
    @ApiModelProperty(value="人才简历-最高教育经历")
    private RecruitmentTopEduDTO recruitmentTopEduDTO;

    /**
     * 人才简历-家庭情况
     */
    @ApiModelProperty(value="人才简历-家庭情况")
    List<RecruitmentFamilyDTO> recruitmentFamilyDTO;

    /**
     * 人才简历-获奖资格
     */
    @ApiModelProperty(value="人才简历-获奖资格")
    List<RecruitmentAwardsDTO> recruitmentAwardsDTO;

    /**
     * 人才简历-录用情况
     */
    @ApiModelProperty(value="人才简历-录用情况")
    List<RecruitmentEmployeeDTO> recruitmentEmployeeDTO;

}
