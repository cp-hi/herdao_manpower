package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "紧急联系人")
public class StaffEmergencyDTO {

    private Long id;

    @ApiModelProperty(value="紧急联系人")
    private String emergencyContacts;

    @ApiModelProperty(value="紧急联系电话")
    private String emergencyPhone;
}
