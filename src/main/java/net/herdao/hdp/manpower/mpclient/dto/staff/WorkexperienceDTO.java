package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelIgnore
    @ApiModelProperty(value="ID")
    private Long id;


    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名")
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    /**
     * 员工工号
     */
    @ExcelProperty(value = "员工工号")
    @ApiModelProperty(value="员工工号")
    private String staffCode;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value="开始时间")
    private String beginDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty(value="结束时间")
    private String endDate;

    /**
     * 单位名称
     */
    @ExcelProperty(value = "单位名称")
    @ApiModelProperty(value="单位名称")
    private String companyName;

    /**
     * 中心/项目
     */
    @ExcelProperty(value = "中心/项目")
    @ApiModelProperty(value="中心/项目")
    private String orgName;

    /**
     * 岗位
     */
    @ExcelProperty(value = "岗位")
    @ApiModelProperty(value="岗位")
    private String post;

    /**
     * 主要业绩
     */
    @ExcelProperty(value = "主要业绩")
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;

    /**
     * 承担角色
     */
    @ExcelProperty(value = "承担角色")
    @ApiModelProperty(value="承担角色")
    private String role;

    /**
     * 下属人数
     */
    @ExcelProperty(value = "下属人数")
    @ApiModelProperty(value="下属人数")
    private Integer subordinates;

    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人")
    @ApiModelProperty(value="操作人")
    private String modifierName;

    /**
     * 操作时间
     */
    @ExcelProperty(value = "操作时间")
    @ApiModelProperty(value="操作时间")
    private String modifiedTime;


    /**
     * 员工ID
     */
    @ExcelProperty(value = "员工ID")
    @ApiModelProperty(value="员工ID")
    private String staffId;
}
