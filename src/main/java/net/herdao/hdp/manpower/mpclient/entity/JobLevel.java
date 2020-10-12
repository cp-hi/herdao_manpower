package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
@ApiModel(value = "职级实体类")
public class JobLevel extends BaseEntity<JobLevel> {
    @NotBlank(message = "职级名称不能为空")
    @ExcelProperty("职级名称")
    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;
    //    @NotBlank(message = "职级编码不能为空")
//    @ExcelProperty("职级编码")
    @ApiModelProperty(value = "职级编码")
    private String jobLevelCode;
    @ExcelProperty("描述")
    @ApiModelProperty(value = "描述")
    private String description;
    @ExcelProperty("职等ID")
    @ApiModelProperty(value = "职等ID")
    private Long jobGradeId;
    @ExcelProperty("集团ID")
    @ApiModelProperty(value = "集团ID")
    private Long groupId;
}
