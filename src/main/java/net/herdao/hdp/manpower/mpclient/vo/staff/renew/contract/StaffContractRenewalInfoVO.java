package net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/5 6:22 下午
 */

@Data
@ApiModel(value = "续签合同详情")
public class StaffContractRenewalInfoVO {
    @ApiModelProperty(value = "合同续约 id")
    private Long id;

    @ApiModelProperty(value = "员工 id")
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "员工姓名(员工工号)", name = "staffNameAndCode", example = "张三")
    private String staffNameAndCode;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private String staffScope;

    @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
    private String staffJobType;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    @ApiModelProperty(value = "所在部门 id", name = "orgId", example = "1234")
    private Long orgId;

    @ApiModelProperty(value = "原合同 id")
    @NotNull
    private Long staffContractId;

    @ApiModelProperty(value = "合同开始日期(原合同生效时间)")
    private Long contractStartTime;

    @ApiModelProperty(value = "合同结束日期(原合同到期时间)")
    private Long contractEndTime;

    @ApiModelProperty(value = "合同试用期")
    private BigDecimal contractProbation;

    @ApiModelProperty(value = "续签主体单位id")
    private Long renewalCompanyId;

    @ApiModelProperty(value = "续签主体单位名称")
    private String renewalCompanyName;

    @ApiModelProperty(value = "合同签定基本工资")
    private BigDecimal signedBasicWage;

    @ApiModelProperty(value = "续签开始日期(续签合同生效日期)")
    private Long renewalStartTime;

    @ApiModelProperty(value = "续签结束日期(续签合同到期日期)")
    private Long renewalEndTime;

    @ApiModelProperty(value = "拟续签年数")
    private BigDecimal proposedSigningYears;

    @ApiModelProperty(value = "劳动合同续签类型")
    private String renewalType;

    @ApiModelProperty(value = "拟续合同签定基本工资")
    private BigDecimal proposedBasicWage;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
