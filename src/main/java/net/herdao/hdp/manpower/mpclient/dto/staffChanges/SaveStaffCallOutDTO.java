package net.herdao.hdp.manpower.mpclient.dto.staffChanges;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 6:48 下午
 */
@Data
@ApiModel(value = "保存调出详情页")
public class SaveStaffCallOutDTO {
    @ApiModelProperty(value = "人事调出 id", name = "id", example = "1", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "对应调入单 id", name = "transApproveId", example = "1", required = true)
    private Long transApproveId;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1", required = true)
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234", required = true)
    @NotNull
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostOrgId", example = "2345", required = true)
    @NotNull
    private Long nowPostOrgId;

    @ApiModelProperty(value = "原职级 id", name = "nowJobLevelId", example = "123", required = true)
    @NotNull
    private Long nowJobLevelId;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321", required = true)
    @NotNull
    private Long transOrgId;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostId", example = "5432", required = true)
    @NotNull
    private Long transPostOrgId;

    @ApiModelProperty(value = "调动后职级 id", name = "transJobLevelId", example = "123", required = true)
    @NotNull
    private Long transJobLevelId;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901", required = true)
    @NotNull
    private Long transStartDate;

    @ApiModelProperty(value = "编制是否置换", name = "isPrepareChange", example = "1")
    private Boolean isPrepareChange;

    @ApiModelProperty(value = "合同年限", name = "contractPeriod")
    private BigDecimal contractPeriod;

    @ApiModelProperty(value = "本公司工龄", name = "companySeniority")
    private BigDecimal companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄", name = "threeGroupSeniority")
    private BigDecimal threeGroupSeniority;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private String appendixIds;
}
