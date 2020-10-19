package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

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
public class JobGradeListDTO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ExcelProperty("职等名称")
    @ApiModelProperty(value = "职等名称")
    private String jobGradeName;

    @ExcelProperty("在职员工数")
    @ApiModelProperty(value = "在职员工数")
    private Integer onJobStaffs;

    @ExcelProperty("职级数")
    @ApiModelProperty(value = "职级数")
    private Integer jobLevels;

    @ExcelProperty("描述")
    @ApiModelProperty(value = "描述")
    private String description;

    @ExcelProperty("创建情况")
    @ApiModelProperty(value = "创建情况")
    @DtoField(joinFields = {"creatorName", "createdTime"}, symbol = " ", suffix = "创建")
    private String createdInfo;

    @ExcelProperty("最近更新情况")
    @ApiModelProperty(value = "最近更新情况")
    @DtoField(joinFields = {"modifierName", "modifiedTime"}, symbol = " ", suffix = "更新")
    private String lastUpdateInfo;
}
