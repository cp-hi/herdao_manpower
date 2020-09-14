package net.hedao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.hedao.hdp.mpclient.entity.Pipeline;

import java.util.List;
import java.util.Map;

public interface PipelineService extends IService<Pipeline> {
    List<Map> pipelineList();
    void addOrUpdate(Pipeline pipeline)throws Exception;
}
