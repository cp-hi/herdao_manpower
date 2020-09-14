package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.entity.Section;

import java.util.List;
import java.util.Map;

public interface PipelineService extends IService<Pipeline> {
    List<Map> pipelineList();
    void addOrUpdate(Pipeline pipeline)throws Exception;
}
