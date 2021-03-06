
package net.herdao.hdp.manpower.mpclient.vo.organization;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String parentName;
	/**
	 * 上级组织
	 */
	@ApiModelProperty(value = "上级组织编码")
	private String parentCode;
	/**
	 * 组织层（ 参考字典：“ZZCJ”）
	 */
	@ApiModelProperty(value = "组织层级")
	private String orgLevel;
	
	@ApiModelProperty(value = "组织层级名称")
	private String orgLevelName;
	
	/**
	 * 组织类型（参考字典：“ZZLX”）
	 */
	@ApiModelProperty(value = "组织类型")
	private String orgType;
	
	@ApiModelProperty(value = "组织类型名称")
	private String orgTypeName;
	/**
	 * 组织负责人ID
	 */
	@ApiModelProperty(value = "组织负责人ID")
	private String orgChargeWorkId;
	/**
	 * 组织负责人姓名
	 */
	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeWorkName;

	/**
	 * 组织负责人工号
	 */
	@ApiModelProperty(value = "组织负责人工号")
	private String orgChargeWorkNo;
	/**
	 * 负责岗位id
	 */
	@ApiModelProperty(value = "负责岗位id")
	private Long postId;

	@ApiModelProperty(value = "岗位组织关系id")
	private Long postOrgId;

	@ApiModelProperty(value = "岗位组织关系名称")
	private String postOrgName;
	
	/**
	 * 负责岗位
	 */
	@ApiModelProperty(value = "负责岗位名称")
	private String postName;
	/**
	 * 负责岗位
	 */
	@ApiModelProperty(value = "负责岗位编码")
	private String postCode;
	/**
	 * 是否虚拟组织（取值待确认）
	 */
	@ApiModelProperty(value = "是否虚拟组织(值：true 是 、值： false 否)")
	private Boolean isVirtual;
	/**
	 * 人员编制（编制人员数）
	 */
	@ApiModelProperty(value = "人员编制")
	private Integer staffTotal;

	/**
	 * 是否项目/中心及以上
	 */
	@ApiModelProperty(value = "是否项目/中心及以上（值：true 是， 值 false 否）")
	private Boolean organizational;
	/**
	 * 福利类型（取福利标准主键）
	 */
	@ApiModelProperty(value = "福利类型")
	private String welfareType;
	/**
	 * 福利类型名称
	 */
	@ApiModelProperty(value = "福利类型名称")
	private String welfareTypeName;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sortNo;
	
	/**
	 * 组织描述
	 */
	@ApiModelProperty(value = "组织描述")
	private String orgDesc;
	/**
	 * 是否停用（值：0 启用 、值：1 停用）
	 */
	@ApiModelProperty(value = "是否停用(值：false 启用 、值：true 停用)")
	private Boolean isStop;
	/**
	 * 启用日期
	 */
	@ApiModelProperty(value = "启用日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;
	/**
	 * 停用日期
	 */
	@ApiModelProperty(value = "停用日期")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date stopDate;
	
	@ApiModelProperty(value = "是否可以删除 （值：true 是， 值 false 否）")
	private Boolean canDelete;
}
