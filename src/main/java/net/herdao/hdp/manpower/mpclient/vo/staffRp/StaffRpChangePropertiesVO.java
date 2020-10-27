
package net.herdao.hdp.manpower.mpclient.vo.staffRp;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.constraints.Pattern;

/**
 * 员工奖惩属性变更
 * @author andy
 * @date 2020-20-21 10:01:48
 */
@Data
@ApiModel(value = "员工奖惩属性变更")
public class StaffRpChangePropertiesVO {

	/**
	 * 奖惩内容
	 */
 	@ApiModelProperty(value = "奖惩内容")
	private String content;

	/**
	 * 奖惩金额
	 */
	@ApiModelProperty(value = "奖惩金额")
	private String amount;

	/**
	 * 奖惩原因
	 */
	@ApiModelProperty(value = "奖惩原因")
	private String reason;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
}
