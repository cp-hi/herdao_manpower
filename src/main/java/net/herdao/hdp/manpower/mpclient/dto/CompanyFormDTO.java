package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "公司表单")
public class CompanyFormDTO {

    private Long id;

    @ApiModelProperty(value="公司名称")
    private String companyName;

    @ApiModelProperty(value="公司编码")
    private String companyCode;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ApiModelProperty(value="所在城市ID")
    private Long cityId;

    @ApiModelProperty(value="公积金标准ID")
    private Long cityFundId;

    @ApiModelProperty(value="工会费比例")
    private BigDecimal lohnkostenRate;

    @ApiModelProperty(value="支付公司账号")
    private String payAcctNo;

    @ApiModelProperty(value="纳税人识别码")
    private String taxerNo;
}
