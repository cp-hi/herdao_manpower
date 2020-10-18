package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@ApiModel(value = "工作经历-lift")
public class WorkexperienceDTO {
	/**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    private LocalDateTime beginDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    private LocalDateTime endDate;
    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称")
    private String companyName;
    /**
     * 中心/项目
     */
    @ApiModelProperty(value="中心/项目")
    private String orgName;
    /**
     * 岗位
     */
    @ApiModelProperty(value="岗位")
    private String post;
    /**
     * 主要业绩
     */
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;
    /**
     * 承担角色
     */
    @ApiModelProperty(value="承担角色")
    private String role;
    /**
     * 下属人数
     */
    @ApiModelProperty(value="下属人数")
    private Integer subordinates;

    /**
     * 操作人
     */
    @ApiModelProperty(value="操作人")
    @TableField(exist = false)
    private String modifierName;
    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private LocalDateTime modifiedTime;
    
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;

    /**
     * 员工信息id
     */
    @ApiModelProperty(value="员工信息id")
    private BigDecimal staffid;
}
