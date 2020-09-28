package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class UserpostDTO {
    @ExcelProperty(value = "员工姓名")
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ExcelProperty(value = "员工工号")
    @ApiModelProperty(value="员工工号")
    private String staffCode;

    @ExcelProperty(value = "任职日期")
    @ApiModelProperty(value="任职日期")
    private String startDate;

    @ExcelProperty(value = "所在组织")
    @ApiModelProperty(value="所在组织")
    private String orgName;

    @ExcelProperty(value = "板块")
    @ApiModelProperty(value="板块")
    private String  sectionName;

    @ExcelProperty(value = "管线")
    @ApiModelProperty(value="管线")
    private String pipelineName;

    @ExcelProperty(value = "岗位")
    @ApiModelProperty(value="岗位")
    private String postName;

    @ExcelProperty(value = "职级")
    @ApiModelProperty(value="职级")
    private String jobLevelCode;

    @ExcelProperty(value = "是否定制岗位")
    @ApiModelProperty(value="是否定制岗位")
    private String customPost;

}
