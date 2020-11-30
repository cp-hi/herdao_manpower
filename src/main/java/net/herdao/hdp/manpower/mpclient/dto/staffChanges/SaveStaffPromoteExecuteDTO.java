package net.herdao.hdp.manpower.mpclient.dto.staffChanges;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 8:49 上午
 */
@Data
@ApiModel(value = "保存晋升/降级执行")
public class SaveStaffPromoteExecuteDTO {
    private Long id;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String executeType;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String releaseNote;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private Long transStartDate;

    @ApiModelProperty(value = "考察期满日", name = "expireDate", example = "20200901")
    private Long expireDate;
}
