package net.herdao.hdp.manpower.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
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
@ExcelIgnoreUnannotated
@TableName("SYS_OPERATION_LOG")
@ApiModel(value = "岗位实体类")
public class OperationLog {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ExcelProperty(value = {"","操作"})
    @ApiModelProperty(value="操作")
    private String operation;

    @ExcelProperty(value =  {"","操作内容"})
    @ApiModelProperty(value="操作内容")
    private String content;

    @ApiModelProperty(value="操作者ID")
    private Long operatorId;

    @ExcelProperty(value =  {"","操作者名称"})
    @ApiModelProperty(value="操作者名称")
    private String operator;

    @ExcelProperty(value =  {"","操作时间"})
    @ApiModelProperty(value="操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date operatedTime;

    @ApiModelProperty(value="被操作的实体类",hidden = true)
    private String entityClass;

    @ApiModelProperty(value="被操作的实体类主键",hidden = true)
    private Long objId;

    @ApiModelProperty(value="额外信息",hidden = true)
    private String extraKey;

    @ApiModelProperty(value="模块名",hidden = true)
    private String module;

}
