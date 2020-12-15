package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "流程回调DTO")
public class WorkflowNotifyDTO {

	
	@ApiModelProperty(value="业务记录ID",required = true)
	private String recordId;
	
	@ApiModelProperty(value="流程类型(录用审批)",required = true)
	private String flowType;
	
	@ApiModelProperty(value="状态",required = true)
	private Integer status;
}
