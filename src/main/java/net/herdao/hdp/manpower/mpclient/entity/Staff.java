/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@Data
@TableName("mp_staff")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工表")
public class Staff extends BaseEntity<Staff> {
private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Long userId;
    /**
     * 工号
     */
    @ApiModelProperty(value="工号")
    private String staffCode;
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String staffName;
    /**
     * 人员性质
     */
    @ApiModelProperty(value="人员性质")
    private String personnelNature;
    /**
     * 人员类别
     */
    @ApiModelProperty(value="人员类别")
    private String personnelType;
    /**
     * 人员归属范围
     */
    @ApiModelProperty(value="人员归属范围")
    private String staffScope;
    /**
     * 任职类型
     */
    @ApiModelProperty(value="任职类型")
    private String jobType;
    /**
     * 实际职务
     */
    @ApiModelProperty(value="实际职务")
    private String actualJob;
    /**
     * 实际工作地点
     */
    @ApiModelProperty(value="实际工作地点")
    private String actualCity;
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
     * 国籍
     */
    @ApiModelProperty(value="国籍")
    private String country;
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
    private String politicsStatus;
    /**
     * 最高学历
     */
    @ApiModelProperty(value="最高学历")
    private String educationQua;

    @ApiModelProperty(value="最高学位")
    private String educationDegree;

    @ApiModelProperty(value="毕业院校")
    private String schoolName;

    @ApiModelProperty(value="入学日期")
    private Date beginDate;

    @ApiModelProperty(value="毕业日期")
    private Date endDate;

    @ApiModelProperty(value="专业")
    private String professional;

