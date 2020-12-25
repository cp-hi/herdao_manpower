
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才简历-最高教育经历DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-最高教育经历DTO")
public class RecruitmentTopEduDTO {

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    private String highestEducation;

    /**
     * 最高学位
     */
    @ApiModelProperty(value="最高学位")
    private String educationDegree;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String graduated;

    /**
     * 入学日期-LocalDateTime
     */
    @ApiModelProperty(value="入学日期-LocalDateTime",hidden = true)
    private LocalDateTime beginDateLocal;

    /**
     * 入学日期
     */
    @ApiModelProperty(value="入学日期")
    private Long beginDate;

    /**
     * 毕业日期-LocalDateTime
     */
    @ApiModelProperty(value="毕业日期-LocalDateTime")
    private LocalDateTime endDateLocal;

    /**
     * 毕业日期
     */
    @ApiModelProperty(value="毕业日期")
    private Long endDate;

    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;

    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;

}
