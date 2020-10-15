package net.herdao.hdp.manpower.mpclient.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.ExcelDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;

/**
 * 员工教育经历列表list
 *
 * @author andy
 * @date 2020-09-23 17:59:39
 */
@Data
@ApiModel(value = "员工教育经历列表vo list")
public class StaffeducationVO {

    /**
     * 学校名称
     */
    @ApiModelProperty(value="毕业院校")
    @ExcelProperty(value = "毕业院校")
    private String schoolName;

    /**
     * 专业
     */
    @ExcelProperty(value = "专业")
    @ApiModelProperty(value="专业")
    private String professional;

    /**
     * 学历与学位
     */
    @ApiModelProperty(value="学位")
    @ExcelProperty(value="学位")
    private String educationDegree;

    /**
     * 学习形式
     */
    @ExcelProperty(value="学习形式")
    @ApiModelProperty(value="学习形式")
    private String learnForm;

    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    @ExcelProperty(value="新建时间")
    private String createdTimeView;


    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    @ExcelProperty(value="最后修改时间")
    private String modifiedTimeView;


    /**
     * 结束时间
     */
    @ApiModelProperty(value="毕业日期")
    @ExcelProperty(value = "毕业日期")
    private String endDateView;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="入学日期")
    @ExcelProperty(value = "入学日期")
    private String beginDateView;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    @ExcelProperty(value = "最后修改人")
    @TableField(exist = false)
    private String modifierName;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    @ExcelProperty(value = "新建用户")
    @TableField(exist = false)
    private String createName;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    @TableField(exist = false)
    private String staffName;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    @TableField(exist = false)
    private String staffCode;

    /**
     * 学历
     */
    @ApiModelProperty(value="学历")
    @ExcelProperty(value = "学历")
    private String educationQua;


}
