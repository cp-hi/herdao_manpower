package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import org.apache.commons.lang3.StringUtils;
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
    @Override
    public List<Map> pipelineList(Long groupId) {
        return baseMapper.pipelineList(groupId);
    }

    @Override
    public IPage page(Page page, Pipeline pipeline) {
        return baseMapper.page(page, pipeline);
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
