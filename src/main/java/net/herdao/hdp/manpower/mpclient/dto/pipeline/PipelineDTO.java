package net.herdao.hdp.manpower.mpclient.dto.pipeline;

import com.baomidou.mybatisplus.annotation.TableField;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;

/**
 * @ClassName PipelineDTO
 * @Description PipelineDTO
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/17 16:14
 * @Version 1.0
 */
public class PipelineDTO extends Pipeline {
    @TableField(exist = false)
    private Group group;
}
