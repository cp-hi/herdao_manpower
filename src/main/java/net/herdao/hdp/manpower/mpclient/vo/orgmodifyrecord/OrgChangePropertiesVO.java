
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
@ApiModel(value = "属性变更")
public class OrgChangePropertiesVO {
	
	/**
	 * 现组织id
	 */
	@ApiModelProperty(value = "现组织id")
	private Long curOrgId;
	/**
	 * 现上级组织id
	 */
	@ApiModelProperty(value = "现上级组织id")
	private Long curOrgParentId;
	/**
	 * 原组织id
	 */
	@ApiModelProperty(value = "原组织id")
	private Long oldOrgId;
	/**
	 * 原上级组织ID
	 */
	@ApiModelProperty(value = "原上级组织id")
	private Long oldOrgParentId;

}
