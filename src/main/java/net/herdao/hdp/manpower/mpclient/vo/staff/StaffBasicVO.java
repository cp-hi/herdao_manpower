package net.herdao.hdp.manpower.mpclient.vo.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    /**
     * TODO:: 数据库中的数字代表什么含义？
     */
    @ApiModelProperty(value = "人员归属范围，1：，2：", name = "staffName", example = "1")
    private String staffScope;

    /**
     * TODO:: 数据库中的数字代表什么含义？
     */
    @ApiModelProperty(value = "任职类型", name = "jobType", example = "1")
    private String jobType;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;
}
