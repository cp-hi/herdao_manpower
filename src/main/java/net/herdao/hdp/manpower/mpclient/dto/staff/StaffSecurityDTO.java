package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "社保信息")
public class StaffSecurityDTO {

    private Long id;

    @ApiModelProperty(value="社保类型")
    private String securityType;

    @ApiModelProperty(value="社保封存时间")
    private LocalDateTime securityArchiveTime;

    @ApiModelProperty(value="个人社保号（省）")
    private String socialSecurityNumberpRovince;

    @ApiModelProperty(value="个人社保号（市）")
    private String socialSecurityNumberCity;

    @ApiModelProperty(value="实际社保购买单位")
    private String securityUnit;

    @ApiModelProperty(value="参保日期")
    private LocalDateTime insuredTime;

    @ApiModelProperty(value="中间停缴社保年份")
    private BigDecimal interruptYear;
}
