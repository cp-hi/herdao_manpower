package net.herdao.hdp.manpower.mpclient.vo.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.admin.api.entity.SysDictItem;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 2:57 下午
 */
@Data
@ApiModel(value = "人事调动详情页-员工基本信息")
public class StaffBasicVO {

    @ApiModelProperty(value = "员工 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名", name = "staffName", example = "张三")
    private String staffName;

    @ApiModelProperty(value = "员工工号", name = "staffCode", example = "123456")
    private String staffCode;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private SysDictItem staffScope;

    @ApiModelProperty(value = "任职类型", name = "jobType", example = "1")
    private SysDictItem jobType;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    private Long orgId;

    private String orgName;

    private Long pastId;

    private String pastName;

    private Long  jobLevelId;

    private String jobLevelName;
}
