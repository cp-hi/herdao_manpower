package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "职称及职业资格")
public class StaffCarreraDTO {

    private Long id;

    @ApiModelProperty(value="职称")
    private String title;

    @ApiModelProperty(value="职业资格")
    private String professionalQualifications;

    @ApiModelProperty(value="评定单位")
    private String assessmentUnit;

    @ApiModelProperty(value="职称证号")
    private String titleCode;

    @ApiModelProperty(value="发证时间")
    private LocalDateTime certificationTime;

    @ApiModelProperty(value="资质挂靠单位")
    private String qualificationUnit;

}
