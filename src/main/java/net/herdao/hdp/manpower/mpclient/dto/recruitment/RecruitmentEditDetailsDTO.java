
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    List<RecruitmentEditAwardsDTO> recruitmentEditAwardsDTO;

    /**
     * 人才简历-教育情况-详情
     */
    @ApiModelProperty(value="人才简历-教育情况-详情")
    List<RecruitmentEditEduDTO>  recruitmentEditEduDTO;

    /**
     * 人才简历-家庭状况-详情
     */
    @ApiModelProperty(value="人才简历-家庭状况-详情")
    List<RecruitmentEditFamilyDTO>  recruitmentEditFamilyDTO;

    /**
     * 人才简历-其他个人信息
     */
    @ApiModelProperty(value="人才简历-其他个人信息")
    RecruitmentEditOtherInfoDTO recruitmentEditOtherInfoDTO;

    /**
     * 人才简历-个人基本信息
     */
    @ApiModelProperty(value="人才简历-个人基本信息")
    RecruitmentEditBaseInfoDTO recruitmentEditBaseInfoDTO;


}
