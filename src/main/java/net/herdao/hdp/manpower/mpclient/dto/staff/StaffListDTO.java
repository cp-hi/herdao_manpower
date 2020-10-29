package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "花名册列表")
public class StaffListDTO {

    private Long id;

    @ApiModelProperty(value="用户id")
    private Long userId;

    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="工号")
    private String staffCode;

    @ApiModelProperty(value = "所在组织")
    private String orgName;

    @ApiModelProperty(value = "任职岗位")
    private String postName;

    @ApiModelProperty(value="是否停用员工-查询用")
    private Long isStop;

    @ApiModelProperty(value = "高级搜索集团")
    private Long groupId;

    @ApiModelProperty(value = "高级搜索岗位")
    private String postId;

    @ApiModelProperty(value = "所在板块id-高级搜索板块" )
    private Long sectionId;

    @ApiModelProperty(value = "所在管线ID-高级搜索管线" )
    private Long pipelineId;

    @ApiModelProperty(value = "职级-高级搜索职级")
    private Long jobLevelId1;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate entryTime;

    @ApiModelProperty(value="转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate regularTime;

    @ApiModelProperty(value="试用期")
    private Long probPeriod;

    @ApiModelProperty(value = "直属主管")
    private String orgChargeName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="证件号码")
    private String idNumber;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="国籍")
    private String country;

    @ApiModelProperty(value="民族")
    private String nation;

    @ApiModelProperty(value="生育状况")
    private String fertility;

    @ApiModelProperty(value="婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value="最高学历")
    private String educationQua;

    @ApiModelProperty(value="户口类型")
    private String accountType;

    @ApiModelProperty(value="最高学位")
    private String educationDegree;

    @ApiModelProperty(value="入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;

    @ApiModelProperty(value="毕业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty(value="毕业院校")
    private String schoolName;

    @ApiModelProperty(value="专业")
    private String professional;

    @ApiModelProperty(value = "修改人名称" )
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifiedTime;


    @ApiModelProperty(value="人员归属范围名称")
    private String staffScopeName;

    @ApiModelProperty(value="任职类型名称")
    private String jobTypeName;

    @ApiModelProperty(value="性别名称")
    private String sexName;

    @ApiModelProperty(value="生育状况名称")
    private String fertilityName;

    @ApiModelProperty(value="婚姻状况名称")
    private String maritalStatusName;

    @ApiModelProperty(value="户口类型名称")
    private String accountTypeName;
}
