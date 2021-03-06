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
            "postSeqDTO.parent.postSeqName", "postSeqDTO.postSeqName"}, separator = "-", delFix = "未分类")
    private String postSeqName;

    @ExcelProperty("所属集团")
    @ApiModelProperty("所属集团")
    private String groupName;

    @ExcelProperty("版块")
    @ApiModelProperty("版块")
    private String sectionName;

    @ExcelProperty("管线")
    @ApiModelProperty("管线")
    private String pipelineName;

    @ExcelProperty("职等")
    @ApiModelProperty("职等")
    @DtoField(objField = {"jobGradeName1", "jobGradeName2"}, separator = "~")
    private String jobGradeName;

    @ExcelProperty("职级")
    @ApiModelProperty("职级")
    @DtoField(objField = {"jobLevelName1", "jobLevelName2"}, separator = "~")
    private String jobLevelName;

    @ExcelProperty("在职员工数")
    @ApiModelProperty(value = "在职员工数")
    private Integer postAuthorized;

    @ExcelProperty("岗位编制")
    @ApiModelProperty(value = "岗位编制")
    private Integer onJobStaffs;


    @ExcelProperty("岗位薪酬级别")
    @ApiModelProperty("岗位薪酬级别")
//    @DtoField(dictField = "XCJB.postLevel")
    private String postLevelName;

    @ExcelProperty("年终奖薪酬比例")
    @ApiModelProperty("年终奖薪酬比例")
//    @DtoField(dictField = "XCBL.yearPayRatio")
    private String yearPayRatioName;

    @ExcelProperty("月度绩效工资比例")
    @ApiModelProperty("月度绩效工资比例")
//    @DtoField(dictField = "YDJXGZBL.perforSalaryRatio")
    private String perforSalaryRatioName;

    @ExcelProperty("岗位组织级别")
    @ApiModelProperty("岗位组织级别")
//    @DtoField(dictField = "GWZZLX.orgType")
    private String orgTypeName;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty("最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, interpolation = "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;

}
