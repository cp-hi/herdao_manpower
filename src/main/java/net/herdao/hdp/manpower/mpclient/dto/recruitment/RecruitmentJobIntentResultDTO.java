
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *  简历详情-求职意向-结果返回-DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "编辑人才简历-求职意向-结果返回-DTO")
public class RecruitmentJobIntentResultDTO {

    /**
     * 简历详情-求职意向
     */
    @ApiModelProperty(value="简历详情-求职意向")
    private RecruitmentJobIntentDTO recruitmentJobIntentDTO;


}
