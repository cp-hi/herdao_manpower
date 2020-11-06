package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.Pipeline;

import java.util.List;
import java.util.Map;

public interface PipelineService extends EntityService<Pipeline> {

    List<Map> pipelineList(Long groupId);
}
