package net.herdao.hdp.manpower.mpclient.vo.pipeline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.sys.annotation.DtoField;

/**
 * @ClassName PipelineFormDTO
 * @Description PipelineFormDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 21:17
 * @Version 1.0
 */
@Data
@ApiModel(value = "管线管理-表单信息，用于新增和编辑")
public class PipelineFormVO {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty(value = "管线名称",required = true)
    private String pipelineName;

    @ApiModelProperty(value = "所属集团",required = true)
    private Long groupId;

    @ApiModelProperty("是否停用")
    private Boolean stop;

    @ApiModelProperty(value = "备注")
    private String remark;
}
