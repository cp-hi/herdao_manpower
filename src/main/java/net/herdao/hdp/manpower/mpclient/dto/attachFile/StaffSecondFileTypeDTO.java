package net.herdao.hdp.manpower.mpclient.dto.attachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "二级修改/新增")
public class StaffSecondFileTypeDTO {

	@ApiModelProperty(value = "itemId 修改时使用")
	private Integer id;
	
	@ApiModelProperty(value = "value值 修改时使用")
	private String value;
	
	@ApiModelProperty(value = "字典父表ID")
	private Integer dictId;
	
	@ApiModelProperty(value = "名称")
	private String label;
	
	@ApiModelProperty(value = "类型")
	private String type;
	
}
