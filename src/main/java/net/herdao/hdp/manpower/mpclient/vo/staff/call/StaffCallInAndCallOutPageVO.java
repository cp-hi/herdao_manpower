package net.herdao.hdp.manpower.mpclient.vo.staff.call;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:45 下午
 */

@Data
@ApiModel(value = "调入调出分页列表")
public class StaffCallInAndCallOutPageVO {
    @ApiModelProperty(value = "调入调出 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工工号", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private Long transStartDate;

    @ApiModelProperty(value = "调动前部门名称", name = "nowOrgName", example = "部门 A")
    private String nowOrgName;

    @ApiModelProperty(value = "调动前岗位名称", name = "nowPostOrgName", example = "岗位 a")
    private String nowPostOrgName;

    @ApiModelProperty(value = "调动前职级名称", name = "nowJobLevelName", example = "123")
    private String nowJobLevelName;

    @ApiModelProperty(value = "调动后部门名称", name = "transOrgName", example = "部门 ")
    private String transOrgName;

    @ApiModelProperty(value = "调动后岗位名称", name = "transPostOrgName", example = "岗位 b")
    private String transPostOrgName;

    @ApiModelProperty(value = "调动后职级名称", name = "transJobLevelName", example = "123")
    private String transJobLevelName;

    @ApiModelProperty(value = "更新信息", name = "updateInfo", example = "由李四于2020-01-02更新")
    private String updateInfo;
}
