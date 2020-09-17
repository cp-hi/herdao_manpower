package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.Pipeline;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface PipelineService extends IService<Pipeline> {
    IPage<Pipeline> page(Page<Pipeline> page);

    List<Map> pipelineList();

    boolean saveOrUpdate(Pipeline pipeline);
}
