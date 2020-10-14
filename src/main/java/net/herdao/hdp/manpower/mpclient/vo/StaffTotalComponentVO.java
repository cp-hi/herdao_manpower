package net.herdao.hdp.manpower.mpclient.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "部门/组织员工数")
public class StaffTotalComponentVO {

	@ApiModelProperty(value = "部门/组织编码")
	private String orgCode;
	
	@ApiModelProperty(value = "员工人数")
	private Integer total;
}
