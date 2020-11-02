
package net.herdao.hdp.manpower.mpclient.dto.staffContract;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

/**
 * 员工合同签订列表list DTO
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Data
@ApiModel(value = "员工合同签订列表list DTO")
public class StaffcontractDTO {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    @ExcelIgnore
    @ApiModelProperty(value="员工外键")
    private Long staffId;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    private String staffCode;

    /**
     * 劳动合同起始日期
     */
    @ApiModelProperty(value="劳动合同起始日期")
    @ExcelProperty(value = "劳动合同起始日期")
    private String startDate;

    /**
     * 劳动合同结束日期
     */
    @ApiModelProperty(value="劳动合同结束日期")
    @ExcelProperty(value = "劳动合同结束日期")
    private String endDate;

    /**
     * 合同签订主体
     */
    @ApiModelProperty(value="合同签订主体")
    @ExcelProperty(value = "合同签订主体")
    private String company;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    @ExcelProperty(value = "合同编号")
    private String contractId;

    /**
     * 合同是否生效
     */
    @ApiModelProperty(value="合同是否生效")
    @ExcelProperty(value = "合同是否生效")
    private String contractStatus;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    @ExcelProperty(value = "合同期限类型")
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限")
    @ExcelProperty(value = "合同期限")
    private String contractPeriod;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期")
    private String probationMonth;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    @ExcelIgnore
    private String updateDesc;
}
