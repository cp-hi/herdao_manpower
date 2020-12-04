
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 人才简历-录用情况-DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-录用情况-DTO")
public class RecruitmentJobDTO extends BaseEntity<RecruitmentJobDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 工作年限
     */
    @ApiModelProperty(value="工作年限")
    private String workAge;



}
