package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "公司表单")
public class CompanyDetailDTO {

    private Long id;

    @ApiModelProperty(value="公司名称")
    private String companyName;

    @ApiModelProperty(value="公司编码")
    private String companyCode;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ApiModelProperty(value="目标财务系统")
    private String targetSys;

    @ApiModelProperty(value="保险标准")
    private String securityStandard;

    @ApiModelProperty(value="所属城市ID")
    private Long cityId;

    @ApiModelProperty(value="公积金标准ID")
    private Long cityFundId;

    @ApiModelProperty(value="工会标准")
    private BigDecimal lohnkostenRate;

    @ApiModelProperty(value="保险科目")
    private String securitySubject;

    @ApiModelProperty(value="付款账号")
    private String payAcctNo;

    @ApiModelProperty(value="排序")
    private Long sort;

    @ApiModelProperty(value="纳税人识别号")
    private String taxerNo;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="扩展字段1")
    private String field1;

    @ApiModelProperty(value="扩展字段2")
    private String field2;

    @ApiModelProperty(value="扩展字段3")
    private String field3;

    @ApiModelProperty(value="扩展字段4")
    private String field4;

    @ApiModelProperty(value="扩展字段5")
    private String field5;
}
