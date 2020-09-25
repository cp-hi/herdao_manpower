package net.herdao.hdp.manpower.mpclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "员工信息")
public class StaffDto {

    private Long id;

    @ApiModelProperty(value="用户id")
    private Long userId;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="最近更新人")
    private String modifierName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;
}
