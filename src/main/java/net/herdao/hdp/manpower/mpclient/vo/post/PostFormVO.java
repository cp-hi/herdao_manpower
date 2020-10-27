package net.herdao.hdp.manpower.mpclient.vo.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;


/**
 * @ClassName PostFormVO
 * @Description PostFormVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 14:40
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位管理-表单信息，用于新增和编辑")
public class PostFormVO {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("岗位名称")
    private String postName;

    @ApiModelProperty("岗位编码")
    private String postCode;

    @ApiModelProperty("岗位序列ID")
    private Long postSeqId;

    @ApiModelProperty("岗位序列")
    @DtoField(objField = "postSeqDTO.postSeqName")
    private String postSeqName;

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
    private Long pipelineId;

    @ApiModelProperty("管线")
    @DtoField(objField = "pipeline.pipelineName")
    private String pipelineName;

    @ApiModelProperty("职等")
    private String jobGradeId;

    @ApiModelProperty("职等")
    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @ApiModelProperty("职级")
    private Long jobLevelId1;

    @ApiModelProperty("职级")
    private Long jobLevelId2;

    @ApiModelProperty("职级")
    @DtoField(objField = {"jobLevel1.id", "jobLevel2.id"}, symbol = ",")
    private String jobLevelId;

    @ApiModelProperty("职级")
    @DtoField(objField = {"jobLevel1.jobLevelName", "jobLevel2.jobLevelName"}, symbol = "~")
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
    @DtoField(dictField = "GWZZLX.orgType")
    private String orgTypeName;

    //endregion

    @ApiModelProperty("岗位职责")
    private String postDescr;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "是否单职级")
    private Boolean singleJobLevle;

    @ApiModelProperty(value = "是否停用")
    @DtoField(objField = "stop", converter =  "{true:\"1\",false:\"0\"}")
    private String stop;

    @ApiModelProperty(value = "是否停用")
    @DtoField(objField = "stop", converter = "{true:\"已停用\",false:\"已启用\"}")
    private String stopName;

    @ApiModelProperty(value = "停用日期")
    @DtoField(objField = "stopDate", pattern = "yyyy-MM-dd")
    private String stopDate;

    @ApiModelProperty(value = "启用日期")
    @DtoField(objField = "startDate", pattern = "yyyy-MM-dd")
    private String startDate;
}
