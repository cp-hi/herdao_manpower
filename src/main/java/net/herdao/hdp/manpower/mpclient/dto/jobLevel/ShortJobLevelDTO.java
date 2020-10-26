package net.herdao.hdp.manpower.mpclient.dto.jobLevel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName ShortJobLevelDTO
 * @Description ShortJobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 19:33
 * @Version 1.0
 */
@Data
@Builder
@ApiModel(value = "简短职等/职级")
public class ShortJobLevelDTO {
    @ApiModelProperty("职等名称")
    private String jobGradeName;
    @ApiModelProperty("职级名称")
    private String jobLevelName;
}
