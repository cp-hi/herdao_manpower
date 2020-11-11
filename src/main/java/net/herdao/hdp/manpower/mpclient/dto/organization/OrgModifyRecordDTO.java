

package net.herdao.hdp.manpower.mpclient.dto.organization;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @description:    组织变更记录（需求待细化TODO）
* @author:         shuling
* @date            2020-11-11 12:16:37
* @version:        1.0
*/
@Data
@ApiModel(value = "组织变更记录")
public class OrgModifyRecordDTO {
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
     * 现上级组织名称
     */
    @ApiModelProperty(value="现上级组织名称")
    private String curOrgParentName;
    /**
     * 现组织层级
     */
    @ApiModelProperty(value = "现组织层级")
	private String curOrgLevel;
    /**
     * 原组织名称
     */
    @ApiModelProperty(value="原组织名称")
    private String oldOrgName;
    /**
     * 原上级组织名称
     */
    @ApiModelProperty(value="原上级组织名称")
    private String oldOrgParentName;
    /**
     * 原组织层级
     */
    @ApiModelProperty(value = "原组织层级")
	private String oldOrgLevel;
    /**
     * 生效日期
     */
    @ApiModelProperty(value="生效日期")
    private Date effectTime;

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
     * 操作简述
     */
    @ApiModelProperty(value="操作简述")
    private String operateDesc;

 }

