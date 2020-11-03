package net.herdao.hdp.manpower.mpclient.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;


/**
 * @ClassName PostVO
 * @Description PostVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/15 20:06
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "岗位管理-列表，用于列表展示")
public class PostListVO {

    @ApiModelProperty("id")
    private Long id;

    @ExcelProperty("岗位名称")
    @ApiModelProperty("岗位名称")
    private String postName;

    @ExcelProperty("岗位编码")
    @ApiModelProperty("岗位编码")
    private String postCode;

    @ExcelProperty("岗位序列")
    @ApiModelProperty("岗位序列")
    @DtoField(objField = {"postSeqDTO.parent.parent.postSeqName",
            "postSeqDTO.parent.postSeqName", "postSeqDTO.postSeqName"}, symbol = "-")
    private String postSeqName;

    @ExcelProperty("所属集团")
    @ApiModelProperty("所属集团")
//    @DtoField(objField = "group.groupName")
    private String groupName;

    @ExcelProperty("版块")
    @ApiModelProperty("版块")
//    @DtoField(objField = "section.sectionName")
    private String sectionName;

    @ExcelProperty("管线")
    @ApiModelProperty("管线")
//    @DtoField(objField = "pipeline.pipelineName")
    private String pipelineName;

    @ExcelProperty("职等")
    @ApiModelProperty("职等")
    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @ExcelProperty("职级")
    @ApiModelProperty("职级")
//    @DtoField(objField = {"jobLevel1.jobLevelName", "jobLevel2.jobLevelName"}, symbol = "~")
    @DtoField(objField = {"jobLevelName1", "jobLevelName2"}, symbol = "~")
    private String jobLevelName;

    @ExcelProperty("在职员工数")
    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ExcelProperty("岗位编制")
    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;


    @ExcelProperty("岗位薪酬级别")
    @ApiModelProperty("岗位薪酬级别")
    @DtoField(dictField = "XCJB.postLevel")
    private String postLevel;

    @ExcelProperty("年终奖薪酬比例")
    @ApiModelProperty("年终奖薪酬比例")
    @DtoField(dictField = "XCBL.yearPayRatio")
    private String yearPayRatio;

    @ExcelProperty("月度绩效工资比例")
    @ApiModelProperty("月度绩效工资比例")
    @DtoField(dictField = "YDJXGZBL.perforSalaryRatio")
    private String perforSalaryRatio;

    @ExcelProperty("岗位组织级别")
    @ApiModelProperty("岗位组织级别")
    @DtoField(dictField = "GWZZLX.orgType")
    private String orgType;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty("最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, mapFix = "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;

}
