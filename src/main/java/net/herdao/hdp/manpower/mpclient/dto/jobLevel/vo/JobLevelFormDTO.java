package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "职级管理-新增修改表单")
public class JobLevelFormDTO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;

    @ApiModelProperty(value = "职等ID")
    private Long jobGradeName;

    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "描述")
    private String description;
}