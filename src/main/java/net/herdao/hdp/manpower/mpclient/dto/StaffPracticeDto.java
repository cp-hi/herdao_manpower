package net.herdao.hdp.manpower.mpclient.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "员工实习记录")
public class StaffPracticeDto {
    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 开始日期 yyyy/mm/dd
     */
    @ApiModelProperty(value="开始日期 yyyy/mm/dd ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beginDate;
    /**
     * 结束日期 yyyy/mm/dd
     */
    @ApiModelProperty(value="结束日期 yyyy/mm/dd ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date endDate;
    /**
     * 实习期 以月为单位，开始日期和结束日期之差，取整
     */
    @ApiModelProperty(value="实习期 以月为单位，开始日期和结束日期之差，取整")
    private String period;
    /**
     * 集团
     */
    @ApiModelProperty(value="集团")
    private String groupName;
    /**
     * 集团ID
     */
    @ApiModelProperty(value="集团ID")
    private Long groupId;
    /**
     * 公司
     */
    @ApiModelProperty(value="公司")
    private String corporationName;
    /**
     * 公司ID
     */
    @ApiModelProperty(value="公司ID")
    private Long corporationId;
    /**
     * 部门
     */
    @ApiModelProperty(value="部门")
    private String departName;
    /**
     * 部门ID
     */
    @ApiModelProperty(value="部门ID")
    private Long departId;
    /**
     * 板块ID
     */
    @ApiModelProperty(value="板块ID")
    private Long plateId;
    /**
     * 管线ID
     */
    @ApiModelProperty(value="管线ID")
    private Long pipeId;
    /**
     * 岗位ID
     */
    @ApiModelProperty(value="岗位ID")
    private Long postId;
    /**
     * 职级
     */
    @ApiModelProperty(value="职级")
    private String jobLevel;
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
    @ApiModelProperty(value="评价人ID")
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
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorId;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierId;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private String staffId;
     
    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

    /**
     * 板块名称
     */
    @ApiModelProperty(value="板块名称")
    private String plateName;

    /**
     * 管线名称
     */
    @ApiModelProperty(value="管线名称")
    private String pipeName;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String postName;

    /**
     * 职级名称
     */
    @ApiModelProperty(value="职级名称")
    private String jobLevelName;


}
