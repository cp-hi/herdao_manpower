package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "组织-人员管理")
public class StaffOrgDTO {

    @ApiModelProperty(value="用户id")
    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value="部门名称")
    private String orgName;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "职级1")
    private Long jobLevelId1;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="E-mail")
    private String email;

}
