package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "最高教育经历")
public class StaffEducationLastDTO {

    private Long id;

    @ApiModelProperty(value="最高学历")
    private String educationDegree;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="毕业时间")
    private LocalDate graduatedTime;

    @ApiModelProperty(value="毕业院校")
    private String graduatedFrom;

    @ApiModelProperty(value="专业")
    private String professional;
}
