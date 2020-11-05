package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

    final GroupService groupService;

    @Override
    public List<Map> pipelineList(Long groupId) {
        return baseMapper.pipelineList(groupId);
    }

    @Override
    public void addEntity(Pipeline pipeline, Object excelObj) {
        PipelineBatchAddVO excel = (PipelineBatchAddVO) excelObj;
        Group group = groupService.selectByName(excel.getGroupName(), true);
        chkEntityExists(excel.getPipelineName(), group.getId(), false);
        pipeline.setGroupId(group.getId());
    }

    @Override
    public void updateEntity(Pipeline pipeline, Object excelObj) {
        PipelineBatchUpdateVO excel = (PipelineBatchUpdateVO) excelObj;
        Group group = groupService.selectByName(excel.getGroupName(), true);
        Pipeline tmp = chkEntityExists(excel.getPipelineName(), group.getId(), true);
        pipeline.setGroupId(group.getId());
        pipeline.setId(tmp.getId());
    }

    @Override
    public Function<Pipeline, String> getNameFieldMapper() {
        return Pipeline::getPipelineName;
    }
}
