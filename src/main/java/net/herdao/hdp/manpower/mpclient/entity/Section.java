package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

/**
 * @ClassName Section
 * @Description Section
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 19:41
 * @Version 1.0
 */
@Data
@TableName("MP_Section")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "板块")
public class Section extends BaseEntity<Section> {
    @ApiModelProperty(value = "板块编码",hidden = true)
    private String sectionCode;
    @ApiModelProperty(value = "板块名称")
    private String sectionName;
    @ApiModelProperty(value = "集团ID",required = true)
    private Long groupId;
    @ApiModelProperty(value = "排序",hidden = true)
    private Integer sortNo;

    @ApiModelProperty(value = "是否停用",hidden = true)
//    @TableField("IS_STOP")
    private Boolean isStop;

    @ApiModelProperty(value = "备注")
    private String remark;
}
