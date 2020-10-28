
package net.herdao.hdp.manpower.mpclient.dto.staffContract;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

/**
 * 员工合同签订列表表单DTO
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Data
@ApiModel(value = "员工合同签订列表表单DTO")
public class StaffcontractFormDTO extends BaseModel<StaffcontractFormDTO> {
    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @ApiModelProperty(value="员工ID")
    private String staffId;

    /**
     * 劳动合同起始日期
     */
    @ApiModelProperty(value="劳动合同起始日期")
    private String startDate;

    /**
     * 劳动合同结束日期
     */
    @ApiModelProperty(value="劳动合同结束日期")
    private String endDate;

    /**
     * 合同签订主体
     */
    @ApiModelProperty(value="合同签订主体")
    private String companyCode;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    private String contractId;

    /**
     * 合同是否生效
     */
    /**
     * 是否当前生效合同 （合同是否生效）,0生效 ，1失效
     */
    @ApiModelProperty(value="是否当前生效合同 （合同是否生效）,0生效，1失效")
    private Boolean newest;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限")
    @ExcelProperty(value = "合同期限")
    private String contractPeriod;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期")
    private Long probationMonth;


}
