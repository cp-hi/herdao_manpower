package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "个人信息")
public class StaffInfoDTO {

    private Long id;

    @ApiModelProperty(value="姓名")
    private String staffName;

    @ApiModelProperty(value="移动电话")
    private String mobile;

    @ApiModelProperty(value="E-mail")
    private String email;

    @ApiModelProperty(value="家庭电话")
    private String homePhone;

    @ApiModelProperty(value="身份证号码")
    private String idNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value="性别")
    private String sex;

    @ApiModelProperty(value="政治面貌")
    private String politicsStatus;

    @ApiModelProperty(value="国籍")
    private String country;

    @ApiModelProperty(value="籍贯")
    private String birthplace;

    @ApiModelProperty(value="民族")
    private String nation;

    @ApiModelProperty(value="婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value="生育状况")
    private String fertility;

    @ApiModelProperty(value="健康情况")
    private String healthStatus;

    @ApiModelProperty(value="户口类型")
    private String accountType;

    @ApiModelProperty(value="户口所在省")
    private String province;

    @ApiModelProperty(value="户口所在城市")
    private String city;

    @ApiModelProperty(value="现住址")
    private String currAddress;

    @ApiModelProperty(value="身高")
    private BigDecimal height;

    @ApiModelProperty(value="体重")
    private BigDecimal weight;

    @ApiModelProperty(value="专业经验")
    private String professionalExperience;

    @ApiModelProperty(value="管理经验")
    private String managementExperience;

    @ApiModelProperty(value="房地产行业经验")
    private String realestateExperience;

    @ApiModelProperty(value="文字能力")
    private String writingAbility;

    @ApiModelProperty(value="专业能力")
    private String professionalCompetence;

    @ApiModelProperty(value="管理能力")
    private String management;

    @ApiModelProperty(value="计算机能力")
    private String computerProficiency;

    @ApiModelProperty(value="兴趣爱好")
    private String interests;

    @ApiModelProperty(value="性格特点")
    private String characteristics;

    @ApiModelProperty(value="优点")
    private String advantage;

    @ApiModelProperty(value="缺点")
    private String shortcoming;

    @ApiModelProperty(value="特长")
    private String specialty;

    @ApiModelProperty(value="主要社会资源")
    private String resources;

    @ApiModelProperty(value="证件类型")
    private String idType;


    @ApiModelProperty(value="性别名称")
    private String sexName;

    @ApiModelProperty(value="政治面貌名称")
    private String politicsStatusName;

    @ApiModelProperty(value="国籍名称")
    private String countryName;

    @ApiModelProperty(value="婚姻状况名称")
    private String maritalStatusName;

    @ApiModelProperty(value="生育状况名称")
    private String fertilityName;

    @ApiModelProperty(value="户口类型名称")
    private String accountTypeName;

    @ApiModelProperty(value="证件类型名称")
    private String idTypeName;
}
