package net.herdao.hdp.manpower.mpclient.dto.staffChanges;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 8:46 上午
 */

@Data
@ApiModel(value = "保存调入调出执行")
public class SaveStaffCallInAndCallOutExecuteDTO {
    private Long id;

    @ApiModelProperty(value = "执行类型", name = "executeType")
    private String executeType;

    @ApiModelProperty(value = "是否发送通知", name = "releaseNote")
    private Boolean releaseNote;
}
