package net.herdao.hdp.manpower.mpclient.dto.staffTransfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 4:10 下午
 */

@Data
@ApiModel(value = "人事调动执行")
public class SaveStaffTransferProveDTO {
    private Long id;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String executeType;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String releaseNote;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    /**
     * TODO:: 字段类型和字段名称待确认
     */
    private String securityType;

    @ApiModelProperty(value = "工资发放单位 id", name = "paidUnitsId")
    private String paidUnitsId;

    @ApiModelProperty(value = "公积金购买单位 id", name = "fundUnitsId")
    private Long fundUnitsId;

    @ApiModelProperty(value = "社保购买单位 id", name = "securityUnitId")
    private Long securityUnitId;
}
