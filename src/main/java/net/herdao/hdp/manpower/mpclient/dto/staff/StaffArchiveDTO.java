package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "个人档案")
public class StaffArchiveDTO {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @ApiModelProperty(value="入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate entryTime;

    @ApiModelProperty(value="转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate regularTime;

    @ApiModelProperty(value="证件类型")
    private String idType;

    @ApiModelProperty(value="证件号码")
    private String idNumber;

    @ApiModelProperty(value="E-mail")
    private String email;

    @ApiModelProperty(value="出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(value="籍贯")
    private String birthplace;

    @ApiModelProperty(value="毕业时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty(value="紧急联系人")
    private String emergencyContacts;

    @ApiModelProperty(value="紧急联系电话")
    private String emergencyPhone;



    @ApiModelProperty(value="任职类型名称")
    private String jobTypeName;

    @ApiModelProperty(value="人员归属范围名称")
    private String staffScopeName;

    @ApiModelProperty(value="证件类型名称")
    private String idTypeName;
}
