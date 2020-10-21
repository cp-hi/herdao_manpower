

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-17 11:03:56
 */
@Data
@TableName("mp_org_modify_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "")
public class OrgModifyRecord extends Model<OrgModifyRecord> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;


    /**
     * 现组织ID
     */
    @ApiModelProperty(value="现组织ID")
    private Long curOrgId;

    /**
     * 现组织名称
     */
    @ApiModelProperty(value="现组织名称")
    private String curOrgName;
    /**
     * 现组织编码
     */
    @ApiModelProperty(value="现组织编码")
    private String curOrgCode;
    /**
     * 现上级组织id
     */
    @ApiModelProperty(value="现上级组织id")
    private Long curOrgParentId;
    /**
     * 现上级组织名称
     */
    @ApiModelProperty(value="现上级组织名称")
    private String curOrgParentName;
    /**
     * 现组织层级(树形层级）
     */
    @ApiModelProperty(value="现组织层级(树形层级）")
    private Long curOrgTreeLevel;
    /**
     * 原组织名称
     */
    @ApiModelProperty(value="原组织名称")
    private String oldOrgName;
    /**
     * 原上级组织ID
     */
    @ApiModelProperty(value="原上级组织ID")
    private Long oldOrgParentId;
    /**
     * 原上级组织名称
     */
    @ApiModelProperty(value="原上级组织名称")
    private String oldOrgParentName;
    /**
     * 原组织层级(树形层级）
     */
    @ApiModelProperty(value="原组织层级")
    private Long oldOrgTreeLevel;
    /**
     * 生效日期
     */
    @ApiModelProperty(value="生效日期")
    private Date effectTime;
    /**
     * 操作人id
     */
    @ApiModelProperty(value="操作人id")
    private String operatorId;
    /**
     * 操作人名称
     */
    @ApiModelProperty(value="操作人名称")
    private String operatorName;
    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private Date operatorTime;

    /**
     * 原组织编码
     */
    @ApiModelProperty(value="原组织编码")
    private String oldOrgCode;

    /**
     * 操作简述
     */
    @ApiModelProperty(value="操作简述")
    private String operateDesc;

 }

