package net.herdao.hdp.manpower.mpclient.dto.section;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Section;

/**
 * @ClassName SectionDTO
 * @Description SectionDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 16:14
 * @Version 1.0
 */
@Data
@ApiModel(value = "板块DTO")
public class SectionDTO extends Section {
    private Group group;
}
