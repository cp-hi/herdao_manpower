package net.herdao.hdp.manpower.mpclient.service.impl;

import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysPipe;
import net.herdao.hdp.admin.api.entity.SysStationSeq;
import net.herdao.hdp.admin.api.feign.RemotePipeService;
import net.herdao.hdp.common.core.constant.CommonConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import net.herdao.hdp.manpower.sys.cache.GroupCache;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
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

    private final RemotePipeService remotePipeService;
    @Override
    public List<Map> pipelineList(Long groupId) {
        return baseMapper.pipelineList(groupId);
    }

    @Override
    public void batchAddVerify(Pipeline pipeline, Object excelObj, StringBuffer buffer) {
        PipelineBatchAddVO excel = (PipelineBatchAddVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) pipeline.setGroupId(group.getId());
        chkEntityExists(excel.getPipelineName(), group.getId(), false, buffer);
    }

    @Override
    public void batchUpdateVerify(Pipeline pipeline, Object excelObj, StringBuffer buffer) {
        PipelineBatchUpdateVO excel = (PipelineBatchUpdateVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
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

    @Override
    public void saveOrUpdateSync(Pipeline pipeline) {
        SysPipe pipe = converterValue(pipeline);
        R<Long> r = remotePipeService.saveOrUpdate(pipe);
        Long aLong = checkData(r);
        pipeline.setId(aLong);
    }
    @Override
    public Boolean deleteSync(Serializable id) {
        R<Boolean> r = remotePipeService.delete(id);
        return checkData(r);
    }

    @Override
    public Boolean stop(Serializable id, Boolean stop) {
        R<Boolean> r = remotePipeService.stop(id, stop);
        return checkData(r);
    }

    @Override
    public void saveOrUpdateBatchSync(List<Pipeline> collection) {
        List<SysPipe> list = new ArrayList<>();
        collection.forEach(pipeline->{
            list.add(converterValue(pipeline));
        });
        R<List<Long>> r = remotePipeService.saveOrUpdateBatch(list);
        List<Long> longs = checkData(r);
        for(int i=0;i<longs.size();i++){
            collection.get(i).setId(longs.get(i));
        }
    }
    private SysPipe converterValue(Pipeline pipeline){
        SysPipe pipe = new SysPipe();
        pipe.setId(pipeline.getId());
        pipe.setName(pipeline.getPipelineName());
        if(pipeline.getIsStop()!=null&&!pipeline.getIsStop()){
        pipe.setUseFlag(CommonConstants.USE_FLAG_START);
        }  else {
            pipe.setUseFlag(CommonConstants.USE_FLAG_STOP);}
        pipe.setGroupId(pipeline.getGroupId());
        pipe.setSort(pipeline.getSortNo());
        return pipe;
    }

   }
