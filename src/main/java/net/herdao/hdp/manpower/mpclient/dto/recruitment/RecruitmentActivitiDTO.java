

package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才活动表DTO
 *
 * @author Andy
 * @date 2020-12-02 20:12:40
 */
@Data
@ApiModel(value = "人才活动表DTO")
public class RecruitmentActivitiDTO{

    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 活动开始时间-LocalDateTime
     */
    @ApiModelProperty(value="活动开始时间-LocalDateTime",hidden = true)
    private LocalDateTime beginDateLocal;


    /**
     * 活动开始时间
     */
    @ApiModelProperty(value="活动开始时间")
    private Long beginDate;

    /**
     * 活动结束时间-LocalDateTime
     */
    @ApiModelProperty(value="活动结束时间-LocalDateTime",hidden = true)
    private LocalDateTime endDateLocal;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value="活动结束时间")
    private Long endDate;

    /**
     * 活动内容
     */
    @ApiModelProperty(value="活动内容")
    private String content;

    /**
     * 成绩/成果
     */
    @ApiModelProperty(value="成绩/成果")
    private String result;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;

 }