    @ApiModelProperty(value="学习形式")
    private String learnForm;
    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime workDate;
    /**
     * 参加工作工龄
     */
    @ApiModelProperty(value="参加工作工龄")
    private BigDecimal workSeniority;
    /**
     * 入职本公司日期
     */
    @ApiModelProperty(value="入职本公司日期")
    private LocalDateTime entryTime;
    /**
     * 本公司工龄
     */
    @ApiModelProperty(value="本公司工龄")
    private BigDecimal companySeniority;
    /**
     * 入职三大集团日期
     */
    @ApiModelProperty(value="入职三大集团日期")
    private LocalDate entryThreeGroupsTime;
    /**
     * 三大集团工龄
     */
    @ApiModelProperty(value="三大集团工龄")
    private BigDecimal threeGroupsSeniority;
    /**
     * 目标职位
     */
    @ApiModelProperty(value="目标职位")
    private String goalPosts;
    /**
     * 现住址
     */
    @ApiModelProperty(value="现住址")
    private String currAddress;
    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码")
    private String postCode;
    /**
     * 户口所在省
     */
    @ApiModelProperty(value="户口所在省")
    private String province;
    /**
     * 户口所在城市
     */
    @ApiModelProperty(value="户口所在城市")
    private String city;
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
     * E-mail
     */
    @ApiModelProperty(value="E-mail")
    private String email;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value="身份证号码")
    private String idNumber;
    /**
     * 家庭电话
     */
    @ApiModelProperty(value="家庭电话")
    private String homePhone;
    /**
     * 移动电话
     */
    @ApiModelProperty(value="移动电话")
    private String mobile;
    /**
     * 办公电话
     */
    @ApiModelProperty(value="办公电话")
    private String officePhone;
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
    private String professionalExperience;
    /**
     * 管理经验
     */
    @ApiModelProperty(value="管理经验")
    private String managementExperience;
    /**
     * 房地产行业经验
     */
    @ApiModelProperty(value="房地产行业经验")
    private String realestateExperience;
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
     * 主要社会资源
     */
    @ApiModelProperty(value="主要社会资源")
    private String resources;
    /**
     * 公司内部人脉关系
     */
    @ApiModelProperty(value="公司内部人脉关系")
    private String internalConnections;
    /**
     * 是否集团统招应届生
     */
    @ApiModelProperty(value="是否集团统招应届生")
    private String admissionGraduates;
    /**
     * 加入或迁移公司集体户时间
     */
    @ApiModelProperty(value="加入或迁移公司集体户时间")
    private LocalDate collectiveHouseholdsTime;
    /**
     * 档案挂靠情况
     */
    @ApiModelProperty(value="档案挂靠情况")
    private String archivesSituation;
    /**
     * 是否接受外派
     */
    @ApiModelProperty(value="是否接受外派")
    private Boolean acceptedAssignment;
    /**
     * 可接受外派地点
     */
    @ApiModelProperty(value="可接受外派地点")
    private String assignmentLocations;
    /**
     * 工资开户银行名称
     */
    @ApiModelProperty(value="工资开户银行名称")
    private String bankName;
    /**
     * 工资开户银行帐户所在地
     */
    @ApiModelProperty(value="工资开户银行帐户所在地")
    private String bankAddress;
    /**
     * 工资开户银行帐号
     */
    @ApiModelProperty(value="工资开户银行帐号")
    private String bankAccount;
    /**
     * 公积金银行名称
     */
    @ApiModelProperty(value="公积金银行名称")
    private String fundArchiveBankName;
    /**
     * 公积金银行帐号
     */
    @ApiModelProperty(value="公积金银行帐号")
    private String fundArchiveBankAccount;
    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
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
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String titleCode;
    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date certificationTime;
    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualificationUnit;
    /**
     * 劳动合同签订主体
     */
    @ApiModelProperty(value="劳动合同签订主体")
    private String contractCompany;
    /**
     * 劳动合同签订期限
     */
    @ApiModelProperty(value="劳动合同签订期限")
    private String contractTerm;
    /**
     * 实际工资发放单位
     */
    @ApiModelProperty(value="实际工资发放单位")
    private String paidUnit;
    /**
     * 实际社保购买单位
     */
    @ApiModelProperty(value="实际社保购买单位")
    private String securityUnit;
    /**
     * 实际公积金购买单位
     */
    @ApiModelProperty(value="实际公积金购买单位")
    private String fundUnit;
    /**
     * 社保封存时间
     */
    @ApiModelProperty(value="社保封存时间")
    private LocalDateTime securityArchiveTime;
    /**
     * 公积金封存时间
     */
    @ApiModelProperty(value="公积金封存时间")
    private LocalDateTime fundArchiveTime;
    /**
     * 个人社保号（省）
     */
    @ApiModelProperty(value="个人社保号（省）")
    private String socialSecurityNumberpRovince;
    /**
     * 个人社保号（市）
     */
    @ApiModelProperty(value="个人社保号（市）")
    private String socialSecurityNumberCity;
    /**
     * 工资发放形式
     */
    @ApiModelProperty(value="工资发放形式")
    private String paySalaryType;
    /**
     * 是否交公积金
     */
    @ApiModelProperty(value="是否交公积金")
    private Boolean funding;
    /**
     * 是否交保险
     */
    @ApiModelProperty(value="是否交保险")
    private Boolean insurance;
    /**
     * 是否发放福利
     */
    @ApiModelProperty(value="是否发放福利")
    private Boolean welfare;
    /**
     * 社保类型
     */
    @ApiModelProperty(value="社保类型")
    private String securityType;
    /**
     * 相片地址
     */
    @ApiModelProperty(value="相片地址")
    private String photoAddr;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 非连续工作时间（不计算年假的年限）
     */
    @ApiModelProperty(value="非连续工作时间（不计算年假的年限）")
    private BigDecimal noWorkingSeniority;
    /**
     * 收款银行支行名称
     */
    @ApiModelProperty(value="收款银行支行名称")
    private String dueBankName;
    /**
     * 收款银行支行编码
     */
    @ApiModelProperty(value="收款银行支行编码")
    private String dueBankCode;
    /**
     * 是否日报考核
     */
    @ApiModelProperty(value="是否日报考核")
    private Boolean dailyInspection;
    /**
     * 实际工资支付单位
     */
    @ApiModelProperty(value="实际工资支付单位")
    private String paySalaryUnit;
    /**
     * 实际社保支付单位
     */
    @ApiModelProperty(value="实际社保支付单位")
    private String paySecurityUnit;
    /**
     * 实际公积金支付单位
     */
    @ApiModelProperty(value="实际公积金支付单位")
    private String payFundUnit;
    /**
     * 参保日期
     */
    @ApiModelProperty(value="参保日期")
    private LocalDate insuredTime;
    /**
     * 中间停缴社保年份
     */
    @ApiModelProperty(value="中间停缴社保年份")
    private BigDecimal interruptYear;

    /**
     * 实际工作工龄
     */
    @ApiModelProperty(value="实际工作工龄")
    private BigDecimal realWorkAge;

    @ApiModelProperty(value="转正日期")
    private LocalDate regularTime;

    @ApiModelProperty(value="证件类型")
    private String idType;

    @ApiModelProperty(value="试用期")
    private Long probPeriod;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private String certificateType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String certificateNo;

}
