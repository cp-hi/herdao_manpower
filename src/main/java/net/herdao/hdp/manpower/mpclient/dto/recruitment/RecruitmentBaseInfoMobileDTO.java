
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 人才简历-个人基本信息-移动端nDTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "编辑人才简历-个人基本信息DTO")
public class RecruitmentBaseInfoMobileDTO {

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String talentName;

    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称")
    private String englishName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;

    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String certificateNo;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 人才类别
     */
    @ApiModelProperty(value="人才类别")
    private String talentType;

    /**
     * 组织id
     */
    @ApiModelProperty(value="组织id")
    private Long orgId;

    /**
     * 简历来源
     */
    @ApiModelProperty(value="简历来源")
    private String resumeSource;

    /**
     * 民族
     */
    @ApiModelProperty(value="民族")
    private String nation;

    /**
     * 籍贯
     */
    @ApiModelProperty(value="籍贯")
    private String birthplace;

    /**
     * 生育状况
     */
    @ApiModelProperty(value="生育状况")
    private String fertility;


    /**
     * 婚姻状况
     */
    @ApiModelProperty(value="婚姻状况")
    private String maritalStatus;

    /**
     * 健康情况
     */
    @ApiModelProperty(value="健康情况")
    private String healthStatus;

    /**
     * 体重
     */
    @ApiModelProperty(value="体重")
    private String weight;

    /**
     * 身高
     */
    @ApiModelProperty(value="身高")
    private String height;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value="政治面貌")
    private String politicalLandscape;

    /**
     * 紧急联系人
     */
    @ApiModelProperty(value="紧急联系人")
    private String emergencyContacts;

    /**
     * 紧急联系电话
     */
    @ApiModelProperty(value="紧急联系电话")
    private String emergencyPhone;

    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    private String highestEducation;

    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;

    /**
     * 最高学历证书号
     */
    @ApiModelProperty(value="最高学历证书号")
    private String highestDiplomano;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String graduated;

    /**
     * 家庭电话
     */
    @ApiModelProperty(value="家庭电话")
    private String homePhone;

    /**
     * 现住址
     */
    @ApiModelProperty(value="现住址")
    private String nowAddress;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码")
    private String zipcode;

    /**
     * 户口地址
     */
    @ApiModelProperty(value="户口地址")
    private String accountAddress;

    /**
     *
     * 兴趣爱好
     */
    @ApiModelProperty(value="兴趣爱好")
    private String interests;

    /**
     * 特长
     */
    @ApiModelProperty(value="特长")
    private String specialty;

    /**
     * 优点
     */
    @ApiModelProperty(value="优点")
    private String advantage;

    /**
     * 缺点
     */
    @ApiModelProperty(value="缺点")
    private String shortcoming;

    /**
     * 性格特点
     */
    @ApiModelProperty(value="性格特点")
    private String characteristics;

    /**
     * 是否接受外派
     */
    @ApiModelProperty(value="是否接受外派")
    private Boolean isAcceptAssignment;

    /**
     * 是否有亲戚朋友在本公司
     */
    @ApiModelProperty(value="是否有亲戚朋友在本公司")
    private Boolean isRelativeCompany;

    /**
     * 国籍
     */
    @ApiModelProperty(value="国籍")
    private String nationality;

    /**
     * 亲戚朋友其职位与姓名
     */
    @ApiModelProperty(value="亲戚朋友其职位与姓名")
    private String relativePostName;

    /**
     * 可接受外派地点
     */
    @ApiModelProperty(value="可接受外派地点")
    private String acceptAssignmentLocation;

    /**
     * 申请人签名
     */
    @ApiModelProperty(value="申请人签名")
    private String applicantSign;

    /**
     * 简历入库时间
     */
    @ApiModelProperty(value="简历入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date resumeAccessTime;

    /**
     * 获得招聘信息途径
     */
    @ApiModelProperty(value="获得招聘信息途径")
    private String resumeAccess;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 户口类型
     */
    @ApiModelProperty(value="户口类型")
    private String accountType;

}
