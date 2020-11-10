package net.herdao.hdp.manpower.mpclient.dto.staffWork;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 工作经历list DTO
 */
@Data
@ApiModel(value = "工作经历list DTO")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value="开始时间")
    private String beginDate;

    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @ExcelProperty(value = "专业部门（中心/项目）")
    @ApiModelProperty(value="专业部门（中心/项目）")
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
    private String subordinates;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    @ExcelIgnore
    private String updateDesc;
    /**
     * 集团id
     */
    @ApiModelProperty(value="集团id")
    @ExcelProperty(value = "集团id")
    private String groupId;
    /**
     * 集团名
     */
    @ApiModelProperty(value="集团名")
    @ExcelProperty(value = "集团名")
    private String groupName;
    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelIgnore
    private String staffId;
}
