package net.herdao.hdp.manpower.mpclient.vo.staff.promote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 该类接受从数据库中拿到的列表原始数据及数据类型完全一致
 *
 * 在 service 层做和 xxxPageVO 中某些字段的数据类型转换
 * e.g. LocalDateTime -> long
 *      updateName & updateTime ->  String： 由 xxx 更新于 2020-01-01
 *
 * @Author Liu Chang
 * @Date 2020/12/8 4:37 下午
 */
@Data
@ApiModel(value = "晋升分页列表")
public class StaffPromotePage {
    @ApiModelProperty(value = "人事调动 id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String  staffName;

    @ApiModelProperty(value = "员工编码", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "晋升类型：1-晋升 2-降级", name = "promoteType", example = "1")
    private Integer promoteType;

    @ApiModelProperty(value = "生效日期", name = "transStartDate", example = "20200901")
    private LocalDateTime promoteStartDate;

    @ApiModelProperty(value = "调动前部门名称", name = "nowOrgName", example = "部门 A")
    private String nowOrgName;

    @ApiModelProperty(value = "调动前岗位名称", name = "nowOrgName", example = "岗位 a")
    private String nowPostName;

    @ApiModelProperty(value = "调动前职级名称", name = "nowJobLevelName")
    private String nowJobLevelName;

    @ApiModelProperty(value = "调动后部门名称", name = "promoteOrgName", example = "4321", required = true)
    private String promoteOrgName;

    @ApiModelProperty(value = "调动后岗位名称", name = "promotePostName", example = "5432", required = true)
    private String promotePostName;

    @ApiModelProperty(value = "调动后职级名称", name = "promoteJobLevelName", example = "123", required = true)
    private String promoteJobLevelName;

    @ApiModelProperty(value = "更新人名", name = "modifierName", example = "李四")
    private String modifierName;

    @ApiModelProperty(value = "更新时间", name = "modifierTime", example = "2020-01-02。")
    private LocalDateTime modifierTime;
}
