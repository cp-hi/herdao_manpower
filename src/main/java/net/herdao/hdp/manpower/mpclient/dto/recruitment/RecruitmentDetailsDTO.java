
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 人才简历-详情
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-详情")
public class RecruitmentDetailsDTO {

    /**
     * 人才简历-个人基本情况-详情
     */
   @ApiModelProperty(value="人才简历-个人基本情况-详情")
   private RecruitmentPersonDTO recruitmentPersonDTO;

    /**
     * 人才简历-从业情况与求职意向-详情
     */
    @ApiModelProperty(value="人才简历-从业情况与求职意向-详情")
    private RecruitmentIntentDTO recruitmentIntentDTO;

    /**
     * 人才简历-工作经历-详情
     */
    @ApiModelProperty(value="人才简历-工作经历-详情")
    private RecruitmentWorkexperienceDTO recruitmentWorkexperienceDTO;

    /**
     * 人才简历-最高教育经历-详情
     */
    @ApiModelProperty(value="人才简历-最高教育经历-详情")
    private RecruitmentTopEduDTO recruitmentTopEduDTO;

    /**
     * 人才简历-家庭情况-详情
     */
    @ApiModelProperty(value="人才简历-家庭情况-详情")
    List<RecruitmentFamilyDTO> recruitmentFamilyDTO;

    /**
     * 人才简历-家庭情况-详情
     */
    @ApiModelProperty(value="人才简历-获奖资格-详情")
    List<RecruitmentAwardsDTO> recruitmentAwardsDTO;

}
