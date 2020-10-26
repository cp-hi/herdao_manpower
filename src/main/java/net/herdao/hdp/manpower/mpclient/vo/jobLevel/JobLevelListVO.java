package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName JobLevelListDTO
 * @Description JobLevelListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 18:19
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "职级管理-列表")
public class JobLevelListVO {
    @ApiModelProperty("id")
    private Long id;

    @ExcelProperty("职级名称")
    @ApiModelProperty("职级名称")
    private String jobLevelName;

    @ExcelProperty("职级编码")
    @ApiModelProperty("职级编码")
    private String jobLevelCode;

    @ExcelProperty("在职员工数")
    @ApiModelProperty("在职员工数")
    private Integer onJobStaffs;

    @ExcelProperty("职等名称")
    @ApiModelProperty("职等名称")
    @DtoField(objField = "jobGrade.jobGradeName")
    private String jobGradeName;

    @ExcelProperty("排序")
    @ApiModelProperty("排序")
    private Integer sortNo;

    @ExcelProperty("描述")
    @ApiModelProperty("描述")
    private String description;

    @ExcelProperty("创建情况")
    @ApiModelProperty("创建情况")
    @DtoField(objField = {"creatorName", "createdTime"}, mapFix = "{1:\"于\",3:\"创建\"}")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty("最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, mapFix = "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;
}
