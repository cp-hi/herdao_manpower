
package net.herdao.hdp.manpower.mpclient.vo.orgmodifyrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织属性变更VO
 *
 * @author shuling
 * @date 2020-20-21 10:01:48
 */
@Data
@ApiModel(value = "组织属性变更")
public class OrgChangePropertiesVO {
	
	@ApiModelProperty(value = "组织名称")
	private String orgName;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
}
