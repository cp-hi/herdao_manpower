package net.herdao.hdp.manpower.mpclient.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "员工附件二级分类")
public class StaffFileTypeDTO {

	/*
    @ApiModelProperty(value="主键ID")
    private Long id;

    @ApiModelProperty(value="员工工号")
    private Long staffCode;

    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="附件二级分类")
    private String type;

    @ApiModelProperty(value="附件一级分类")
    private String superType;

    @ApiModelProperty(value="启用状态 名称")
    private String enableStatus;

    @ApiModelProperty(value="启用状态 值")
    private String status;

    @ApiModelProperty(value="修改人")
    private String modifierName;

    @ApiModelProperty(value="修改时间")
    private String modifyTimec;

    @ApiModelProperty(value="创建人")
    private String createName;

    @ApiModelProperty(value="创建时间")
    private String createTime;

    @ApiModelProperty(value="附件数量")
    private String fileCount;

    @ApiModelProperty(value="一级附件分类ID")
    private Long superId;
	*/

	/*
	@ApiModelProperty("字典项id")
	private Integer id;
	
	@ApiModelProperty("所属字典类id")
	private Integer dictId;
	*/
	
	@ApiModelProperty("数据值")
	private String value;
	
	@ApiModelProperty("标签名")
	private String label;
	
	@ApiModelProperty("类型")
	private String type;
	
	@ApiModelProperty("文件IDS逗号分隔")
	private String fileIds;
	
	@ApiModelProperty(value="附件数量")
    private Integer fileCount;
}
