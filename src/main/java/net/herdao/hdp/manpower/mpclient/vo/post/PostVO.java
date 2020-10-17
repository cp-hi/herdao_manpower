package net.herdao.hdp.manpower.mpclient.vo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

import java.util.Date;


/**
 * @ClassName PostVO
 * @Description PostVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 20:06
 * @Version 1.0
 */
@Data
public class PostVO {

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

    @ApiModelProperty("管线")
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

    @ApiModelProperty(value = "创建人名称")
    private String creatorName;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(value = "修改ID")
    private Long modifierId;
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;

}
