package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "组织架构图")
public class OrgChartDTO {

    private Long id;

    @ApiModelProperty(value="组织编码")
    private String orgCode;

    @ApiModelProperty(value="组织名称")
    private String orgName;

    @ApiModelProperty(value="组织负责人姓名")
    private String orgChargeName;

    @ApiModelProperty(value="组织负责人工号")
    private String orgChargeWorkNo;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="是否有子节点")
    private Boolean hasChild;
}
