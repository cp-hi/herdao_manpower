package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教育经历表单 DTO
 * @author andy
 */
@Data
@ApiModel(value = "教育经历表单 DTO")
public class StaffEducationFormDTO {
    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value="人员外键")
    private Long staffId;

    @ApiModelProperty(value="入学日期")
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


}
