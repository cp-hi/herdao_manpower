package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("岗位员工数量")
public class PostStaffCountDTO {
    @ApiModelProperty("岗位组织表id")
    private Long PostOrgId;
    @ApiModelProperty("员工数量")
    private Integer staffCount;
}
