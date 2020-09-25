package net.herdao.hdp.manpower.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Integer id;
    @ApiModelProperty(value="操作",required = true)
    private String operation;
    @ApiModelProperty(value="操作者ID",required = true)
    private Long operatorId;
    @ApiModelProperty(value="操作者名称",required = true)
    private String operator;
    @ApiModelProperty(value="操作时间",required = true)
    private Date operatedTime;
    @ApiModelProperty(value="被操作的实体类",required = true)
    private String entityClass;
    @ApiModelProperty(value="被操作的实体类主键")
    private Long objId;
}
