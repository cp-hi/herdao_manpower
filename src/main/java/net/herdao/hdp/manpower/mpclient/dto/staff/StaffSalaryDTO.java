package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "薪资信息")
public class StaffSalaryDTO {

    private Long id;

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

    @ApiModelProperty(value="工资发放形式名称")
    private String paySalaryTypeName;
}
