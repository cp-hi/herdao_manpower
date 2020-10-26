package net.herdao.hdp.manpower.mpclient.dto.jobLevel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OKJobLevleSysDetailDTO
 * @Description OKJobLevleSysDetailDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 16:46
 * @Version 1.0
 */

@Data
@ApiModel(value = "职级体系-详情")
public class OKJobLevleSysDetailDTO {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("职级列表")
    private List<ShortJobLevelDTO> shortJobLevelDTOList= new ArrayList<>();
}
