package net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
public class StaffContractRrenewalPage {
}
