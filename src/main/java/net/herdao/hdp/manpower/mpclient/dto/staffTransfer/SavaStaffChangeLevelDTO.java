package net.herdao.hdp.manpower.mpclient.dto.staffTransfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 7:19 下午
 */
@Data
@ApiModel(value = "保存升/降级详情页")
public class SavaStaffChangeLevelDTO {
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1", required = true)
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234", required = true)
    @NotNull
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostId", example = "2345", required = true)
    @NotNull
    private Long nowPostId;

    @ApiModelProperty(value = "原职级 id", name = "jobLevelId", example = "123", required = true)
    @NotNull
    private Long nowJobLevelId;

    @ApiModelProperty(value = "调动后部门 id", name = "transOrgId", example = "4321", required = true)
    @NotNull
    private Long transOrgId;

    @ApiModelProperty(value = "调动后岗位 id", name = "transPostId", example = "5432", required = true)
    @NotNull
    private Long transPostId;

    @ApiModelProperty(value = "调动后职级 id", name = "jobLevelId", example = "123", required = true)
    @NotNull
    private Long transJobLevelId;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901", required = true)
    @NotNull
    private Long transStartDate;

    @ApiModelProperty(value = "考察期满日", name = "expireDate", example = "20200901")
    private Long expireDate;

    @ApiModelProperty(value = "岗位年度编制", name = "yearPostPrepareCount", example = "5")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "岗位月度编制", name = "monthPostPrepareCount", example = "5")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数", name = "postHasCount", example = "5")
    private Integer postHasCount;

    @ApiModelProperty(value = "编制是否置换", name = "isPrepareChange", example = "1")
    private Boolean prepareChange;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
