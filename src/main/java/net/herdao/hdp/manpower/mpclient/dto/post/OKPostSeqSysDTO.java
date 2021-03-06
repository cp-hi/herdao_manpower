package net.herdao.hdp.manpower.mpclient.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OKPostSeqSysDTO
 * @Description OKPostSeqSysDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:17
 * @Version 1.0
 */

@Data
@ApiModel(value = "一键创岗位序列体系-列表")
public class OKPostSeqSysDTO {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("岗位序列列表")
    private List<OKPostSeqDTO> okPostSeqDTOList;
}
