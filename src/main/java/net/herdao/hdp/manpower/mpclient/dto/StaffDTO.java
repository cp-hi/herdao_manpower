package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "员工信息")
public class StaffDTO {

    private Long id;

    @ApiModelProperty(value="用户id")
    private Long userId;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="最近更新人")
    private String modifierName;

}
