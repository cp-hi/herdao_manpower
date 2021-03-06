
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 人才列表DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才列表DTO")
public class RecruitmentDTO   {

    /**
     * id
     */
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * "姓名"
     */
    @ExcelProperty(value = "姓名")
    @ApiModelProperty(value="姓名")
    private String talentName;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    @ApiModelProperty(value="手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    @ApiModelProperty(value="年龄")
    private String age;

    /**
     * 工作年限
     */
    @ExcelProperty(value = "工作年限")
    @ApiModelProperty(value="工作年限")
    private String workAge;

    /**
     * 最高学历
     */
    @ExcelProperty(value = "最高学历")
    @ApiModelProperty(value="最高学历")
    private String highestEducation;

    /**
     * 最近工作单位
     */
    @ExcelProperty(value = "最近工作单位")
    @ApiModelProperty(value="最近工作单位")
    private String finalJobCompany;

    /**
     * 最近从事岗位
     */
    @ExcelProperty(value = "最近从事岗位")
    @ApiModelProperty(value="最近从事岗位")
    private String finalPostName;

    /**
     * 人才类型
     */
    @ExcelProperty(value = "人才类型")
    @ApiModelProperty(value="人才类型")
    private String talentLabel;

    /**
     * 人才标签
     */
    @ExcelProperty(value = "人才标签")
    @ApiModelProperty(value="人才标签")
    private String rcLabel;

    /**
     * 邀请情况
     */
    @ExcelProperty(value = "邀请情况")
    @ApiModelProperty(value="邀请情况" )
    private String inviteSituation;

    /**
     * 最近更新情况
     */
    @ExcelProperty(value = "最近更新情况")
    @ApiModelProperty(value="最近更新情况")
    private String updateDesc;

    /**
     * 组织id
     */
    @ExcelIgnore
    @ApiModelProperty(value="组织id")
    private Long orgId;

    /**
     * 招聘状态
     */
    @ExcelProperty(value = "招聘状态")
    @ApiModelProperty(value="招聘状态")
    private String recruitmentStatus;

    /**
     * 所属组织
     */
    @ExcelProperty(value = "所属组织")
    @ApiModelProperty(value="所属组织")
    private String orgFullName;

}
