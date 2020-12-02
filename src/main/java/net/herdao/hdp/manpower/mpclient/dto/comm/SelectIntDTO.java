package net.herdao.hdp.manpower.mpclient.dto.comm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  
@AllArgsConstructor
@ApiModel(value = "selectIntDTO")
public class SelectIntDTO {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="value")
    private Long value;	
	
	@ApiModelProperty(value="label")
    private String label;
	
	@ApiModelProperty(value="children")
	private Object children;
}
