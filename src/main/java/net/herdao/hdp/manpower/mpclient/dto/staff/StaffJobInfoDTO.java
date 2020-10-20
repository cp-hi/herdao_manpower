package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "在职信息")
public class StaffJobInfoDTO {

    private Long id;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty(value="入职本公司日期")
    private LocalDate entryTime;

    @ApiModelProperty(value="实际工作地点")
    private String actualCity;

    @ApiModelProperty(value="办公电话")
    private String officePhone;

    @ApiModelProperty(value="E-mail")
    private String email;

    @ApiModelProperty(value="非连续工作时间（不计算年假的年限）")
    private BigDecimal noWorkingSeniority;
}
