

package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 组织OID
     */
    @ApiModelProperty(value="组织OID")
    private String oid;

    /**
     * 组织名称
     */
    @ExcelProperty(value = "组织名称", index = 0)
    @ApiModelProperty(value="组织名称")
    private String orgName;

    /**
     * 组织全名
     */
    @ApiModelProperty(value="组织全名")
    private String orgFullname;
    /**
     * 组织编码
     */
    @ApiModelProperty(value="组织编码")
    private String orgCode;
    /**
     * 上级OID
     */
    @ApiModelProperty(value="上级OID")
    private String parentOid;

    /**
     * 父ID
     */
    @ApiModelProperty(value="父ID")
    private Long parentId;

    /**
     * 父ID
     */
    @ExcelProperty(value = "上级组织编字符串传参", index = 1)
    @ApiModelProperty(value="父ID")
    @TableField(exist = false)
    private String parentIdStr;

    /**
     * 组织级别
     */
    @ApiModelProperty(value="组织级别")
    private String orgLevel;
    /**
     * 是否组织 是否项目/中心及以上
     */
    @ApiModelProperty(value="是否组织 是否项目/中心及以上")
    private Boolean organizational;
    /**
     * 管线编码
     */
    @ApiModelProperty(value="管线编码")
    private String pipelineCode;
    /**
     * 福利类型
     */
    @ApiModelProperty(value="福利类型")
    private String welfareType;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private String sortNo;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Integer isStop;

    /**
     * 是否停用 页面展示
     */
    @ApiModelProperty(value="是否停用 页面展示")
    @TableField(exist = false)
    private String enableStatus;

    /**
     * 是否虚拟组织
     */
    @ApiModelProperty(value="是否虚拟组织")
    private Boolean isVirtual;
    /**
     * 组织类型
     */
    @ApiModelProperty(value="组织类型")
    private String orgType;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 组织描述
     */
    @ApiModelProperty(value="组织描述")
    private String orgDesc;
    /**
     * 组织负责人姓名
     */
    @ApiModelProperty(value="组织负责人姓名")
    private String orgChargeName;

    /**
     * 组织负责人工号
     */
    @ExcelProperty(value = "组织负责人", index = 3)
    @ApiModelProperty(value="组织负责人工号")
    private String orgChargeWorkNo;
    /**
     * 组织简称
     */
    @ApiModelProperty(value="组织简称")
    private String orgSimpleDesc;

    /**
     * 部门负责人岗位（负责岗位）
     */
    @ExcelProperty(value = "负责岗位", index = 4)
    @ApiModelProperty(value="部门负责人岗位（负责岗位）")
    private String chargeOrg;

    /**
     * 人员编制 组织编制
     */
    @ApiModelProperty(value="人员编制 组织编制")
    private String staff;
    /**
     * 在职员工数
     */
    @ApiModelProperty(value="在职员工数")
    private String empInService;

    /**
     * 启用日期
     */
    @ApiModelProperty(value="启用日期")
    private String startDate;

    /**
     * 停用日期
     */
    @ApiModelProperty(value="停用日期")
    private String stopDate;

    /**
     * 组织树层级
     */
    @ApiModelProperty(value="组织树层级")
    private Long orgTreeLevel;

    /**
     * 启用日期操作时间
     */
    @ApiModelProperty(value="启用日期操作时间")
    private Date startOrgOperateDate;


    /**
     * 停用日期操作时间
     */
    @ApiModelProperty(value="停用日期操作时间")
    private Date endOrgOperateDate;

    /**
     * 父组织架构集合
     */
    @ApiModelProperty(value="父组织架构集合")
    @TableField(exist = false)
    private List<Organization> parent;

    /**
     * 子组织架构集合
     */
    @ApiModelProperty(value="子组织架构集合")
    @TableField(exist = false)
    private List<Organization> children;

    @ApiModelProperty(value="判断是否加载根组织架构 传参")
    @TableField(exist = false)
    private Boolean isRoot;

    @ApiModelProperty(value="子组织架构集合 点击触发")
    @TableField(exist = false)
    private List<Organization> childrenClick;

    @ApiModelProperty(value="架构id集合 传参")
    @TableField(exist = false)
    private List<Long> ids;

    @ApiModelProperty(value="起始页")
    @TableField(exist = false)
    private List<Integer> pageStart;

    @ApiModelProperty(value="所属组织架构外键集合list")
    @TableField(exist = false)
    private List<Integer> orgDeptIds;

    @ApiModelProperty(value="部门负责人姓名")
    @TableField(exist = false)
    private String postName;

    @ApiModelProperty(value="组织负责人姓名 传参")
    @TableField(exist = false)
    private String userName;

    /**
     * 父组织name
     */
    @ApiModelProperty(value="父组织name")
    @TableField(exist = false)
    private String parentName;


    /**
     * 创建情况
     */
    @ApiModelProperty(value="创建情况")
    @TableField(exist = false)
    private String createDesc;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @TableField(exist = false)
    private String updateDesc;

    /**
     * 是否虚拟组织 页面展示
     */
    @ApiModelProperty(value="是否虚拟组织 页面展示")
    @TableField(exist = false)
    private String virtual;

}



