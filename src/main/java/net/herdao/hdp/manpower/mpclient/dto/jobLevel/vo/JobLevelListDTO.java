package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

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
@ApiModel(value = "职级管理-列表")
public class JobLevelListDTO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;

    @ApiModelProperty(value = "职级编码")
    private String jobLevelCode;

    @ApiModelProperty(value = "在职员工数")
    private Integer onJobStaffs;
    @ApiModelProperty(value = "职等名称")
    private String jobGradeName;
    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建情况")
    @DtoField(joinFields = {"creatorName", "createdTime"}, symbol = " ", suffix = "创建")
    private String createdInfo;

    @ApiModelProperty(value = "最近更新情况")
    @DtoField(joinFields = {"modifierName", "modifiedTime"}, symbol = " ", suffix = "更新")
    private String lastUpdateInfo;

}