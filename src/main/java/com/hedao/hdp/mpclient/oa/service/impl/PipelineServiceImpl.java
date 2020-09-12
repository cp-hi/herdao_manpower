package com.hedao.hdp.mpclient.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.mapper.PipelineMapper;
import com.hedao.hdp.mpclient.oa.service.PipelineService;
import org.springframework.stereotype.Service;

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

    public void addOrUpdate(Pipeline pipeline) throws Exception {
        if (baseMapper.chkDuplicatePipelineName(pipeline))
            throw new Exception("岗位编码重复了");
        if (baseMapper.chkDuplicatePipelineName(pipeline))
            throw new Exception("岗位名称重复了");
        if (null == pipeline.getId()) {
            this.save(pipeline);
        } else {
            this.updateById(pipeline);
        }
    }
}
