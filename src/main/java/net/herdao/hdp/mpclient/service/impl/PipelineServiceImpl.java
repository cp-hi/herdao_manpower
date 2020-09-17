package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Pipeline;
import net.herdao.hdp.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.mpclient.service.PipelineService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PipelineServiceImpl
 * @Description PipelineServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:21
 * @Version 1.0
 */
@Service
public class PipelineServiceImpl extends ServiceImpl<PipelineMapper, Pipeline> implements PipelineService {

    public List<Map> pipelineList() {
        return baseMapper.pipelineList();
    }

    public IPage<Pipeline> page(Page<Pipeline> page) {
        IPage<Pipeline> p =baseMapper.query(page);
        return p;
    }

    @Override
    public boolean saveOrUpdate(Pipeline pipeline) {
        if (baseMapper.chkDuplicatePipelineCode(pipeline))
            throw new RuntimeException("管线编码重复了");
        if (baseMapper.chkDuplicatePipelineName(pipeline))
            throw new RuntimeException("管线名称重复了");
        return super.saveOrUpdate(pipeline);
    }
}
