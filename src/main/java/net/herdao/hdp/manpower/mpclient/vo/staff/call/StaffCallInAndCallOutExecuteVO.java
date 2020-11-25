package net.herdao.hdp.manpower.mpclient.vo.staff.call;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 8:43 上午
 */
@Data
@ApiModel(value = "执行调入调出")
public class StaffCallInAndCallOutExecuteVO {
    private Long id;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String executeType;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String releaseNote;
}
