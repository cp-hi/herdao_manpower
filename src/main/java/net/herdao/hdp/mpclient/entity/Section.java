package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Section extends Model<Section> {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String sectionCode;
    private String sectionName;
    private String groupOid;
    private Integer groupId;
    private Integer sortNo;
    @TableField("IS_STOP")
    private Boolean stop;
    private Integer tenantId;
}
