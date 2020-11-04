package net.herdao.hdp.manpower.mpclient.dto.comm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "selectDTO")
public class SelectDTO {
	
	@ApiModelProperty(value="value")
    private String value;	
	
	@ApiModelProperty(value="label")
    private String label;
	
	@ApiModelProperty(value="children")
	private Object children;
}
