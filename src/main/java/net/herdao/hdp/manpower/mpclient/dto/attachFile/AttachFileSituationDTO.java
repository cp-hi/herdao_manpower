
package net.herdao.hdp.manpower.mpclient.dto.attachFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 个人简历-简历附件DTO
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "个人简历-简历附件DTO")
public class AttachFileSituationDTO {

    /**
     * 文件类型
     */
    @ApiModelProperty(value="文件类型")
    private String fileType;

    /**
     * 文件总数量
     */
    @ApiModelProperty(value="文件总数量")
    private String fileCount;

    /**
     * 文件总大小
     */
    @ApiModelProperty(value="文件总大小")
    private String fileTotalSize;


}
