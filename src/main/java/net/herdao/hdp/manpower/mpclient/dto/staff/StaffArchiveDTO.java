package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

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

    @ApiModelProperty(value="入职本公司日期")
    private LocalDateTime entryTime;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @ApiModelProperty(value="E-mail")
    private String email;

    @ApiModelProperty(value="出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value="籍贯")
    private String birthplace;

    @ApiModelProperty(value="毕业时间")
    private LocalDateTime graduatedTime;

    @ApiModelProperty(value="紧急联系人")
    private String emergencyContacts;

    @ApiModelProperty(value="紧急联系电话")
    private String emergencyPhone;

}
