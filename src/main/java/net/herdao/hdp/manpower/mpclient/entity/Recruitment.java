
package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-25 10:47:36
 */
@Data
@TableName("mp_recruitment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "人才表")
public class Recruitment extends Model<Recruitment> {

    private static final long serialVersionUID = 1L;

    /**
     * 人才ID
     */
    @TableId
    @ApiModelProperty(value="人才ID")
    private Long id;
    /**
     * 人才类型
     */
    @ApiModelProperty(value="人才类型")
    private String talentType;
    /**
     * 来源类型
     */
    @ApiModelProperty(value="来源类型")
    private String fromType;
    /**
     * 中文名称
     */
    @ApiModelProperty(value="中文名称")
    private String talentName;
    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称")
    private String engshName;
    /**
     * 应聘岗位ID
     */
    @ApiModelProperty(value="应聘岗位ID")
    private Long recruitmentPostId;
    /**
     * 应聘岗位OID
     */
    @ApiModelProperty(value="应聘岗位OID")
    private String recruitmentPostOid;
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
     * 移动电话
     */
    @ApiModelProperty(value="移动电话")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;
    /**
     * 毕业院校
     */
    @ApiModelProperty(value="毕业院校")
    private String graduated;
    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;
    /**
     * 入学日期
     */
    @ApiModelProperty(value="入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    /**
     * 毕业时间
     */
    @ApiModelProperty(value="毕业时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    /**
     * 最高学历证书号
     */
    @ApiModelProperty(value="最高学历证书号")
    private String highestDiplomano;
    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    private String highestEducation;
    /**
     * 最高学位
     */
    @ApiModelProperty(value="最高学位")
    private String educationDegree;
    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;
    /**
     * 职称及职业资格
     */
    @ApiModelProperty(value="职称及职业资格")
    private String title;
    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String assessmentUnit;
    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String professionalQualifications;
    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualificationUnit;
    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workdate;
    /**
     * 最近工作单位名称
     */
    @ApiModelProperty(value="最近工作单位名称")
    private String finalJobCompany;
    /**
     * 最近从事岗位
     */
    @ApiModelProperty(value="最近从事岗位")
    private String finalPostName;
    /**
     * 其他个人情况
     */
    @ApiModelProperty(value="其他个人情况")
    private String personRemark;
    /**
     * 可到职日期
     */
    @ApiModelProperty(value="可到职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date inductionTime;
    /**
     * 是否有简历
     */
    @ApiModelProperty(value="是否有简历")
    private Integer isResume;
    /**
     * 信息录入时间
     */
    @ApiModelProperty(value="信息录入时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date informationCreateTime;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 简历路径
     */
    @ApiModelProperty(value="简历路径")
    private String resumePath;
    /**
     * 籍贯
     */
    @ApiModelProperty(value="籍贯")
    private String birthplace;
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
    private BigDecimal height;
    /**
     * 体重
     */
    @ApiModelProperty(value="体重")
    private BigDecimal weight;
    /**
     * 政治面貌
     */
    @ApiModelProperty(value="政治面貌")
    private String politicalLandscape;
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
     * 户口类型
     */
    @ApiModelProperty(value="户口类型")
    private String accountType;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value="身份证号码")
    private String idnumber;
    /**
     * 家庭电话
     */
    @ApiModelProperty(value="家庭电话")
    private String homePhone;
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
     * 性格特点
     */
    @ApiModelProperty(value="性格特点")
    private String characteristics;
    /**
     * 主要社会资源
     */
    @ApiModelProperty(value="主要社会资源")
    private String resources;
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
     * 专业经验
     */
    @ApiModelProperty(value="专业经验")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date professionalExperience;
    /**
     * 管理经验
     */
    @ApiModelProperty(value="管理经验")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date managementExperience;
    /**
     * 房地产行业经验
     */
    @ApiModelProperty(value="房地产行业经验")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date realEstateExperience;
    /**
     * 文字能力
     */
    @ApiModelProperty(value="文字能力")
    private String writingAbility;
    /**
     * 专业能力
     */
    @ApiModelProperty(value="专业能力")
    private String professionalCompetence;
    /**
     * 管理能力
     */
    @ApiModelProperty(value="管理能力")
    private String management;
    /**
     * 外语能力
     */
    @ApiModelProperty(value="外语能力")
    private String foreignLanguages;
    /**
     * 计算机能力
     */
    @ApiModelProperty(value="计算机能力")
    private String computerProficiency;
    /**
     * 最低薪资（月薪/年薪）
     */
    @ApiModelProperty(value="最低薪资（月薪/年薪）")
    private BigDecimal minimumLevelincome;
    /**
     * 期望薪资（月薪/年薪）
     */
    @ApiModelProperty(value="期望薪资（月薪/年薪）")
    private BigDecimal expectedLevelincome;
    /**
     * 其他要求
     */
    @ApiModelProperty(value="其他要求")
    private String otherRequest;
    /**
     * 职业规划
     */
    @ApiModelProperty(value="职业规划")
    private String careerPlanning;
    /**
     * 是否有亲戚朋友在本公司
     */
    @ApiModelProperty(value="是否有亲戚朋友在本公司")
    private Integer isRelativeCompany;
    /**
     * 职位跟姓名
     */
    @ApiModelProperty(value="职位跟姓名")
    private String relativePostName;
    /**
     * 是否接受外派
     */
    @ApiModelProperty(value="是否接受外派")
    private Integer isAcceptAssignment;
    /**
     * 可接受外派地点
     */
    @ApiModelProperty(value="可接受外派地点")
    private String acceptAssignmentLocation;
    /**
     * 新建人工号
     */
    @ApiModelProperty(value="新建人工号")
    private String creatorCode;
    /**
     * 新建人
     */
    @ApiModelProperty(value="新建人")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改人工号
     */
    @ApiModelProperty(value="修改人工号")
    private String modifierCode;
    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;
    /**
     * 修改人时间
     */
    @ApiModelProperty(value="修改人时间")
    private LocalDateTime modifierTime;
    /**
     * 资历评价
     */
    @ApiModelProperty(value="资历评价")
    private String qualification;
    /**
     * 目标工作地
     */
    @ApiModelProperty(value="目标工作地")
    private String address;
    /**
     * 组织OID
     */
    @ApiModelProperty(value="组织OID")
    private String orgOid;
    /**
     * 组织id
     */
    @ApiModelProperty(value="组织id")
    private Long orgId;
    /**
     * 应聘岗位名称
     */
    @ApiModelProperty(value="应聘岗位名称")
    private String recruitJobName;
    /**
     * 旧人力OID
     */
    @ApiModelProperty(value="旧人力OID")
    private String oid;
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;
    /**
     * 预留字段6
     */
    @ApiModelProperty(value="预留字段6")
    private String field6;
    /**
     * 预留字段7
     */
    @ApiModelProperty(value="预留字段7")
    private String field7;
    /**
     * 预留字段8
     */
    @ApiModelProperty(value="预留字段8")
    private String field8;
    /**
     * 预留字段9
     */
    @ApiModelProperty(value="预留字段9")
    private String field9;
    /**
     * 预留字段10
     */
    @ApiModelProperty(value="预留字段10")
    private String field10;
    /**
     * 预留字段11
     */
    @ApiModelProperty(value="预留字段11")
    private String field11;
    /**
     * 预留字段12
     */
    @ApiModelProperty(value="预留字段12")
    private String field12;
    /**
     * 预留字段13
     */
    @ApiModelProperty(value="预留字段13")
    private String field13;
    /**
     * 预留字段14
     */
    @ApiModelProperty(value="预留字段14")
    private String field14;
    /**
     * 预留字段15
     */
    @ApiModelProperty(value="预留字段15")
    private String field15;
    /**
     * 预留字段16
     */
    @ApiModelProperty(value="预留字段16")
    private String field16;
    /**
     * 预留字段17
     */
    @ApiModelProperty(value="预留字段17")
    private String field17;
    /**
     * 预留字段18
     */
    @ApiModelProperty(value="预留字段18")
    private String field18;
    /**
     * 预留字段19
     */
    @ApiModelProperty(value="预留字段19")
    private String field19;
    /**
     * 预留字段20
     */
    @ApiModelProperty(value="预留字段20")
    private String field20;
    /**
     * 招聘状态
     */
    @ApiModelProperty(value="招聘状态")
    private String recruitStatus;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    @TableLogic
    private Boolean delFlag;

    /**
     * 简历来源
     */
    @ApiModelProperty(value="简历来源")
    private String resumeSource;
    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;
    /**
     * 候选人头像
     */
    @ApiModelProperty(value="候选人头像")
    private String photo;
    /**
     * 意向岗位
     */
    @ApiModelProperty(value="意向岗位")
    private String intentionPostId;
    /**
     * 联系号码
     */
    @ApiModelProperty(value="联系号码")
    private String contactNumber;
    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNumber;
    /**
     * 应聘组织
     */
    @ApiModelProperty(value="应聘组织")
    private String recruitOrgName;
    /**
     * 目标岗位/职业规划
     */
    @ApiModelProperty(value="目标岗位/职业规划")
    private String careerPlan;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date resumeAccessTime;

    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String certificateNo;

    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date certificateTime;

    /**
     * 招聘状态
     */
    @ApiModelProperty(value="招聘状态")
    private String recruitmentStatus;

}
