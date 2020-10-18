package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "新增/编辑组织架构图")
public class OrgChartFormDTO {

    private Long id;

    @ApiModelProperty(value="组织名称")
    private String orgName;

    @ApiModelProperty(value="父ID")
    private Long parentId;

    @ApiModelProperty(value="组织类型")
    private String orgType;

    @ApiModelProperty(value="组织简称")
    private String orgSimpleDesc;

    @ApiModelProperty(value="组织负责人工号")
    private String orgChargeWorkNo;

    @ApiModelProperty(value="组织负责人姓名")
    private String orgChargeName;

    @ApiModelProperty(value="部门负责人岗位（负责岗位）")
    private String chargeOrg;

    @ApiModelProperty(value="组织描述")
    private String orgDesc;
}
