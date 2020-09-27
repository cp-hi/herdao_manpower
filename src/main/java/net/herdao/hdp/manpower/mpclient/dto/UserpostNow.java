package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;

import java.util.List;

@Data
@ApiModel(value = "现任职情况")
@AllArgsConstructor
public class UserpostNow {
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    private String staffCode;

    @ApiModelProperty(value="任职日期")
    private String startDate;

    @ApiModelProperty(value="所在组织")
    private String orgName;

    @ApiModelProperty(value="板块")
    private String  sectionName;

    @ApiModelProperty(value="管线")
    private String pipelineName;

    @ApiModelProperty(value="岗位")
    private String postName;

    @ApiModelProperty(value="职级")
    private String jobLevelCode;

    @ApiModelProperty(value="是否定制岗位")
    private String customPost;



}
