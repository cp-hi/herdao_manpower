package net.herdao.hdp.manpower.mpclient.dto.staff;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工异动列表list DTO
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工异动列表list DTO")
public class StafftransDTO {
    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

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


    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人")
    @ApiModelProperty(value="操作人")
    private String modifiedName;


    /**
     * 操作时间
     */
    @ExcelProperty(value = "操作时间")
    @ApiModelProperty(value="操作时间")
    private String modifiedTime;

}
