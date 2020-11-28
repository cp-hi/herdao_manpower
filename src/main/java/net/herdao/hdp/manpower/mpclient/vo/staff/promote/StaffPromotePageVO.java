package net.herdao.hdp.manpower.mpclient.vo.staff.promote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 7:03 下午
 */

@Data
@ApiModel(value = "职级分页列表")
public class StaffPromotePageVO {
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private Long transStartDate;

    @ApiModelProperty(value = "调动前部门名称", name = "nowOrgName", example = "部门 A")
    private String nowOrgName;

    @ApiModelProperty(value = "调动前岗位名称", name = "nowOrgName", example = "岗位 a")
    private String nowPostName;

    @ApiModelProperty(value = "调动前职级名称", name = "nowJobLevelName")
    private String nowJobLevelName;

    @ApiModelProperty(value = "调动后部门名称", name = "transOrgName", example = "部门 B")
    private String transOrgName;

    @ApiModelProperty(value = "调动后岗位名称", name = "transPostName", example = "岗位 b")
    private String transPostName;

    @ApiModelProperty(value = "调动后职级名称", name = "transJobLevelName")
    private String transJobLevelName;

    @ApiModelProperty(value = "更新信息", name = "updateInfo", example = "由李四于2020-01-02更新")
    private String updateInfo;
}
