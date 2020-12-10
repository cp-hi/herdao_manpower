package net.herdao.hdp.manpower.mpclient.vo.staff.leave.post;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Date 2020/12/10 3:56 下午
 */
@Data
@ApiModel(value = "离职分页列表")
public class StaffLeavePostPage {
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
    private LocalDateTime entryTime;

    @ApiModelProperty(value = "司龄")
    private Double companySeniority;

    @ApiModelProperty(value = "计划离职日期")
    private LocalDateTime leaveTime;

    @ApiModelProperty(value = "离职原因")
    private String leaveReason;

    @ApiModelProperty(value = "修改人姓名")
    private String modifierName;

    @ApiModelProperty(value = "修改人时间")
    private LocalDateTime modifierTime;
}
