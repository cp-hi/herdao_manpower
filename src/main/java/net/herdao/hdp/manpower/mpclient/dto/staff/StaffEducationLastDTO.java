package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "最高教育经历")
public class StaffEducationLastDTO {

    private Long id;

    @ApiModelProperty(value="人员外键")
    private String staffId;

    @ApiModelProperty(value="学历")
    private String educationQua;

    @ApiModelProperty(value="学位")
    private String educationDegree;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="入学日期")
    private Date beginDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="毕业日期")
    private Date endDate;

    @ApiModelProperty(value="毕业院校")
    private String schoolName;

    @ApiModelProperty(value="专业")
    private String professional;

    @ApiModelProperty(value="学习形式")
    private String learnForm;

}
