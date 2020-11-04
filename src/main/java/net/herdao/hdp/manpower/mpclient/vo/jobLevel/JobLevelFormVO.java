package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

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
public class JobLevelFormVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "职级名称",required = true)
    private String jobLevelName;

    @ApiModelProperty("职等")
    private Long jobGradeId;

    @ApiModelProperty(value = "所属集团",required = true)
    private Long groupId;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty("描述")
    private String description;
}
