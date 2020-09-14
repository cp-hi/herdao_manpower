package net.hedao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.hedao.hdp.mpclient.entity.Pipeline;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface PipelineMapper extends BaseMapper<Pipeline> {
    List<Map> pipelineList();
    Boolean chkDuplicatePipelineName(Pipeline pipeline);
    Boolean chkDuplicatePipelineCode(Pipeline pipeline);
}
