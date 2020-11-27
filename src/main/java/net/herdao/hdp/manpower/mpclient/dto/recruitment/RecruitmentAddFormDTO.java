
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 人才表单新增DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才表单新增DTO")
public class RecruitmentAddFormDTO extends BaseEntity<RecruitmentAddFormDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * "姓名"
     */
    @ApiModelProperty(value="姓名",required = true)
    @Length(max = 20,message = "候选人不超过20个字")
    @NotBlank(message = "候选人姓名不能为空")
    private String talentName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码",required = true)
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;


    /**
     * 人才类别
     */
    @ApiModelProperty(value="人才类别")
    private String talentType;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="个人邮箱")
    private String email;

    /**
     * 最近工作单位名称
     */
    @ApiModelProperty(value="最近工作单位")
    private String finalJobCompany;

    /**
     * 最近从事岗位
     */
    @ApiModelProperty(value="最近从事岗位")
    private String finalPostName;

    /**
     * 参加工作日期
     */
    @ApiModelProperty(value="参加工作日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workdate;
}
