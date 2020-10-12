package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "花名册列表")
public class StaffListDto {

    private Long id;

    @ApiModelProperty(value="用户id")
    private Long userId;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="入职本公司日期")
    private LocalDateTime entryTime;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="国籍")
    private String country;

    @ApiModelProperty(value="民族")
    private String nation;

    @ApiModelProperty(value="生育状况")
    private String fertility;

    @ApiModelProperty(value="婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value="最高学历")
    private String educationDegree;

    @ApiModelProperty(value="户口类型")
    private String accountType;

    @ApiModelProperty(value="毕业时间")
    private LocalDateTime graduatedTime;

    @ApiModelProperty(value="毕业院校")
    private String graduatedFrom;

    @ApiModelProperty(value="专业")
    private String professional;

    @ApiModelProperty(value = "修改人名称" )
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;
}
