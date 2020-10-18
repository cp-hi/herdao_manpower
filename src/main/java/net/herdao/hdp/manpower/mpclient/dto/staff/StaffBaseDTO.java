package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "人员基本信息")
public class StaffBaseDTO {

    private Long id;

    @ApiModelProperty(value="相片地址")
    private String photoAddr;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="办公电话")
    private String officePhone;

    @ApiModelProperty(value="任职类型")
    private String jobType;
}
