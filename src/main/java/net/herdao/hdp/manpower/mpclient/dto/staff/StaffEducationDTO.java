package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author yangrr
 */
@Data
@ApiModel(value = "教育经历")
public class StaffEducationDTO {

    private Long id;

    @ApiModelProperty(value="人员外键")
    private String staffId;

    @ApiModelProperty(value="起止时间")
    private String period;

    @ApiModelProperty(value="结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value="学校名称")
    private String schoolName;

    @ApiModelProperty(value="专业")
    private String professional;

    @ApiModelProperty(value="学历与学位")
    private String educationDegree;

    @ApiModelProperty(value="学习形式")
    private String learnForm;

    @ApiModelProperty(value="最后修改人")
    @TableField(exist = false)
    private String modifierName;

    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
}
