package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "任职轨迹")
public class StaffJobTravelDTO {

    private Long id;

    @ApiModelProperty(value="人员外键")
    private Long staffId;

    @ApiModelProperty(value="异动类型")
    private String tranType;

    @ApiModelProperty(value="异动时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tranTime;

    @ApiModelProperty(value="调往公司名称")
    private String outUnitName;

    @ApiModelProperty(value="调往中心名称")
    private String outCenterName;

    @ApiModelProperty(value="岗位外键")
    private Long postId;

    @ApiModelProperty(value="岗位")
    private String postName;

}
