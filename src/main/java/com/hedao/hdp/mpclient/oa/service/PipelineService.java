package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.entity.Section;

public interface PipelineService extends IService<Pipeline> {
    void addOrUpdate(Pipeline pipeline)throws Exception;
}
