package net.herdao.hdp.manpower.mpclient.vo.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName PostBaseVO
 * @Description PostBaseVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 15:23
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位管理-基础信息")
public class PostBaseVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位编码")
    private String postCode;

    @ApiModelProperty("岗位序列")
    @DtoField(objField = "postSeqDTO.postSeqName")
    private String postSeqId;

    @ApiModelProperty("所属集团")
    private Long groupId;

    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    @ApiModelProperty("版块")
    private Long sectionId;

    @ApiModelProperty("版块")
    @DtoField(objField = "section.sectionName")
    private String sectionName;

    @ApiModelProperty("管线")
    private String pipelineId;

    @ApiModelProperty("管线")
    @DtoField(objField = "pipeline.pipelineName")
    private String pipelineName;

    @ApiModelProperty("职等")
    private String jobGradeId;

    @ApiModelProperty("职等")
    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @ApiModelProperty("职级")
    private String jobLevelId1;

    @ApiModelProperty("职级")
    @DtoField(objField = "jobLevel1.jobLevelName")
    private String jobLevelName;

    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;

    //region 字典

    @ApiModelProperty("岗位薪酬级别")
    private String postLevel;

    @ApiModelProperty("岗位薪酬级别")
    @DtoField(dictField = "XCJB.postLevel")
    private String postLevelName;

    @ApiModelProperty("年终奖薪酬比例")
    private String yearPayRatio;

    @ApiModelProperty("年终奖薪酬比例")
    @DtoField(dictField = "XCBL.yearPayRatio")
    private String yearPayRatioName;

    @ApiModelProperty("岗位组织级别")
    private String perforSalaryRatio;

    @ApiModelProperty("岗位组织级别")
    @DtoField(dictField = "YDJXGZBL.perforSalaryRatio")
    private String perforSalaryRatioName;

    @ApiModelProperty("岗位组织级别")
    private String orgType;

    @ApiModelProperty("岗位组织级别")
    private String orgTypeName;

    //endregion

    @ApiModelProperty("岗位职责")
    private String postDescr;

    @ApiModelProperty("备注")
    private String remark;
}
