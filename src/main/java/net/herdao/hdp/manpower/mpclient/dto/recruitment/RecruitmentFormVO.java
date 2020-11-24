
package net.herdao.hdp.manpower.mpclient.dto.recruitment;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人才表单VO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "人才表单VO")
public class RecruitmentFormVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * "姓名"
     */
    @ApiModelProperty(value="姓名")
    private String talentName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
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
}
