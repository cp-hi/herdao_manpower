
package net.herdao.hdp.manpower.mpclient.vo.organization;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门/组织树VO
 *
 * @author shuling
 * @date 2020-10-18 10:37:22
 */
@Data
@ApiModel(value = "部门/组织树VO")
public class OrganizationTreeVO {
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;
	/**
	 * 组织名称
	 */
	@ApiModelProperty(value = "组织名称")
	private String orgName;

	/**
	 * 组织编码
	 */
	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	/**
	 * 是否叶子节点 值： 1 是、 值 0 否
	 */
	@ApiModelProperty(value = "是否叶子节点（值： true 是、 值： false 否）")
	private Boolean leafNode;
	
	/**
	 * 子组织信息
	 */
	@ApiModelProperty(value = "子组织信息")
	List<OrganizationTreeVO> organizationChildrens;
}
