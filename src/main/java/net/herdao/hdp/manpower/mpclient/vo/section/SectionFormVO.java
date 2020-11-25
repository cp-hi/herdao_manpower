package net.herdao.hdp.manpower.mpclient.vo.section;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SectionFormDTO
 * @Description SectionFormDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 20:37
 * @Version 1.0
 */
@Data
@ApiModel(value = "板块管理-表单信息，用于新增和编辑")
public class SectionFormVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty(value = "板块名称",required = true)
    private String sectionName;

    @ApiModelProperty(value = "所属集团",required = true)
    private Long groupId;

    @ApiModelProperty("是否停用")
    private Boolean isStop;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty(value = "备注")
    private String remark;
}
