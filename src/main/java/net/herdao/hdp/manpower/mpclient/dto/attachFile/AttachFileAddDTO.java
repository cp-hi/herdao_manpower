package net.herdao.hdp.manpower.mpclient.dto.attachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增通用附件")
public class AttachFileAddDTO {

	/**
     * 	文件上传ID (上传ftp后成功返回的ID)
     */
    //@ApiModelProperty(value="文件上传ID (上传ftp后成功返回的ID)")
    //private String fileId;
    
    @ApiModelProperty(value="文件信息")
    private HerDaoFileDTO fileMsg;
    
    /**
     * 	文件所属字典类型
     */
    @ApiModelProperty(value="文件所属字典类型")
    private String moduleType;
    
    /**
     * 	文件所属字典value值
     */
    @ApiModelProperty(value="文件所属字典value值")
    private String moduleValue;
    
    /**
     * 	业务表ID(例如：人才表的主键ID)
     */
    @ApiModelProperty(value="业务表ID(例如：人才表的主键ID)")
    private String bizId;
	
    
    
    
}
