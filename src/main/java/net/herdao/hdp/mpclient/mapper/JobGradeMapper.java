package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.mpclient.entity.JobGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobGradeMapper extends BaseMapper<JobGrade> {
    List<Map> jobGradeList();

    Boolean chkDuplicateJobGradeName(JobGrade jobGrade);

    Boolean chkDuplicateJobGradeCode(JobGrade jobGrade);
}
