package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 教育经历list DTO
 * @author andy
 */
@Data
@ApiModel(value = "教育经历list DTO")
public class StaffEducationDTO {
    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    private String staffCode;

    @ApiModelProperty(value="人员外键")
    @ExcelIgnore
    private String staffId;

    @ApiModelProperty(value="入学日期")
    @ExcelProperty(value = "入学日期")
    private String beginDate;

    @ApiModelProperty(value="毕业日期")
    @ExcelProperty(value = "毕业日期")
    private String endDate;

    @ApiModelProperty(value="毕业院校")
    @ExcelProperty(value = "毕业院校")
    private String schoolName;

    @ApiModelProperty(value="专业")
    @ExcelProperty(value = "专业")
    private String professional;

    @ApiModelProperty(value="学位")
    @ExcelProperty(value = "学位")
    private String educationDegree;

    @ApiModelProperty(value="学历")
    @ExcelProperty(value = "学历")
    private String educationQua;

    @ApiModelProperty(value="学习形式")
    @ExcelProperty(value = "学习形式")
    private String learnForm;

    @ApiModelProperty(value="操作人")
    @TableField(exist = false)
    @ExcelProperty(value = "操作人")
    private String modifierName;

    @ApiModelProperty(value="操作时间")
    @ExcelProperty(value = "操作时间")
    private String modifiedTime;
}
