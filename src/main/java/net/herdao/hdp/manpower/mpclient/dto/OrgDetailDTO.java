package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织详情
 */
@Data
@ApiModel(value = "组织基础信息")
public class OrgDetailDTO {
    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 组织名称
     */
    @ApiModelProperty( "组织名称")
    private String orgName;

    /**
     * 组织编码
     */
    @ApiModelProperty(value="组织编码")
    private String orgCode;

    /**
     * 组织层级
     */
    @ApiModelProperty(value="组织层级")
    private String orgLevel;

    /**
     * 组织类型
     */
    @ApiModelProperty(value="组织类型")
    private String orgType;

    /**
     * 是否虚拟组织
     */
    @ApiModelProperty(value="是否虚拟组织")
    private Boolean isVirtual;

    /**
     * 是否组织 是否项目/中心及以上
     */
    @ApiModelProperty(value="是否组织 是否项目/中心及以上")
    private Boolean organizational;

    /**
     * 组织描述
     */
    @ApiModelProperty(value="组织描述")
    private String orgDesc;

    /**
     * 组织简称
     */
    @ApiModelProperty(value="组织简称")
    private String orgSimpleDesc;

    /**
     * 父组织name 上级组织
     */
    @ApiModelProperty(value="父组织name 上级组织")
     private String parentName;

    /**
     * 部门负责人
     */
    @ApiModelProperty(value="部门负责人姓名 部门负责人")
    private String postName;

    /**
     * 部门负责人岗位（负责岗位）
     */
     @ApiModelProperty(value="部门负责人岗位（负责岗位）")
     private String chargeOrg;

    /**
     * 人员编制 组织编制
     */
    @ApiModelProperty(value="人员编制 组织编制")
    private String staff;

    /**
     * 福利类型
     */
    @ApiModelProperty(value="福利类型")
    private String welfareType;

    /**
     * 是否停用 页面展示
     */
    @ApiModelProperty(value="是否停用 页面展示")
    @TableField(exist = false)
    private String enableStatus;

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
     * 父ID 新增或修改时使用
     */
    @ApiModelProperty(value="父ID 新增或修改时使用")
    private Long parentId;

    /**
     * 组织负责人工号
     */
    @ApiModelProperty(value="组织负责人工号 新增或修改时使用")
    private String orgChargeWorkNo;
}
