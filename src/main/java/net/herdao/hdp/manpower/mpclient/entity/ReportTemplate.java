package net.herdao.hdp.manpower.mpclient.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "报表模板")
public class ReportTemplate {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "报表名称")
    private String name;
    @ApiModelProperty(value = "报表编码")
    private String code;
    @ApiModelProperty(value = "报表文件路径")
    private String templateuri;
    @ApiModelProperty(value = "启用状态")
    private Integer isStop;
    @ApiModelProperty(value = "描述")
    private String desc;
}
