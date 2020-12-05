
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;

import java.util.List;

/**
 * 编辑人才简历-基础信息-详情
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "编辑人才简历-基础信息-详情")
public class RecruitmentEditDetailsDTO {

    /**
     * 人才简历-获奖资格-详情
     */
    @ApiModelProperty(value="人才简历-获奖资格-详情")
    List<RecruitmentAwardsDTO> recruitmentAwardsDTO;

    /**
     * 人才简历-教育经历-详情
     */
    @ApiModelProperty(value="人才简历-获奖资格-详情")
    List<RecruitmentEduDTO>  recruitmentEduDTO;

    /**
     * 人才简历-家庭状况-详情
     */
    @ApiModelProperty(value="人才简历-家庭状况-详情")
    List<RecruitmentFamilyDTO>  recruitmentFamilyDTO;


}
