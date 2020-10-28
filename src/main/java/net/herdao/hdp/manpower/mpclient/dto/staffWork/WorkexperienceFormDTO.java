package net.herdao.hdp.manpower.mpclient.dto.staffWork;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 工作经历 表单DTO
 */
@Data
@ApiModel(value = "工作经历 表单DTO")
public class WorkexperienceFormDTO {
	/**
     * ID
     */
    @TableId
    @ExcelIgnore
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 员工ID
     */
    @ExcelProperty(value = "员工ID")
    @ApiModelProperty(value="员工ID")
    private Long staffId;



    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value="开始时间")
    private LocalDate beginDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty(value="结束时间")
    private LocalDate endDate;

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
    private Integer subordinates;
}
