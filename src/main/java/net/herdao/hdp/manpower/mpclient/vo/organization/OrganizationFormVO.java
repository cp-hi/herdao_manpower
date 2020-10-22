
package net.herdao.hdp.manpower.mpclient.vo.organization;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织VO
 *
 * @author shuling
 * @date 2020-10-18 10:37:22
 */
@Data
@ApiModel(value = "组织VO")
public class OrganizationFormVO {
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键id")
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
	 * 组织简称
	 */
	@ApiModelProperty(value = "组织简称")
	private String orgSimpleDesc;
	/**
	 * 上级组织id
	 */
	@ApiModelProperty(value = "上级组织id")
	private Long parentId;
	/**
	 * 上级组织名称
	 */
	@ApiModelProperty(value = "上级组织名称")
	private String parentOrgName;
	/**
	 * 上级组织
	 */
	@ApiModelProperty(value = "上级组织编码")
	private String parentOrgCode;
	/**
	 * 组织层（ 参考字典：“ZZCJ”）
	 */
	@ApiModelProperty(value = "组织层级")
	private String orgLevel;
	/**
	 * 组织类型（参考字典：“ZZLX”）
	 */
	@ApiModelProperty(value = "组织类型")
	private String orgType;
	/**
	 * 负责岗位id
	 */
	@ApiModelProperty(value = "负责岗位id")
	private Long postId;
	/**
	 * 负责岗位
	 */
	@ApiModelProperty(value = "负责岗位名称")
	private Long postName;
	/**
	 * 负责岗位
	 */
	@ApiModelProperty(value = "负责岗位编码")
	private Long postCode;
	/**
	 * 是否虚拟组织（取值待确认）
	 */
	@ApiModelProperty(value = "是否虚拟组织")
	private Boolean isVirtual;
	/**
	 * 人员编制（编制人员数）
	 */
	@ApiModelProperty(value = "人员编制")
	private Integer staffTotal;

	/**
	 * 是否项目/中心及以上 TODO 取值待确定
	 */
	@ApiModelProperty(value = "是否项目/中心及以上")
	private Boolean organizational;
	/**
	 * 福利类型（取福利标准主键）待确定取数 TOTO
	 */
	@ApiModelProperty(value = "福利类型")
	private String welfareType;
	/**
	 * 组织描述
	 */
	@ApiModelProperty(value = "组织描述")
	private String orgDesc;
	/**
	 * 是否停用（值：0 启用 、值：1 停用）
	 */
	@ApiModelProperty(value = "是否停用")
	private Boolean isStop;
	/**
	 * 启用日期
	 */
	@ApiModelProperty(value = "启用日期")
	private Date startDate;
	/**
	 * 停用日期
	 */
	@ApiModelProperty(value = "停用日期")
	private Date stopDate;
}