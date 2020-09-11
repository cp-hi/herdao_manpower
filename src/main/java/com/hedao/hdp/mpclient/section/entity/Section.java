package com.hedao.hdp.mpclient.section.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hedao.hdp.mpclient.entity.BaseEntity;
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
@ApiModel(value = "")
public class Section extends BaseEntity<Section> {
}
