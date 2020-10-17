package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "最高教育经历")
public class StaffEducationLastDTO {

    private Long id;

    @ApiModelProperty(value="最高学历")
    private String educationDegree;

    @ApiModelProperty(value="毕业时间")
    private LocalDateTime graduatedTime;

    @ApiModelProperty(value="毕业院校")
    private String graduatedFrom;

    @ApiModelProperty(value="专业")
    private String professional;
}
