package net.herdao.hdp.manpower.mpclient.dto.staffTransfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 4:10 下午
 */

@Data
@ApiModel(value = "保存人事调动执行")
public class SaveStaffTransferProveDTO {
    private Long id;

    @ApiModelProperty(value = "执行类型", name = "executeType")
    private String executeType;

    @ApiModelProperty(value = "是否发送通知", name = "releaseNote")
    private Boolean releaseNote;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    @ApiModelProperty(value = "社保类型", name = "securityType", example = "20201003")
    private String securityType;

    @ApiModelProperty(value = "工资发放单位 id", name = "paidUnitsId")
    private String paidUnitsId;

    @ApiModelProperty(value = "公积金购买单位 id", name = "fundUnitsId")
    private Long fundUnitsId;

    @ApiModelProperty(value = "社保购买单位 id", name = "securityUnitId")
    private Long securityUnitId;
}
