
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 人才表单编辑DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才表单编辑DTO")
public class RecruitmentUpdateFormDTO extends BaseEntity<RecruitmentUpdateFormDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id",required = true)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    /**
     * "姓名"
     */
    @ApiModelProperty(value="姓名")
    @Length(max = 20,message = "候选人不超过20个字")
    private String talentName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    /**
     * 人才类别
     */
    @ApiModelProperty(value="人才类别")
    private String talentType;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 人才标签
     */
    @ApiModelProperty(value="人才标签")
    private String talentLabel;

    /**
     * 人才ID
     */
    @ApiModelProperty(value="人才ID")
    private Long recruitmentId;

}
