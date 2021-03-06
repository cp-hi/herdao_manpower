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
@ApiModel(value = "公司列表")
@ExcelIgnoreUnannotated
public class CompanyListDTO {

    private Long id;

    @ApiModelProperty(value="公司名称")
    @ExcelProperty(value = "公司名称",order = 1)
    private String companyName;

    @ApiModelProperty(value="公司编码")
    @ExcelProperty(value = "公司编码",order = 2)
    private String companyCode;

    @ApiModelProperty(value="所属集团")
    private String groupName;

    @ApiModelProperty(value="所属集团编码")
    private String groupOrgCode;

    @ApiModelProperty(value="对应组织主键")
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
    private Long sort;

    @ApiModelProperty(value="所属城市ID")
    private Long cityId;
    
    @ApiModelProperty(value="城市名称")
    @ExcelProperty(value = "城市名称",order = 7)
    private String cityName;

    @ApiModelProperty(value="目标财务系统")
    private String targetSys;
    
    @ApiModelProperty(value="目标财务系统名称")
    @ExcelProperty(value = "目标财务系统名称",order = 4)
    private String targetSysName;

    @ApiModelProperty(value="保险标准")
    private String securityStandard;

    @ApiModelProperty(value="保险标准名称")
    @ExcelProperty(value = "保险标准名称",order = 5)
    private String securityStandardName;

    @ApiModelProperty(value="公积金标准")
    private String cityFund;

    @ApiModelProperty(value="公积金标准名称")
    @ExcelProperty(value = "公积金标准名称",order = 6)
    private String cityFundName;

    @ApiModelProperty(value = "在职员工数")
    private String empInService;

    @ApiModelProperty(value = "修改人名称" )
    @ExcelProperty(value = "修改人名称",order = 8)
    private String modifierName;

    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ExcelProperty(value = "修改时间",order = 9)
    private Date modifiedTime;

    @ApiModelProperty(value = "创建人名称" )
    private String creatorName;

    @ApiModelProperty(value = "创建时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdTime;

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
