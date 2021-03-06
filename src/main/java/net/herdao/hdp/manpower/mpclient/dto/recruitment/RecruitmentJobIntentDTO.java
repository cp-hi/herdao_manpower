
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 编辑人才简历-求职意向-DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "编辑人才简历-求职意向-DTO")
public class RecruitmentJobIntentDTO  {

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 工作年限
     */
    @ApiModelProperty(value="工作年限")
    private String workAge;

    /**
     * 应聘组织
     */
    @ApiModelProperty(value="应聘组织")
    private String orgName;

    /**
     * 应聘岗位
     */
    @ApiModelProperty(value="应聘岗位")
    private String intentionPostName;

    /**
     * 最低薪资（月薪/年薪）
     */
    @ApiModelProperty(value="最低薪资（月薪/年薪）")
    private String minimumLevelincome;

    /**
     * 期望薪资（月薪/年薪）
     */
    @ApiModelProperty(value="期望薪资（月薪/年薪）")
    private String expectedLevelincome;

    /**
     * 目标岗位/职业规划
     */
    @ApiModelProperty(value="目标岗位/职业规划")
    private String careerPlan;

    /**
     * 文字能力
     */
    @ApiModelProperty(value="文字能力")
    private String writingAbility;

    /**
     * 专业能力
     */
    @ApiModelProperty(value="专业能力")
    private String professionalCompetence;

    /**
     * 管理能力
     */
    @ApiModelProperty(value="管理能力")
    private String management;

    /**
     * 外语能力
     */
    @ApiModelProperty(value="外语能力")
    private String foreignLanguages;

    /**
     * 计算机能力
     */
    @ApiModelProperty(value="计算机能力")
    private String computerProficiency;

    /**
     * 主要社会资源
     */
    @ApiModelProperty(value="主要社会资源")
    private String resources;

    /**
     * 职称及职业资格
     */
    @ApiModelProperty(value="职称及职业资格")
    private String title;

    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String assessmentUnit;

    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String professionalQualifications;

    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualificationUnit;

    /**
     * 资历评价
     */
    @ApiModelProperty(value="资历评价")
    private String qualification;

    /**
     * 目标工作地
     */
    @ApiModelProperty(value="目标工作地")
    private String address;

    /**
     * 其他要求
     */
    @ApiModelProperty(value="其他要求")
    private String otherRequest;

    /**
     * 应聘聘组织ID
     */
    @ApiModelProperty(value="应聘聘组织ID")
    private Long orgId;

    /**
     * 参加工作日期-LocalDateTime
     */
    @ApiModelProperty(value="参加工作日期-LocalDateTime",hidden = true)
    private LocalDateTime workdateLocal;

    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    private Long workdate;

    /**
     * 专业经验-LocalDateTime
     */
    @ApiModelProperty(value="专业经验-LocalDateTime",hidden = true)
    private LocalDateTime professionalExperienceLocal;

    /**
     * 专业经验
     */
    @ApiModelProperty(value="专业经验")
    private Long professionalExperience;

    /**
     * 管理经验-LocalDateTime
     */
    @ApiModelProperty(value="管理经验-LocalDateTime")
    private LocalDateTime managementExperienceLocal;

    /**
     * 管理经验
     */
    @ApiModelProperty(value="管理经验")
    private Long managementExperience;

    /**
     * 房地产行业经验-LocalDateTime
     */
    @ApiModelProperty(value="房地产行业经验-LocalDateTime",hidden = true)
    private LocalDateTime realEstateExperienceLocal;

    /**
     * 房地产行业经验
     */
    @ApiModelProperty(value="房地产行业经验")
    private Long realEstateExperience;

    /**
     * 可到职日期-LocalDateTime
     */
    @ApiModelProperty(value="可到职日期-LocalDateTime",hidden = true)
    private LocalDateTime inductionTimeLocal;

    /**
     * 可到职日期
     */
    @ApiModelProperty(value="可到职日期")
    private Long inductionTime;

}
