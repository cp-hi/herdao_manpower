package net.herdao.hdp.manpower.mpclient.vo.staff.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 4:01 下午
 */
@Data
@ApiModel(value = "人事调动-调动执行")
public class StaffTransferApproveVO {

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

    @ApiModelProperty(value = "工资发放单位名称", name = "paidUnitsName")
    private String paidUnitsName;

    @ApiModelProperty(value = "公积金购买单位 id", name = "fundUnitsId")
    private Long fundUnitsId;

    @ApiModelProperty(value = "公积金购买单位名称", name = "fundUnitsName")
    private String fundUnitsName;

    @ApiModelProperty(value = "社保购买单位 id", name = "securityUnitId")
    private Long securityUnitId;

    @ApiModelProperty(value = "社保购买单位名称", name = "securityUnitName")
    private Long securityUnitName;

}
