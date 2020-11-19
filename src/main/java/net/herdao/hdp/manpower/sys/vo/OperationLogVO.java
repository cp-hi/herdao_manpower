package net.herdao.hdp.manpower.sys.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

import java.util.Date;

/**
 * @ClassName OperationLogVO
 * @Description OperationLogVO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/11 11:59
 * @Version 1.0
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "操作记录")
public class OperationLogVO {

    @ExcelProperty(value = {"", "操作时间"})
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date operatedTime;

    @ExcelProperty(value = {"", "IP地址"})
    @ApiModelProperty(value = "IP地址")
    private String ipAddr;

    @ExcelProperty(value = {"", "mac地址"})
    @ApiModelProperty(value = "mac地址")
    private String mac;

    @ExcelProperty(value = {"", "操作人"})
    @ApiModelProperty(value = "操作人")
//    @DtoField(objField = {"operator", "operatorCode"}, mapFix = "{1:\"(\",3:\")\"}", symbol = "")
    private String operator;

    @ExcelProperty(value = {"", "操作内容"})
    @ApiModelProperty(value = "操作内容")
    @DtoField(objField = {"operator", "content"}, symbol = "")
    private String content;
}
