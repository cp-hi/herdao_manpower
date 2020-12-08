package net.herdao.hdp.manpower.mpclient.dto.staffChanges;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/12/5 5:43 下午
 */
@Data
@ApiModel(value = "保存合同续签详情页")
public class SaveStaffContractRenewalDTO {

    @ApiModelProperty(value = "合同续约 id")
    private Long id;

    @ApiModelProperty(value = "员工 id")
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "原合同 id")
    @NotNull
    private Long staffContractId;

    @ApiModelProperty(value = "合同开始日期")
    private Long contractStartTime;

    @ApiModelProperty(value = "合同结束日期")
    private Long contractEndTime;

    @ApiModelProperty(value = "合同试用期")
    private Double contractProbation;

    @ApiModelProperty(value = "续签主体单位id")
    private Long renewalCompanyId;

    @ApiModelProperty(value = "合同签定基本工资")
    private Double signedBasicWage;

    @ApiModelProperty(value = "续签开始日期")
    private Long renewalStartTime;

    @ApiModelProperty(value = "续签结束日期")
    private Long renewalEndTime;

    @ApiModelProperty(value = "拟续签年数")
    private Double proposedSigningYears;

    @ApiModelProperty(value = "劳动合同续签类型")
    private String renewalType;

    @ApiModelProperty(value = "拟续合同签定基本工资")
    private Double proposedBasicWage;

    @ApiModelProperty(value = "经办人意见")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
