package net.herdao.hdp.manpower.mpclient.dto.staffUserpost;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *任职情况list DTO
 */
@Data
@ApiModel(value = "任职情况list DTO")
public class UserpostDTO {
    @ExcelIgnore
    @ApiModelProperty(value="id")
    private String id;

    @ExcelProperty(value = "员工姓名")
    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ExcelProperty(value = "员工工号")
    @ApiModelProperty(value="员工工号")
    private String staffCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "任职日期")
    @ApiModelProperty(value="任职日期")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="免职日期")
    @ExcelProperty(value = "免职日期")
    private LocalDate endDate;

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
    private String jobLevelName;

    @ExcelProperty(value = "是否定制岗位")
    @ApiModelProperty(value="是否定制岗位")
    private String customPost;

    @ApiModelProperty(value="任职集团")
    @ExcelProperty(value = "任职集团")
    private String groupName;

    @ApiModelProperty(value="任职公司")
    @ExcelProperty(value = "任职公司")
    private String company;

    @ApiModelProperty(value="任职公司")
    @ExcelProperty(value = "任职公司")
    private String deptName;

    @ApiModelProperty(value = "是否定编岗位" )
    private String officePostTypeName;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    private String updateDesc;
    /**
     * 集团id
     */
    @ApiModelProperty(value="集团id")
    @ExcelProperty(value = "集团id")
    private String groupId;
    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelIgnore
    private String staffId;
    /**
     * 所在组织id
     */
    @ApiModelProperty(value = "所在组织id")
    private Long orgId;
}
