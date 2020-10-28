package net.herdao.hdp.manpower.mpclient.dto.staffTrans;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 员工异动表单 DTO
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工异动表单 DTO")
public class StafftransFormDTO {
    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "员工ID")
    @ApiModelProperty(value="员工ID")
    private String staffId;

    /**
     * 异动时间
     */
    @ExcelProperty(value = "异动时间")
    @ApiModelProperty(value="异动时间")
    private String tranTime;

    /**
     * 异动类型 下拉数据：异动类型
     */
    @ExcelProperty(value = "异动类型")
    @ApiModelProperty(value="异动类型")
    private String tranType;

    /**
     * 异动原因
     */
    @ExcelProperty(value = "异动原因")
    @ApiModelProperty(value="异动原因")
    private String tranReason;


    /**
     * 调往公司名称 异动前所在组织
     */
    @ExcelProperty(value = "异动前所在组织")
    @ApiModelProperty(value="异动前所在组织")
    private String outUnitName;

    /**
     * 调来原公司名称 异动后所在组织
     */
    @ExcelProperty(value = "异动后所在组织")
    @ApiModelProperty(value="异动后所在组织")
    private String comeUnitName;



}
