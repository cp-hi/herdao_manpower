package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobLevelMapper extends EntityMapper<JobLevel> {

    List<Map> jobLevelList(Long groupId);

    Boolean chkCodeAndName(JobLevel jobLevel);

    @Override
    String getLastEntityCode(JobLevel jobLevel);

    Boolean checkDuplicateNameInJobGrade(JobLevel jobLevel);

}
