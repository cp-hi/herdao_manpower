package net.herdao.hdp.manpower.mpclient.service.impl;

import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchUpdateVO;
import net.herdao.hdp.manpower.sys.service.CacheService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName PipelineServiceImpl
 * @Description PipelineServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:21
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class PipelineServiceImpl extends EntityServiceImpl<PipelineMapper, Pipeline> implements PipelineService {
    @Autowired
    CacheService cacheService;

    @Override
    public List<Map> pipelineList(Long groupId) {
        return baseMapper.pipelineList(groupId);
    }

    @Override
    public void batchAddVerify(Pipeline pipeline, Object excelObj, StringBuffer buffer) {
        PipelineBatchAddVO excel = (PipelineBatchAddVO) excelObj;
        Group group = cacheService.getGroupByName(excel.getGroupName(), true);
        if (null != group) pipeline.setGroupId(group.getId());
        chkEntityExists(excel.getPipelineName(), group.getId(), false, buffer);
    }

    @Override
    public void batchUpdateVerify(Pipeline pipeline, Object excelObj, StringBuffer buffer) {
        PipelineBatchUpdateVO excel = (PipelineBatchUpdateVO) excelObj;
        Group group = cacheService.getGroupByName(excel.getGroupName(), true);
        if (null != group) pipeline.setGroupId(group.getId());
        Pipeline tmp = chkEntityExists(excel.getPipelineName(), group.getId(), true, buffer);
        if (StringUtils.isBlank(buffer)) {
            pipeline.setIsStop(DtoConverter.string2bool(excel, "isStop"));
            pipeline.setId(tmp.getId());
        }
    }

    @Override
    public Function<Pipeline, String> getNameMapper() {
        return Pipeline::getPipelineName;
    }

    @Override
    public Function<Pipeline, Long> getGroupIdMapper() {
        return Pipeline::getGroupId;
    }

}
