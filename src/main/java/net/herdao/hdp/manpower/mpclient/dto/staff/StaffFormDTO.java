package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@ApiModel(value = "新增/修改员工")
public class StaffFormDTO {

    public StaffFormDTO(){}

    @ApiModelProperty(value="基本信息")
    StaffFormBaseDTO baseObj;

    @ApiModelProperty(value="入职信息")
    StaffFormJobDTO jobObj;
}
