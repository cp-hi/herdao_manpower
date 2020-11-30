package net.herdao.hdp.manpower.mpclient.vo.staff.promote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 7:05 下午
 */

@Data
@ApiModel(value = "职级更新信息")
public class StaffPromoteInfoVO {
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "调动前部门 id", name = "nowOrgId", example = "1234")
    private Long nowOrgId;

    @ApiModelProperty(value = "调动前岗位 id", name = "nowPostId", example = "2345")
    private Long nowPostId;

    @ApiModelProperty(value = "原职级 id", name = "nowJobLevelId", example = "123")
    private Long nowJobLevelId;

    @ApiModelProperty(value = "调动后部门 id", name = "promoteOrgId", example = "4321", required = true)
    private Long promoteOrgId;

    @ApiModelProperty(value = "调动后岗位 id", name = "promotePostId", example = "5432", required = true)
    private Long promotePostId;

    @ApiModelProperty(value = "调动后职级 id", name = "promoteJobLevelId", example = "123", required = true)
    private Long promoteJobLevelId;

    @ApiModelProperty(value = "生效日期", name = "promoteDate", example = "20200901", required = true)
    private Long promoteDate;

    @ApiModelProperty(value = "考察期满日", name = "expireDate", example = "20200901")
    private Long expireDate;

    @ApiModelProperty(value = "岗位年度编制", name = "yearPostPrepareCount", example = "5")
    private Integer yearPostPrepareCount;

    @ApiModelProperty(value = "岗位月度编制", name = "monthPostPrepareCount", example = "5")
    private Integer monthPostPrepareCount;

    @ApiModelProperty(value = "已到岗人数", name = "postHasCount", example = "5")
    private Integer postHasCount;

    @ApiModelProperty(value = "编制是否置换", name = "isPrepareChange", example = "1")
    private Boolean isPrepareChange;

    @ApiModelProperty(value = "经办人意见", name = "remark")
    private String remark;

    @ApiModelProperty(value = "附件列表 id", name = "appendixIds")
    private List<String> appendixIds;
}
