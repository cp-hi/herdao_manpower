

package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才培训经历DTO
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
@Data
@ApiModel(value = "人才培训经历DTO")
public class RecruitmentTrainDTO {
    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 开始培训时间-LocalDateTime
     */
    @ApiModelProperty(value="开始培训时间-LocalDateTime",hidden = true)
    private LocalDateTime beginDateLocal;

    /**
     * 开始培训时间
     */
    @ApiModelProperty(value="开始培训时间")
    private Long beginDate;

    /**
     * 结束培训时间-LocalDateTime
     */
    @ApiModelProperty(value="结束培训时间-LocalDateTime",hidden = true)
    private LocalDateTime endDateLocal;

    /**
     * 结束培训时间
     */
    @ApiModelProperty(value="结束培训时间")
    private Long endDate;

    /**
     * 培训内容
     */
    @ApiModelProperty(value="培训内容")
    private String content;
    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    private String certificateName;
    /**
     * 证书编号
     */
    @ApiModelProperty(value="证书编号")
    private String certificateNo;

    /**
     * 组织者
     */
    @ApiModelProperty(value="组织者")
    private String organizer;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;

}
