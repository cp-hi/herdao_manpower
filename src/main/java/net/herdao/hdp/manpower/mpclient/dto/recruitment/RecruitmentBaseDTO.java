
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

}
