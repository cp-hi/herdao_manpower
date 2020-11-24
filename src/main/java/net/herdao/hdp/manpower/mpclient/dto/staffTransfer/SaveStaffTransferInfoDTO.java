package net.herdao.hdp.manpower.mpclient.dto.staffTransfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 3:24 下午
 */

@Data
@ApiModel(value = "人事调动详情")
public class SaveStaffTransferInfoDTO {

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1234", required = true)
    private Long userId;

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234", required = true)
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostId", example = "2345", required = true)
    private Long nowPostId;

    @ApiModelProperty(value = "原职级 id", name = "jobLevelId", example = "123", required = true)
    private Long nowJobLevelId;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321", required = true)
    private Long transOrgId;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostId", example = "5432", required = true)
    private Long transPostId;

    @ApiModelProperty(value = "雕工后职级 id", name = "jobLevelId", example = "123", required = true)
    private Long transJobLevelId;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901", required = true)
    private Long transStartDate;

    @ApiModelProperty(value = "岗位年度编制", name = "yearPostPrepareCount", example = "5")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "岗位月度编制", name = "monthPostPrepareCount", example = "5")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数", name = "postHasCount", example = "5")
    private Integer postHasCount;

    @ApiModelProperty(value = "编制是否置换", name = "isPrepareChange", example = "1")
    private Boolean prepareChange;

    @ApiModelProperty(value = "合同年限", name = "contractPeriod")
    private Double contractPeriod;

    @ApiModelProperty(value = "使用期", name = "probation")
    private Double probation;

    @ApiModelProperty(value = "本公司工龄", name = "probation")
    private Double companySeniority;

    @ApiModelProperty(value = "合生珠江系工龄", name = "threeGroupSeniority")
    private Double threeGroupSeniority;

    @ApiModelProperty(value = "工资发放单位名称", name = "paidUnitsName")
    private String paidUnitsName;

    @ApiModelProperty(value = "公积金购买单位名称", name = "fundUnitsName")
    private String fundUnitsName;

    @ApiModelProperty(value = "社保购买单位名称", name = "securityUnitName")
    private Long securityUnitName;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
