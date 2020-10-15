package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "劳资情况")
public class StaffWelfareDTO {

    private Long id;

    @ApiModelProperty(value="社保类型")
    private String securityType;

    @ApiModelProperty(value="个人社保号（省）")
    private String socialSecurityNumberpRovince;

    @ApiModelProperty(value="个人社保号（市）")
    private String socialSecurityNumberCity;

    @ApiModelProperty(value="社保封存时间")
    private LocalDateTime securityArchiveTime;

    @ApiModelProperty(value="实际社保购买单位")
    private String securityUnit;

    @ApiModelProperty(value="参保日期")
    private LocalDateTime insuredTime;

    @ApiModelProperty(value="中间停缴社保年份")
    private BigDecimal interruptYear;

    @ApiModelProperty(value="是否交公积金")
    private Boolean funding;

    @ApiModelProperty(value="实际公积金购买单位")
    private String fundUnit;

    @ApiModelProperty(value="公积金封存时间")
    private LocalDateTime fundArchiveTime;

    @ApiModelProperty(value="公积金银行名称")
    private String fundArchiveBankName;

    @ApiModelProperty(value="公积金银行帐号")
    private String fundArchiveBankAccount;

    @ApiModelProperty(value="工资发放形式")
    private String paySalaryType;

    @ApiModelProperty(value="是否发放福利")
    private Boolean welfare;

    @ApiModelProperty(value="工资开户银行帐户所在地")
    private String bankAddress;

    @ApiModelProperty(value="实际工资发放单位")
    private String paidUnit;

    @ApiModelProperty(value="工资开户银行名称")
    private String bankName;

    @ApiModelProperty(value="工资开户银行帐号")
    private String bankAccount;

    @ApiModelProperty(value="收款银行支行名称")
    private String dueBankName;

    @ApiModelProperty(value="收款银行支行编码")
    private String dueBankCode;
}
