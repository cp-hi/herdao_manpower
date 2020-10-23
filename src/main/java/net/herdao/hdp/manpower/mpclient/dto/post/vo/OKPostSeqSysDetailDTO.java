package net.herdao.hdp.manpower.mpclient.dto.post.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.ShortJobLevelDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OKPostSeqSysDetailDTO
 * @Description OKPostSeqSysDetailDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:19
 * @Version 1.0
 */
@Data
@ApiModel(value = "岗位序列体系-详情")
public class OKPostSeqSysDetailDTO {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("岗位序列列表")
    private List<ShortPostSeqDTO> shortPostSeqDTOList;
}
