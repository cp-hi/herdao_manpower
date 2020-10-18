package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

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
    private String sectionCode;
    private String sectionName;
    private Integer groupId;
    private Integer sortNo;
    @TableField("IS_STOP")
    private Boolean stop;

}
