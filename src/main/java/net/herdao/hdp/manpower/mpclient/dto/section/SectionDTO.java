package net.herdao.hdp.manpower.mpclient.dto.section;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class SectionDTO extends Section {
    @TableField(exist = false)
    private Group group;
}
