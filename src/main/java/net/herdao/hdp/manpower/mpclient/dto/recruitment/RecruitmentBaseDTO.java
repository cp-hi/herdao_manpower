
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才简历-个人基本情况 从业情况与求职意向
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-个人基本情况 从业情况与求职意向")
public class RecruitmentBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    private Long id;

    /**
     * 中文名称
     */
    @ApiModelProperty(value="姓名")
    private String talentName;

    /**
     * 移动电话
     */
    @ApiModelProperty(value="移动电话")
    private String mobile;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private String age;

    /**
     * 人才类型
     */
    @ApiModelProperty(value="人才类型")
    private String talentType;

    /**
     * 简历来源
     */
    @ApiModelProperty(value="简历来源")
    private String resumeSource;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private LocalDateTime birthday;

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
    private BigDecimal weight;

    /**
     * 身高
     */
    @ApiModelProperty(value="身高")
    private BigDecimal height;

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
     * 工作年限
     */
    @ApiModelProperty(value="工作年限")
    private String workAge;

    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    private String workdate;

    /**
     * 意向岗位
     */
    @ApiModelProperty(value="意向岗位")
    private String postName;

    /**
     * 最低薪资（月薪/年薪）
     */
    @ApiModelProperty(value="最低薪资（月薪/年薪）")
    private String minimumLevelincome;

    /**
     * 期望薪资（月薪/年薪）
     */
    @ApiModelProperty(value="期望薪资（月薪/年薪）")
    private String expectedLevelincome;

    /**
     * 其他要求
     */
    @ApiModelProperty(value="其他要求")
    private String otherRequest;

    /**
     * 可到职日期
     */
    @ApiModelProperty(value="可到职日期")
    private String inductionTime;

    /**
     * 性格特点
     */
    @ApiModelProperty(value="性格特点")
    private String characteristics;

    /**
     * 缺点
     */
    @ApiModelProperty(value="缺点")
    private String shortcoming;

    /**
     * 优点
     */
    @ApiModelProperty(value="优点")
    private String advantage;

    /**
     * 特长
     */
    @ApiModelProperty(value="特长")
    private String specialty;

    /**
     * 兴趣爱好
     */
    @ApiModelProperty(value="兴趣爱好")
    private String interests;

    /**
     * 户口地址
     */
    @ApiModelProperty(value="户口地址")
    private String accountAddress;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码")
    private String zipcode;

    /**
     * 现住址
     */
    @ApiModelProperty(value="现住址")
    private String nowAddress;

    /**
     * 家庭电话
     */
    @ApiModelProperty(value="家庭电话")
    private String homePhone;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String graduated;

    /**
     * 最高学历证书号
     */
    @ApiModelProperty(value="最高学历证书号")
    private String highestDiplomano;

    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;

    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    private String highestEducation;

    /**
     * 是否有亲戚朋友在本公司
     */
    @ApiModelProperty(value="是否有亲戚朋友在本公司")
    private Integer isRelativeCompany;

    /**
     * 是否接受外派
     */
    @ApiModelProperty(value="是否接受外派")
    private Integer isAcceptAssignment;

    /**
     * 职位跟姓名
     */
    @ApiModelProperty(value="其职位与姓名")
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
     * 获取岗位招聘信息途径
     */
    @ApiModelProperty(value="获取岗位招聘信息途径")
    private String resumeAccess;


    /**
     * 简历入库时间
     */
    @ApiModelProperty(value="简历入库时间")
    private LocalDateTime resumeAccessTime;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remarks;

}
