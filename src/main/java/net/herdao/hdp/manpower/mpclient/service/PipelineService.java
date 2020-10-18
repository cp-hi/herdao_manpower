package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface PipelineService extends EntityService<Pipeline> {
    IPage<Pipeline> page(Page<Pipeline> page,Pipeline pipeline);

    List<Map> pipelineList(Long groupId);
@Override
    boolean saveOrUpdate(Pipeline pipeline);
}
