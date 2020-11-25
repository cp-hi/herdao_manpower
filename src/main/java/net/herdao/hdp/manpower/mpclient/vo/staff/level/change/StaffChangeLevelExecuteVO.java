package net.herdao.hdp.manpower.mpclient.vo.staff.level.change;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 8:47 上午
 */
@Data
@ApiModel(value = "晋升/降级调动-调动执行")
public class StaffChangeLevelExecuteVO {
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
