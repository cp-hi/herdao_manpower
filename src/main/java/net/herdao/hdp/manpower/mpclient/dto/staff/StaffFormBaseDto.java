package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "新增/修改员工")
public class StaffFormBaseDto {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="E-mail")
    private String email;

}
