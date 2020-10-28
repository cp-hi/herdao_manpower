package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "新增/修改员工")
public class StaffDetailDTO {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="证件类型")
    private String idType;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="E-mail")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="入职本公司日期")
    private LocalDate entryTime;

    @ApiModelProperty(value="试用期")
    private Long probPeriod;

    @ApiModelProperty(value="任职类型")
    private String jobType;

    @ApiModelProperty(value="人员性质")
    private String personnelNature;

    @ApiModelProperty(value="人员归属范围")
    private String staffScope;

    @ApiModelProperty(value="岗位外键")
    private Long postId;

    @ApiModelProperty(value="所属部门外键")
    private Long orgDeptId;

}
