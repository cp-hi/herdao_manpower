package net.herdao.hdp.manpower.mpclient.vo.jobLevel;

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
public class JobGradeFormVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "职等名称",required = true)
    private String jobGradeName;

    @ApiModelProperty(value = "职衔")
    private String jobTitle;

    @ApiModelProperty(value = "所属集团",required = true)
    private Long groupId;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty("描述")
    private String description;

}
