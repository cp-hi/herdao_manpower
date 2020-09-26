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
@ApiModel(value = "员工主页")
@AllArgsConstructor
public class StaffHomePage {

    public StaffHomePage(){}

    @ApiModelProperty(value="员工信息")
    private Staff staff;

    @ApiModelProperty(value="任职轨迹")
    private List<Userposthistory> uphList;

    @ApiModelProperty(value="工作经历")
    private List<Workexperience> expList;

    @ApiModelProperty(value="家庭情况")
    private List<Familystatus> familyList;

}
