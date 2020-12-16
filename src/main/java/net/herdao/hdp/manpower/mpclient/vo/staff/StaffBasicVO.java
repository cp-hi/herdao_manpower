package net.herdao.hdp.manpower.mpclient.vo.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.admin.api.entity.SysDictItem;

import javax.validation.Valid;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 2:57 下午
 */
@Data
@ApiModel(value = "人事调动详情页-员工基本信息")
public class StaffBasicVO {
    @ApiModelProperty(value = "基本信息", name = "staffInfo")
    private StaffInfo staffInfo;

    @ApiModelProperty(value = "员工任职信息", name = "staffInfo")
    private StaffPostInfo staffPostInfo;

    @Data
    public static class StaffInfo {
        @ApiModelProperty(value = "用户 id", name = "userId", example = "1")
        private Long userId;

        @ApiModelProperty(value = "员工姓名(员工工号)", name = "staffNameAndCode", example = "张三")
        private String staffNameAndCode;

        @ApiModelProperty(value = "人员归属范围", name = "staffScope", example = "在职人员")
        private SysDictItem staffScope;

        @ApiModelProperty(value = "人员任职类型", name = "staffJobType", example = "在职人员")
        private SysDictItem staffJobType;

        @ApiModelProperty(value = "入职日期", name = "entryTime", example = "20201003")
        private Long entryTime;
    }

    @Data
    public static class StaffPostInfo{
        @ApiModelProperty(value = "组织/部门 id", name = "orgId")
        private Long orgId;

        @ApiModelProperty(value = "组织/部门名称", name = "orgName")
        private String orgName;

        @ApiModelProperty(value = "岗位 id", name = "postId")
        private Long postId;

        @ApiModelProperty(value = "岗位名称", name = "postName")
        private String postName;

        @ApiModelProperty(value = "职级 id", name = "jobLevelId")
        private Long  jobLevelId;

        @ApiModelProperty(value = "职级名称", name = "jobLevelName")
        private String jobLevelName;
    }
}
