package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yangrr
 */
@Data
@ApiModel(value = "集团列表")
@ExcelIgnoreUnannotated
public class GroupListDTO {

    private Long id;

    @ApiModelProperty(value="集团名称")
    @ExcelProperty(value = "集团名称",order = 1)
    private String groupName;

    @ApiModelProperty(value="集团编码")
    @ExcelProperty(value = "集团编码",order = 2)
    private String groupCode;

    @ApiModelProperty(value="对应组织主键")
    @ExcelProperty(value = "对应组织主键",order = 7)
    private Long orgId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "组织名称")
    @ExcelProperty(value = "组织名称",order = 3)
    private String orgName;

    @ApiModelProperty(value = "是否虚拟组织")
    private Boolean isVirtual;

    @ApiModelProperty(value = "组织状态")
    private Boolean isStop;

    @ApiModelProperty(value="排序")
    @ExcelProperty(value = "排序",order = 4)
    private Integer sortNo;

    @ApiModelProperty(value = "在职员工数")
    private String empInService;

    @ApiModelProperty(value = "创建人名称" )
    private String creatorName;

    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "修改人名称" )
    @ExcelProperty(value = "修改人名称",order = 5)
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ExcelProperty(value = "修改时间",order = 6)
    private Date modifiedTime;

    @ApiModelProperty(value="扩展字段1")
    private String field1;

    @ApiModelProperty(value="扩展字段2")
    private String field2;

    @ApiModelProperty(value="扩展字段3")
    private String field3;

    @ApiModelProperty(value="扩展字段4")
    private String field4;

    @ApiModelProperty(value="扩展字段5")
    private String field5;

}
