package net.herdao.hdp.manpower.mpclient.dto.pipeline;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
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
@Data
@ApiModel(value = "管线DTO")
public class PipelineDTO extends Pipeline {
    private Group group;
}
