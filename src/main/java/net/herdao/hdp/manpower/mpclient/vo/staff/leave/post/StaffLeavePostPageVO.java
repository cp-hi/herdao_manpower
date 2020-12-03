package net.herdao.hdp.manpower.mpclient.vo.staff.leave.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 10:54 上午
 */
@Data
@ApiModel(value = "离职分页列表")
public class StaffLeavePostPageVO {

    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "所属组织")
    private String nowOrgName;

    @ApiModelProperty(value = "岗位")
    private String nowPostName;

    @ApiModelProperty(value = "职级")
    private String nowJobLevelName;

    @ApiModelProperty(value = "任职类型")
    private String jobType;

    @ApiModelProperty(value = "入职日期")
    private Long entryTime;

    @ApiModelProperty(value = "司龄")
    private Double companySeniority;

    @ApiModelProperty(value = "计划离职日期")
    private Long leaveTime;

    @ApiModelProperty(value = "离职原因")
    private String leaveReason;

    @ApiModelProperty(value = "更新信息", name = "updateInfo", example = "由李四于2020-01-02更新")
    private String updateInfo;
}
