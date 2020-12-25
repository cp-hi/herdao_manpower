
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才简历-个人基本情况
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才简历-个人基本情况-详情DTO")
public class RecruitmentPersonDTO {

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
     * 移动电话
     */
    @ApiModelProperty(value="手机号码")
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
     * 人才类别
     */
    @ApiModelProperty(value="人才类别")
    private String talentType;

    /**
     * 归属组织
     */
    @ApiModelProperty(value="归属组织")
    private String orgName;

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
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private Long birthday;

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
     * 政治面貌
     */
    @ApiModelProperty(value="政治面貌")
    private String politicalLandscape;

    /**
     * 生育状况
     */
    @ApiModelProperty(value="生育状况")
    private String fertility;

    /**
     * 紧急联系人
     */
    @ApiModelProperty(value="紧急联系人")
    private String emergencyContacts;

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
     * 紧急联系电话
     */
    @ApiModelProperty(value="紧急联系电话")
    private String emergencyPhone;


    /**
     * 出生日期-LocalDateTime
     */
    @ApiModelProperty(value="出生日期-LocalDateTime",hidden = true)
    private LocalDateTime birthdayLocal;


}
