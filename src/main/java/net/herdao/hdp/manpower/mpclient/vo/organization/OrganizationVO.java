
package net.herdao.hdp.manpower.mpclient.vo.organization;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * 组织VO
 *
 * @author shuling
 * @date 2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织VO")
public class OrganizationVO extends BaseEntity<OrganizationVO> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 组织名称
	 */
	@Valid
	@NotBlank(message = "组织名称不能为空")
	@ApiModelProperty(value = "组织名称")
	private String orgName;

	/**
	 * 组织全名
	 */
	@ApiModelProperty(value = "组织全名")
	private String orgFullname;

	/**
	 * 组织编码
	 */
	@ApiModelProperty(value = "组织编码")
	private String orgCode;

	/**
	 * 父组织id
	 */
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	/**
	 * 父组织名称
	 */
	@ApiModelProperty(value = "父组织名称")
	private String parentOrgName;
	
	/**
	 * 父组织编码
	 */
	@ApiModelProperty(value = "父组织编码")
	private String parentOrgCode;
	

	/**
	 * 是否项目/中心及以上
	 */
	@ApiModelProperty(value = "是否项目/中心及以上（值：true 是， 值 false 否）")
	private Boolean organizational;

	/**
	 * 管线编码（取管线表主键）
	 */
	@ApiModelProperty(value = "管线编码")
	private String pipelineCode;

	/**
	 * 福利类型（取福利标准主键）
	 */
	@ApiModelProperty(value = "福利类型")
	private String welfareType;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	@Valid
	@Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = "排序" + ExcelPatternMsgContants.NUMBER_MSG)
	private String sortNo;

	/**
	 * 是否停用（值：0 启用 、值：1 停用 、值：3 或者 NULL 查询全部）
	 */
	@ApiModelProperty(value = "是否停用(值：false 启用 、值：true 停用)")
	private Boolean isStop;

	/**
	 * 是否虚拟组织
	 */
	@ApiModelProperty(value = "是否虚拟组织(值：true 是 、值： false 否)")
	private Boolean isVirtual;

	/**
	 * 组织类型（参考字典：“ZZLX”）
	 */
	@ApiModelProperty(value = "组织类型")
	private String orgType;
	
	@ApiModelProperty(value = "组织类型名称")
	private String orgTypeName;

	/**
	 * 组织层（ 参考字典：“ZZCJ”）
	 */
	@ApiModelProperty(value = "组织层级")
	private String orgLevel;
	
	@ApiModelProperty(value = "组织层级名称")
	private String orgLevelName;

	/**
	 * 组织树层级
	 */
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;

	/**
	 * 组织简称
	 */
	@ApiModelProperty(value = "组织简称")
	private String orgSimpleDesc;

	/**
	 * 组织负责人id
	 */
	@ApiModelProperty(value = "组织负责人id")
	private String orgChargeId;

	/**
	 * 组织负责人姓名
	 */
	@ApiModelProperty(value = "组织负责人姓名")
	private String orgChargeName;

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
	
	/**
	 * 负责岗位
	 */
	@ApiModelProperty(value = "负责岗位")
	private Long postName; 

	/**
	 * 组织描述
	 */
	@ApiModelProperty(value = "组织描述")
	private String orgDesc;

	/**
	 * 人员编制（编制人员数）
	 */
	@ApiModelProperty(value = "人员编制")
	private Integer staffTotal;
	/**
	 * 在职员工数
	 */
	@ApiModelProperty(value = "在职员工数")
	private Integer workforceTotal;

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
	
	/**
	 * 创建情况
	 */
	@ApiModelProperty(value = "创建情况")
	private String createDesc;

	/**
	 * 最近更新情况
	 */
	@ApiModelProperty(value = "最近更新情况")
	private String updateDesc;
}
