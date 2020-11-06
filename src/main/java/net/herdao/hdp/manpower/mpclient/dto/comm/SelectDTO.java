package net.herdao.hdp.manpower.mpclient.dto.comm;

import java.io.Serializable;

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
@ApiModel(value = "selectDTO")
public class SelectDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="value")
    private String value;	
	
	@ApiModelProperty(value="label")
    private String label;
	
	@ApiModelProperty(value="children")
	private Object children;
}
