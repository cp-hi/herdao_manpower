package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "新增/修改员工")
public class StaffDetailJobDTO {

    private Long id;

    @ApiModelProperty(value="入职本公司日期")
    private LocalDateTime entryTime;

    @ApiModelProperty(value="试用期")
    private Long probPeriod;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

}
