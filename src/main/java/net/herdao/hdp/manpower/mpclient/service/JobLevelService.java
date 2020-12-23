package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelListVO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface JobLevelService extends  EntityService<JobLevel> {
	
    List<Map> jobLevelList(Long groupId);

    void validityCheck(Long id, String msg) throws Exception;

	R<IPage<JobLevelListVO>> getPage(Page page, JobLevel jobLevel);
}
