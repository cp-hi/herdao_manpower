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
    private String modifierName;
    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private LocalDateTime modifiedTime;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="承担角色")
    private String staffName;
    
}
