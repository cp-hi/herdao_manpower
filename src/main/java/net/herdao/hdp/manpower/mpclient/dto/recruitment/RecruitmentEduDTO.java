
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
 * 人才教育情况DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才教育情况")
public class RecruitmentEduDTO   {

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 入学日期
     */
    @ApiModelProperty(value="入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date period;

    /**
     * 毕业日期
     */
    @ApiModelProperty(value="毕业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date todate;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String schoolName;

    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;

    /**
     * 学位
     */
    @ApiModelProperty(value="学位")
    private String degree;

    /**
     * 学制
     */
    @ApiModelProperty(value="学制")
    private String schoolSystem;

    /**
     * 学历
     */
    @ApiModelProperty(value="学历")
    private String educationQua;

    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    private String updateDesc;

    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;


}
