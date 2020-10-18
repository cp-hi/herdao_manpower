package net.herdao.hdp.manpower.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @ClassName Operation
 * @Description Operation
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:14
 * @Version 1.0
 */
@Data
@TableName("SYS_OPERATION_LOG")
@ApiModel(value = "岗位实体类")
public class OperationLog {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "操作")
    @ApiModelProperty(value="操作",required = true)
    private String operation;

    @ExcelProperty(value = "操作内容")
    @ApiModelProperty(value="操作内容",required = true)
    private String content;

    @ApiModelProperty(value="操作者ID",required = true)
    @ExcelIgnore
    private Long operatorId;

    @ExcelProperty(value = "操作者名称")
    @ApiModelProperty(value="操作者名称",required = true)
    private String operator;

    @ExcelProperty(value = "操作时间")
    @ApiModelProperty(value="操作时间",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date operatedTime;

    @ApiModelProperty(value="被操作的实体类",required = true)
    @ExcelIgnore
    private String entityClass;

    @ApiModelProperty(value="被操作的实体类主键")
    @ExcelIgnore
    private Long objId;

    @ApiModelProperty(value="额外信息")
    @ExcelIgnore
    private String extraKey;

    @ApiModelProperty(value="模块名")
    @ExcelIgnore
    private String module;

}
