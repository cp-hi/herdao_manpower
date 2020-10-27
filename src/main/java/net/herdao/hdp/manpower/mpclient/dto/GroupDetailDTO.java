package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "集团表单")
public class GroupDetailDTO {

    private Long id;

    @ApiModelProperty(value="集团名称")
    private String groupName;

    @ApiModelProperty(value="集团编码")
    private String groupCode;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ApiModelProperty(value="对应组织编码")
    private String orgCode;

    @ApiModelProperty(value="排序")
    private Integer sortNo;

    @ApiModelProperty(value="绩效计算标准")
    private String achieveCalculateStandard;

    @ApiModelProperty(value="备注")
    private String remark;

}
