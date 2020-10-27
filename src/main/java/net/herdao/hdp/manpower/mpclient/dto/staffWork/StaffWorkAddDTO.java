
package net.herdao.hdp.manpower.mpclient.dto.staffWork;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工工作经历批量导入DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工工作经历批量导入DTO")
public class StaffWorkAddDTO {
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private String staffId;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名",index = 0)
    @ApiModelProperty(value="员工姓名")
    @NotBlank(message = "不能为空")
    private String staffName;

    /**
     * 员工工号
     */
    @ExcelProperty(value = "员工工号",index = 1)
    @ApiModelProperty(value="员工工号")
    @NotBlank(message = "不能为空")
    private String staffCode;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始日期",index = 2)
    @ApiModelProperty(value="开始日期")
    @NotBlank(message = "不能为空")
    private String beginDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束日期",index = 3)
    @ApiModelProperty(value="结束日期")
    @NotBlank(message = "不能为空")
    private String endDate;

    /**
     * 单位名称
     */
    @ExcelProperty(value = "单位名称",index = 4)
    @ApiModelProperty(value="单位名称")
    @NotBlank(message = "不能为空")
    private String companyName;

    /**
     * 中心/项目
     */
    @ExcelProperty(value = "专业部门（中心/项目）",index = 5)
    @ApiModelProperty(value="专业部门（中心/项目）")
    private String orgName;

    /**
     * 岗位
     */
    @ExcelProperty(value = "岗位",index = 6)
    @ApiModelProperty(value="岗位")
    private String post;

    /**
     * 主要业绩
     */
    @ExcelProperty(value = "主要业绩",index = 7)
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;

    /**
     * 承担角色
     */
    @ExcelProperty(value = "承担角色",index = 8)
    @ApiModelProperty(value="承担角色")
    private String role;

    /**
     * 下属人数
     */
    @ExcelProperty(value = "下属人数",index = 9)
    @ApiModelProperty(value="下属人数")
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String subordinates;

}
