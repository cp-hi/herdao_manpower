
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才获奖情况DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才获奖情况DTO")
public class RecruitmentAwardsDTO {

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;


    /**
     * 获奖时间
     */
    @ApiModelProperty(value="获奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date awardsTime;

    /**
     * 获奖内容
     */
    @ApiModelProperty(value="获奖内容")
    private String awardsContent;

    /**
     * 颁发单位
     */
    @ApiModelProperty(value="颁发单位")
    private String issuedUnits;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    private String updateDesc;
}
