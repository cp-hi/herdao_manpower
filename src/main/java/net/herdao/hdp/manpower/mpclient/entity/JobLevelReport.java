package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织报表 -- 组织职级统计
 * @author andy
 * @date 2020-09-22 10:13:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@HeadFontStyle
@ColumnWidth(30)
@ApiModel(value = "组织报表")
public class JobLevelReport extends Model<JobLevelReport> {
    /**
     * 组织名称
     */
    @TableId
    @ApiModelProperty(value="组织名称")
    @ExcelProperty(value = "组织名称",index = 0)
    private String orgName;

    /**
     * 组织编码
     */
    @ApiModelProperty(value="组织编码")
    @ExcelProperty(value = "组织编码",index = 1)
    private String orgCode;

    /**
     * 职级名称
     */
    @ApiModelProperty(value="职级名称")
    @ExcelProperty(value = "职级名称",index = 2)
    private String jobLevelName;

    /**
     * 职级code
     */
    @ApiModelProperty(value="职级code")
    @ExcelProperty(value = "职级code",index = 3)
    @ExcelIgnore
    private String jobLevelCode;

    /**
     * 职级人数
     */
    @ApiModelProperty(value="职级人数")
    @ExcelProperty(value = "职级人数",index = 4)
    private String jobLevelNum;
}
