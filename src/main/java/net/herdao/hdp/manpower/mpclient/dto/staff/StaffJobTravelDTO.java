package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "任职轨迹")
public class StaffJobTravelDTO {

    private Long id;

    @ApiModelProperty(value="用户外键")
    private Long userId;

    @ApiModelProperty(value="任职日期")
    private LocalDateTime startDate;

    @ApiModelProperty(value="免职日期")
    private LocalDateTime endDate;

    @ApiModelProperty(value="所属部门外键")
    private Long orgDeptId;

    @ApiModelProperty(value="岗位外键")
    private Long postId;
}
