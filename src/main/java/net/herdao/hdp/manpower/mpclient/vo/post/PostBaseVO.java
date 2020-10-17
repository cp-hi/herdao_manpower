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
@ApiModel(value = "岗位基础信息")
public class PostBaseVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位编码")
    private String postCode;

    @ApiModelProperty("岗位序列")
    @DtoField(objField = "postSeq.postSeqName")
    private String postSeqName;

    @ApiModelProperty("所属集团")
    @DtoField(objField = "group.groupName")
    private String groupName;

    @ApiModelProperty("版块")
    @DtoField(objField = "section.sectionName")
    private String sectionName;

    @ApiModelProperty("管线")
    @DtoField(objField = "pipeline.pipelineName")
    private String pipelineName;

    @ApiModelProperty("职等")
    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @ApiModelProperty("职级")
    @DtoField(objField = "jobLevel1.jobLevelName")
    private String jobLevelName;

    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;

    @ApiModelProperty("岗位薪酬级别")
    @DtoField(dictField = "XCJB.postLevel")
    private String postLevel;

    @ApiModelProperty("年终奖薪酬比例")
    @DtoField(dictField = "XCBL.yearPayRatio")
    private String yearPayRatio;

    @ApiModelProperty("岗位组织级别")
    @DtoField(dictField = "YDJXGZBL.perforSalaryRatio")
    private String perforSalaryRatio;

    @ApiModelProperty("岗位组织级别")
    @DtoField(dictField = "GWZZLX.orgType")
    private String orgType;

    @ApiModelProperty(value = "岗位职责", required = true)
    private String postDescr;
    @ApiModelProperty(value = "备注")
    private String remark;
}
