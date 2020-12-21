package net.herdao.hdp.manpower.mpclient.vo.staff.positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 2:57 下午
 */
@Data
@ApiModel(value = "转正管理(员工)基础信息-（根据id查询基本信息")
public class StaffBasicPositiveVO {
    @ApiModelProperty(value = "用户 id", name = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "员工姓名(员工工号)", name = "staffNameAndCode", example = "张三")
    private String staffNameAndCode;

    @ApiModelProperty(value = "集团 Id", name = "groupId", example = "123")
    private Long groupId;

    @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
    private String staffScope;

    @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
    private String staffJobType;

    @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
    private Long entryTime;

    @ApiModelProperty(value = "组织/部门 id", name = "orgId")
    private Long nowOrgId;

    @ApiModelProperty(value = "组织/部门名称", name = "orgName")
    private String nowOrgName;

    @ApiModelProperty(value = "岗位 id", name = "postId")
    private Long nowPostOrgId;

    @ApiModelProperty(value = "岗位名称", name = "postName")
    private String nowPostOrgName;

    @ApiModelProperty(value = "职级 id", name = "jobLevelId")
    private Long  nowJobLevelId;

    @ApiModelProperty(value = "职级名称", name = "jobLevelName")
    private String nowJobLevelName;


    @ApiModelProperty(value="试用日期")
    private long entryTime1;
}
