package com.hedao.hdp.mpclient.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PipelineMapper extends BaseMapper<Pipeline> {
    Boolean chkDuplicatePipelineName(Pipeline pipeline);
    Boolean chkDuplicatePipelineCode(Pipeline pipeline);
}
