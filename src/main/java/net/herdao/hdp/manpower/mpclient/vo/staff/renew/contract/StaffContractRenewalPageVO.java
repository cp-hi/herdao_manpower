package net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Liu Chang
 * @Date 2020/12/5 6:22 下午
 */
@Data
@ApiModel(value = "续签合同列表")
public class StaffContractRenewalPageVO {
    @ApiModelProperty(value = "续签合同 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "续签主体单位id")
    private String companyName;

    @ApiModelProperty(value = "合同类型")
    private String renewalType;

    @ApiModelProperty(value = "续签合同生效日期")
    private Long renewalStartTime;

    @ApiModelProperty(value = "续签合同结束日期")
    private Long renewalEndTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "更新信息", name = "updateInfo", example = "由李四于2020-01-02更新")
    private String updateInfo;
}
