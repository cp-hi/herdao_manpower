package net.herdao.hdp.manpower.mpclient.dto.staffFamily;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.constant.ExcelPatternMsgContants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description 异常处理
 * @author      andy
 * @date        2020-10-18 10:37:22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StaffFamilyAddErrDTO {

    @ExcelProperty(index =0, value = "错误信息")
    @ColumnWidth(75)
    private String errMsg;

    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    private Long staffId;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名",index =1)
    @TableField(exist = false)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号",index =2)
    @TableField(exist = false)
    @Valid
    @javax.validation.constraints.NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String staffCode;

    @ApiModelProperty(value="家庭成员姓名")
    @ExcelProperty(value = "家庭成员姓名",index =3)
    @Valid
    @NotBlank(message = "不能为空")
    @HeadFontStyle(color = 10)
    private String name;

    @ApiModelProperty(value="关系")
    @ExcelProperty(value = "关系",index =4)
    private String relations;

    @ApiModelProperty(value="年龄")
    @ExcelProperty(value = "年龄",index =5)
    @Pattern(regexp = ExcelPatternMsgContants.NUMBER,message = ExcelPatternMsgContants.NUMBER_MSG)
    private String age;

    @ApiModelProperty(value="职业")
    @ExcelProperty(value = "职业",index =6)
    private String professional;

    @ApiModelProperty(value="工作单位/就读学校")
    @ExcelProperty(value = "工作单位/就读学校",index =7)
    private String workUnit;

    @ApiModelProperty(value="所在地址")
    @ExcelProperty(value = "所在地址",index =8)
    private String address;

}
