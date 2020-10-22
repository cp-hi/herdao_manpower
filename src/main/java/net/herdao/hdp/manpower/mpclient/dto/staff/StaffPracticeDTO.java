package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@ApiModel(value = "实习记录")
public class StaffPracticeDTO {
	/**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 开始日期 yyyy-MM-dd
     */
    @ApiModelProperty(value="开始日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beginDate;
    /**
     * 结束日期 yyyy-MM-dd
     */
    @ApiModelProperty(value="结束日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date endDate;
    /**
     * 实习期 以月为单位，开始日期和结束日期之差，取整
     */
    @ApiModelProperty(value="实习期") 
    private String period;
    /**
     * 集团
     */
    @ApiModelProperty(value="集团")
    private String groupName;
    /**
     * 公司
     */
    @ApiModelProperty(value="公司")
    private String corporationName;
    /**
     * 部门
     */
    @ApiModelProperty(value="部门名称")
    private String departName;
    /**
     * 部门ID
     */
    @ApiModelProperty(value="部门")
    private Long departId;
    /**
     * 板块ID
     */
    @ApiModelProperty(value="板块")
    private Long sectionId;
    /**
     * 板块名称
     */
    @ApiModelProperty(value="板块名称")
    private String sectionName;
    /**
     * 管线ID
     */
    @ApiModelProperty(value="管线")
    private Long pipelineId;
    /**
     * 管线名称
     */
    @ApiModelProperty(value="管线名称")
    private String pipelineName;
    /**
     * 岗位ID
     */
    @ApiModelProperty(value="岗位")
    private Long postId;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String postName;
    /**
     * 职级
     */
    @ApiModelProperty(value="职级")
    private String jobLevelId;
    /**
     * 职级名称
     */
    @ApiModelProperty(value="职级名称")
    private String jobLevelName;
    /**
     * 实习成绩
     */
    @ApiModelProperty(value="实习成绩")
    private Double score;
    /**
     * 评价
    */
    @ApiModelProperty(value="评价")
    private String evaluate;
    /**
     * 评价人ID
    */
    @ApiModelProperty(value="评价人")
    private String evaluateId;
    /**
     * 评价人姓名
    */
    @ApiModelProperty(value="评价人姓名")
    private String evaluateName;
    /**
     * 评价时间
    */
    @ApiModelProperty(value="评价时间")
    private LocalDateTime evaluateTime;
    /**
     * 人员外键
    */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
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
}
