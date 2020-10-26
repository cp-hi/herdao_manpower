package net.herdao.hdp.manpower.mpclient.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
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
public class GroupListDTO {

    private Long id;

    @ApiModelProperty(value="集团名称")
    private String groupName;

    @ApiModelProperty(value="集团编码")
    private String groupCode;

    @ApiModelProperty(value="对应组织主键")
    private Long orgId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "是否虚拟组织")
    private Boolean isVirtual;

    @ApiModelProperty(value = "组织状态")
    private Boolean isStop;

    @ApiModelProperty(value="排序")
    private Integer sortNo;

    @ApiModelProperty(value = "在职员工数")
    private String empInService;

    @ApiModelProperty(value = "创建人名称" )
    private String creatorName;

    @ExcelIgnore
    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "修改人名称" )
    private String modifierName;

    @ExcelIgnore
    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date modifiedTime;

}
