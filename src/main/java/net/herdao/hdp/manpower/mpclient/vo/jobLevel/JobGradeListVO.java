package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName JobGradeListDTO
 * @Description JobGradeListDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 21:47
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "职等管理-列表")
public class JobGradeListVO {
    @ApiModelProperty("id")
    private Long id;

    @ExcelProperty("职等名称")
    @ApiModelProperty("职等名称")
    private String jobGradeName;

    @ExcelProperty("在职员工数")
    @ApiModelProperty("在职员工数")
    private Integer onJobStaffs;

    @ExcelProperty("职级数")
    @ApiModelProperty("职级数")
    private Integer jobLevels;

    @ExcelProperty("描述")
    @ApiModelProperty("描述")
    private String description;

    @ExcelProperty("创建情况")
    @ApiModelProperty("创建情况")
    @DtoField(objField = {"creatorName", "createdTime"}, interpolation = "{1:\"于\",3:\"创建\"}")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty("最近更新情况")
    @DtoField(objField = {"modifierName", "modifiedTime"}, interpolation = "{1:\"于\",3:\"更新\"}")
    private String lastUpdatedInfo;
}
