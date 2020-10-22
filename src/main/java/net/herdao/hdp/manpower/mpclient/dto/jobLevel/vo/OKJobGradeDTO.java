package net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName OKJobGradeDTO
 * @Description OKJobGradeDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 16:13
 * @Version 1.0
 */
public class OKJobGradeDTO {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("名称")
    private String jobGradeName;

    private List<OKJobLevelDTO> okJobLevelDTOList;

}
