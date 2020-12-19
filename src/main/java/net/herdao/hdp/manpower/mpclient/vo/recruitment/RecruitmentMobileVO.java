package net.herdao.hdp.manpower.mpclient.vo.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 手机端-个人信息VO
 * @author Andy
 */
@Data
@ApiModel(value = "手机端-个人信息VO")
public class RecruitmentMobileVO {

    /**
     * 家庭成员姓名
     */
    @ApiModelProperty(value="家庭成员姓名")
    private String name;

    /**
     * 关系
     */
    @ApiModelProperty(value="关系")
    private String relations;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private String age;

    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    private String professional;

    /**
     * 工作单位/就读学校
     */
    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;

    /**
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    private String address;


    /**
     * 入学日期
     */
    @ApiModelProperty(value="入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date period;

    /**
     * 毕业日期
     */
    @ApiModelProperty(value="毕业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date todate;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String schoolName;

    /**
     * 学位
     */
    @ApiModelProperty(value="学位")
    private String degree;

    /**
     * 学制
     */
    @ApiModelProperty(value="学制")
    private String schoolSystem;

    /**
     * 学历
     */
    @ApiModelProperty(value="学历")
    private String educationQua;

    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String talentName;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 民族
     */
    @ApiModelProperty(value="民族")
    private String nation;

    /**
     * 婚姻状况
     */
    @ApiModelProperty(value="婚姻状况")
    private String maritalStatus;

    /**
     * 生育状况
     */
    @ApiModelProperty(value="生育状况")
    private String fertility;

    /**
     * 健康情况
     */
    @ApiModelProperty(value="健康情况")
    private String healthStatus;

    /**
     * 身高
     */
    @ApiModelProperty(value="身高")
    private String height;

    /**
     * 体重
     */
    @ApiModelProperty(value="体重")
    private String weight;

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
     * 国籍
     */
    @ApiModelProperty(value="国籍")
    private String nationality;

    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称")
    private String englishName;

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
     * 移动电话
     */
    @ApiModelProperty(value="移动电话")
    private String mobile;

    /**
     * 户口类型
     */
    @ApiModelProperty(value="户口类型")
    private String accountType;

    /**
     * 简历来源
     */
    @ApiModelProperty(value="简历来源")
    private String resumeSource;

}
