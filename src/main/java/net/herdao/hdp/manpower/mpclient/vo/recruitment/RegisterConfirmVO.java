package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 入职登记确认VO
 * @author Andy
 */
@Data
@ApiModel(value = "入职登记确认VO")
public class RegisterConfirmVO {

    /**
     * 人才表主键id
     */
    @ApiModelProperty(value="人才表主键id")
    private String id;

}
