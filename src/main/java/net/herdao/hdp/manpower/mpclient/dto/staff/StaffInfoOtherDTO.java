package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "其他个人信息")
public class StaffInfoOtherDTO {

    private Long id;

    @ApiModelProperty(value="是否集团统招应届生")
    private String admissionGraduates;

    @ApiModelProperty(value="加入或迁移公司集体户时间")
    private LocalDateTime collectiveHouseholdsTime;

    @ApiModelProperty(value="档案挂靠情况")
    private String archivesSituation;

    @ApiModelProperty(value="公司内部人脉关系")
    private String internalConnections;

    @ApiModelProperty(value="是否接受外派")
    private Boolean acceptedAssignment;

    @ApiModelProperty(value="可接受外派地点")
    private String assignmentLocations;

}
