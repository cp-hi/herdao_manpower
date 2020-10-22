package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevel;

/**
 * @ClassName OKJobLevelDTO
 * @Description OKJobLevelDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 16:14
 * @Version 1.0
 */
@Data
public class OKJobLevelDTO   {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;
}
