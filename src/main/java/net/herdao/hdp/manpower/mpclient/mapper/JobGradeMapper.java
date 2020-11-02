package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobGradeMapper extends EntityMapper<JobGrade> {

    List<JobGradeShortVO> jobGradeList(Long groupId);
}
