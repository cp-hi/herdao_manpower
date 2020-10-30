package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName JobLevel
 * @Description JobLevel
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 18:55
 * @Version 1.0
 */

@Data
@TableName("MP_JOB_LEVEL")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职级")
public class JobLevel extends BaseEntity<JobLevel> {

    @NotBlank(message = "职级名称不能为空")
    @ExcelProperty(value = "职级名称")
    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;

    @ApiModelProperty(value = "职级编码", hidden = true)
    private String jobLevelCode;

    @ExcelProperty(value = "描述")
    @ApiModelProperty(value = "描述", hidden = true)
    private String description;

    @ApiModelProperty(value = "职等ID", required = true)
    private Long jobGradeId;

    @ApiModelProperty(value = "集团ID", required = true)
    private Long groupId;

    @ExcelProperty(value = "排序")
    @ApiModelProperty(value = "排序", hidden = true)
    private Integer sortNo;
}
