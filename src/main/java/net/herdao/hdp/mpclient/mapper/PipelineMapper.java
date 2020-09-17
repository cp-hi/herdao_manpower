package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.mpclient.entity.Pipeline;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface PipelineMapper extends BaseMapper<Pipeline> {
    List<Map> pipelineList();

    IPage<Pipeline> query(Page<Pipeline> page);

    Boolean chkDuplicatePipelineName(Pipeline pipeline);

    Boolean chkDuplicatePipelineCode(Pipeline pipeline);

    List<String> getPipelineCodesByGroupId(Long groupId);
}
