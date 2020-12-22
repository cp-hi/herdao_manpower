package net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 该类接受从数据库中拿到的列表原始数据及数据类型完全一致
 *
 * 在 service 层做和 xxxPageVO 中某些字段的数据类型转换
 * e.g. LocalDateTime -> long
 *      updateName & updateTime ->  String： 由 xxx 更新于 2020-01-01
 *
 * @Author Liu Chang
 * @Date 2020/12/10 3:57 下午
 */

@Data
@ApiModel(value = "续签合同列表")
public class StaffContractRenewalPage {
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
    private LocalDateTime renewalStartTime;

    @ApiModelProperty(value = "续签合同结束日期")
    private LocalDateTime renewalEndTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "更新人名", name = "modifierName", example = "李四")
    private String modifierName;

    @ApiModelProperty(value = "更新时间", name = "modifierTime", example = "2020-01-02")
    private LocalDateTime modifierTime;
}
