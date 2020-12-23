package net.herdao.hdp.manpower.mpclient.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;

import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

@Mapper
public interface JobLevelMapper extends EntityMapper<JobLevel> {
	
	IPage page(IPage page, @Param("t")JobLevel t);

    List<Map> jobLevelList(Long groupId);

    @Override
    String getLastEntityCode(JobLevel jobLevel);

    Boolean checkDuplicateNameInJobGrade(JobLevel jobLevel);

}
