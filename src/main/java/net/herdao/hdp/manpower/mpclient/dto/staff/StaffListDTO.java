package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateConverter;

import java.time.LocalDate;
import java.util.Date;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "花名册列表")
@ExcelIgnoreUnannotated
public class StaffListDTO {

    private Long id;

    @ApiModelProperty(value="用户id")
    @ExcelProperty(value = "用户id",order = 18)
    private Long userId;

    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "姓名",order = 1)
    private String staffName;

    @ApiModelProperty(value="工号")
    @ExcelProperty(value = "工号",order = 2)
    private String staffCode;

    @ApiModelProperty(value = "所在组织id")
    private Long orgId;

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
    @ExcelProperty(value = "员工状态",order = 3)
    private String staffScope;

    @ApiModelProperty(value="任职类型")
    @ExcelProperty(value = "任职类型",order = 17)
    private String jobType;

    @ApiModelProperty(value="入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "入职本公司日期",order = 16,converter = LocalDateConverter.class)
    private LocalDate entryTime;

    @ApiModelProperty(value="转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate regularTime;

    @ApiModelProperty(value="试用期")
    private Long probPeriod;

    @ApiModelProperty(value = "直属主管")
    private String orgChargeWorkName;

    @ApiModelProperty(value="移动电话")
    @ExcelProperty(value = "联系电话",order = 4)
    private String mobile;

    @ApiModelProperty(value="证件号码")
    @ExcelProperty(value = "身份证号码",order = 15)
    private String idNumber;

    @ApiModelProperty(value="性别")
    @ExcelProperty(value = "性别",order = 14)
    private String sex;

    @ApiModelProperty(value="国籍")
    @ExcelProperty(value = "国籍",order = 13)
    private String country;

    @ApiModelProperty(value="民族")
    @ExcelProperty(value = "民族",order = 12)
    private String nation;

    @ApiModelProperty(value="生育状况")
    @ExcelProperty(value = "生育状况",order = 11)
    private String fertility;

    @ApiModelProperty(value="婚姻状况")
    @ExcelProperty(value = "婚姻状况",order = 10)
    private String maritalStatus;

    @ApiModelProperty(value="最高学历")
    @ExcelProperty(value = "最高学历",order = 9)
    private String educationQua;

    @ApiModelProperty(value="户口类型")
    @ExcelProperty(value = "户口类型",order = 8)
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
    @ExcelProperty(value = "专业",order = 7)
    private String professional;

    @ApiModelProperty(value = "修改人名称" )
    @ExcelProperty(value = "修改人名称",order = 5)
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "修改时间",order = 6)
    private Date modifiedTime;

}
