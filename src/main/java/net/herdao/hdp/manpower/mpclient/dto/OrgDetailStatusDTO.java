package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "组织状态")
public class OrgDetailStatusDTO {
	
	/**
     * 是否停用 页面展示
     */
    @ApiModelProperty(value="启用状态")
    private String enableStatus;

    /**
     * 启用日期
     */
    @ApiModelProperty(value="启用日期")
    private String startDate;

    /**
     * 停用日期
     */
    @ApiModelProperty(value="停用日期")
    private String stopDate;

}
