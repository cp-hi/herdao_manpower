package net.herdao.hdp.manpower.mpclient.dto.staffEdu;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工教育经历列表list
 *
 * @author andy
 * @date 2020-09-23 17:59:39
 */
@Data
@ApiModel(value = "员工教育经历列表list")
public class StaffeducationListDto {

    /**
     * 学校名称
     */
    @ApiModelProperty(value="学校名称")
    @ExcelProperty(value = "学校名称")
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
    @ApiModelProperty(value="学历与学位")
    @ExcelProperty(value="学历与学位")
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
    private String createdTime;


    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    @ExcelProperty(value="最后修改时间")
    private String modifiedTime;


    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelProperty(value = "结束时间")
    private String endDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="开始时间")
    @ExcelProperty(value = "开始时间")
    private String beginDate;

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

}
