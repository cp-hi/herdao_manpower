package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobGradeMapper extends EntityMapper<JobGrade> {

    List<JobGradeShortVO> jobGradeList(Long groupId);

    @Select("select * from mp_job_grade g where  g.id =" +
            "(select job_grade_id from mp_job_level l WHERE l.id=#{jobLevelId,jdbcType=BIGINT})")
    JobGrade getByJobLevelId(Long jobLevelId);
}
