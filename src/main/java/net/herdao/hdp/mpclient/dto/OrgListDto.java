package net.herdao.hdp.mpclient.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

public class OrgListDto {
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
     * 组织类型
     */
    @ApiModelProperty(value="组织类型")
    private String orgType;

    /**
     * 部门负责人岗位（负责岗位）
     */
    @ApiModelProperty(value="部门负责人岗位（负责岗位）")
    private String chargeOrg;

    /**
     * 组织负责人姓名
     */
    @ApiModelProperty(value="组织负责人姓名")
    private String orgChargeName;

    /**
     * 人员编制
     */
    @ApiModelProperty(value="人员编制")
    private String staff;

    /**
     * 在职员工数
     */
    @ApiModelProperty(value="在职员工数")
    private String empInService;

    /**
     * 创建情况
     */
    @ApiModelProperty(value="创建情况")
    private String createDesc;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @TableField(exist = false)
    private String updateDesc;
}
