package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface PipelineMapper extends BaseMapper<Pipeline> {
    List<Map> pipelineList();
    Boolean chkDuplicatePipelineName(Pipeline pipeline);
    Boolean chkDuplicatePipelineCode(Pipeline pipeline);
}
