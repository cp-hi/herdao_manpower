
package net.herdao.hdp.manpower.mpclient.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * 
 *
 * @author andy
 * @date 2020-09-09 15:31:20
 */
@Data
@TableName("mp_organization")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织")
public class Organization extends BaseEntity<Organization> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 组织OID
	 */
	@ApiModelProperty(value = "组织OID")
	private String oid;

	/**
	 * 组织名称
	 */
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
	 * 上级OID
	 */
	@ApiModelProperty(value = "上级OID")
	private String parentOid;

	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID")
	private Long parentId;

	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID")
	@TableField(exist = false)
	private String parentIdStr;

	/**
	 * 是否组织 是否项目/中心及以上
	 */
	@ApiModelProperty(value = "是否组织 是否项目/中心及以上")
	private Boolean organizational;
	/**
	 * 管线编码
	 */
	@ApiModelProperty(value = "管线编码")
	private String pipelineCode;
	/**
	 * 福利类型
	 */
	@ApiModelProperty(value = "福利类型")
	private String welfareType;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sortNo;
	/**
	 * 是否停用(0 停用 ，1启用（默认），不传或者传3 查询全部)
	 */
	@ApiModelProperty(value = "是否停用")
	private Boolean isStop;

	/**
	 * 是否停用 页面展示
	 */
	@ApiModelProperty(value = "是否停用 页面展示")
	@TableField(exist = false)
	private String enableStatus;

	/**
	 * 是否虚拟组织
	 */
	@ApiModelProperty(value = "是否虚拟组织")
	private Boolean isVirtual;
	/**
	 * 组织类型
	 */
	@ApiModelProperty(value = "组织类型")
	private String orgType;

	/**
	 * 组织层级 参考字典：“ZZCJ”
	 */
	@ApiModelProperty(value = "组织层级")
	private String orgLevel;

	/**
	 * 组织树层级
	 */
	@ApiModelProperty(value = "组织树层级")
	private Long orgTreeLevel;

	/**
	 * 组织描述
	 */
	@ApiModelProperty(value = "组织描述")
	private String orgDesc;

	/**
	 * 组织负责人id
	 */
	@ApiModelProperty(value = "组织负责人id")
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private Long orgChargeWorkId;

	/**
	 * 组织负责人姓名
	 */
	@ApiModelProperty(value = "组织负责人姓名")
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private String orgChargeWorkName;

	/**
	 * 组织负责人工号
	 */
	@ApiModelProperty(value = "组织负责人工号")
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private String orgChargeWorkNo;

	/**
	 * 组织简称
	 */
	@ApiModelProperty(value = "组织简称")
	private String orgSimpleDesc;

	/**
	 * 人员编制 组织编制
	 */
	@ApiModelProperty(value = "人员编制 组织编制")
	private String staff;
	/**
	 * 在职员工数
	 */
	@ApiModelProperty(value = "在职员工数")
	private String empInService;

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
	 * 父组织架构集合
	 */
	@ApiModelProperty(value = "父组织架构集合")
	@TableField(exist = false)
	private List<Organization> parent;

	/**
	 * 子组织架构集合
	 */
	@ApiModelProperty(value = "子组织架构集合")
	@TableField(exist = false)
	private List<Organization> children;

	@ApiModelProperty(value = "判断是否加载根组织架构 传参")
	@TableField(exist = false)
	private Boolean isRoot;

	@ApiModelProperty(value = "子组织架构集合 点击触发")
	@TableField(exist = false)
	private List<Organization> childrenClick;

	@ApiModelProperty(value = "架构id集合 传参")
	@TableField(exist = false)
	private List<Long> ids;

	@ApiModelProperty(value = "起始页")
	@TableField(exist = false)
	private List<Integer> pageStart;

	@ApiModelProperty(value = "所属组织架构外键集合list")
	@TableField(exist = false)
	private List<Integer> orgDeptIds;

	/**
	 * 负责岗位id
	 */
	@ApiModelProperty(value = "岗位id")
	private Long postId;

	@ApiModelProperty(value = "岗位组织关系id")
	private Long postOrgId;

	/**
	 * 负责岗位名称
	 */
	@ApiModelProperty(value = "岗位名称")
	@TableField(exist = false)
	private String postName;

	@ApiModelProperty(value = "组织负责人姓名 传参")
	@TableField(exist = false)
	private String userName;

	/**
	 * 父组织name
	 */
	@ApiModelProperty(value = "父组织name")
	@TableField(exist = false)
	private String parentName;

	/**
	 * 创建情况
	 */
	@ApiModelProperty(value = "创建情况")
	@TableField(exist = false)
	private String createDesc;

	/**
	 * 最近更新情况
	 */
	@ApiModelProperty(value = "最近更新情况")
	@TableField(exist = false)
	private String updateDesc;

	/**
	 * 是否虚拟组织 页面展示
	 */
	@ApiModelProperty(value = "是否虚拟组织 页面展示")
	@TableField(exist = false)
	private String virtual;

}
