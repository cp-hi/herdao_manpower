package net.herdao.hdp.manpower.mpclient.mapper;

import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface PipelineMapper extends EntityMapper<Pipeline> {
    List<Map> pipelineList(Long groupId);
}
