package net.herdao.hdp.manpower.mpclient.dto.familyStatus;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.vo.ExcelMsg;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;

/**
 * 家庭情况分页Dto
 */
@Data
@ApiModel(value = "家庭情况分页Dto")
public class FamilyStatusListDTO extends Familystatus implements ExcelMsg {
    @ExcelProperty(value = "错误信息")
    @ColumnWidth(100)
    private String errMsg;

    /**
     *
     */
    @TableId
    @ExcelIgnore
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    @TableField(exist = false)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    @TableField(exist = false)
    private String staffCode;

    /**
     * 姓名
     */
    @ExcelProperty(value = "家庭成员姓名")
    @ApiModelProperty(value="家庭成员姓名")
    private String name;

    /**
     * 关系
     */
    @ApiModelProperty(value="关系")
    @ExcelProperty(value = "关系")
    private String relations;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    @ExcelProperty(value = "职业")
    private String professional;

    /**
     * 工作单位/就读学校
     */
    @ApiModelProperty(value="工作单位/就读学校")
    @ExcelProperty(value = "工作单位/就读学校")
    private String workUnit;

    /**
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    @ExcelProperty(value="所在地址")
    private String address;


    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    private String updateDesc;

}
