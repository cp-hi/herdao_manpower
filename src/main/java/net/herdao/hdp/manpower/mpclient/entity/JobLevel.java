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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "职级实体类")
public class JobLevel extends BaseEntity<JobLevel> {

    public JobLevel(Long id) {
        this.setId(id);
    }

    @NotBlank(message = "职级名称不能为空")
    @ExcelProperty(value = "职级名称",index = 0)
    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;

    @ApiModelProperty(value = "职级编码")
    private String jobLevelCode;

    @ExcelProperty(value = "描述",index = 3)
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty("职等ID")
    private Long jobGradeId;

    @ApiModelProperty(value = "集团ID")
    private Long groupId;

    @ExcelProperty(value = "排序",index = 2)
    @ApiModelProperty("排序")
    private Integer sortNo;
}
