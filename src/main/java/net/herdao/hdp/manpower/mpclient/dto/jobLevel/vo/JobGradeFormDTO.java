package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName JobGradeFormDTO
 * @Description JobGradeFormDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 21:47
 * @Version 1.0
 */
@Data
@ApiModel(value = "职等管理-表单，用于新增修改")
public class JobGradeFormDTO {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "职等名称")
    private String jobGradeName;

    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "描述")
    private String description;

}
