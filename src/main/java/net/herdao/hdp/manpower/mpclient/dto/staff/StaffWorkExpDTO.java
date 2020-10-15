package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "工作经历")
public class StaffWorkExpDTO {

    private Long id;

    @ApiModelProperty(value="开始时间")
    private LocalDateTime beginDate;

    @ApiModelProperty(value="结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value="单位名称")
    private String companyName;

    @ApiModelProperty(value="中心/项目")
    private String orgName;

    @ApiModelProperty(value="岗位")
    private String post;

    @ApiModelProperty(value="承担角色")
    private String role;

    @ApiModelProperty(value="下属人数")
    private Integer subordinates;

    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;

    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value="最后修改人")
    private String modifierName;
}
