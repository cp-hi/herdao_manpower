package net.herdao.hdp.manpower.mpclient.dto.jobLevel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName JobGradeShortDTO
 * @Description JobGradeShortDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 14:53
 * @Version 1.0
 */

@Data
@ApiModel(value = "职等管理-下拉列表")
public class JobGradeShortDTO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("职等名称")
    private String jobGradeName;
    @ApiModelProperty("职等编码")
    private String jobGradeCode;
    @ApiModelProperty("所属集团")
    private Long groupId;
}
