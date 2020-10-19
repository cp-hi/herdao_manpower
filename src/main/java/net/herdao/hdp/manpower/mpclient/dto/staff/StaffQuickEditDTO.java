package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "花名册快速编辑")
public class StaffQuickEditDTO {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="证件类型")
    private String idType;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @ApiModelProperty(value="入职本公司日期")
    private LocalDateTime entryTime;
}
