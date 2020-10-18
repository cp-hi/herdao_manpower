package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.List;

@Mapper
public interface PipelineMapper extends BaseMapper<Pipeline> {
    List<Map> pipelineList(Long groupId);

    IPage page(Page page, @Param("pipeline") Pipeline pipeline);

    Boolean chkDuplicatePipelineName(Pipeline pipeline);

    Boolean chkDuplicatePipelineCode(Pipeline pipeline);

    List<String> getPipelineCodesByGroupId(Long groupId);
}
