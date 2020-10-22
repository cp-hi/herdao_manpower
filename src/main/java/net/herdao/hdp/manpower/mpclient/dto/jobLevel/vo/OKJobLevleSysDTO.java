package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OKJobLevleSysDTO
 * @Description OKJobLevleSysDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 15:23
 * @Version 1.0
 */
@Data
@ApiModel(value = "一键创建职级-列表")
public class OKJobLevleSysDTO {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("职等列表")
    private List<OKJobGradeDTO> okJobGradeDTOList;
}
