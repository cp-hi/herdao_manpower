package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobGradeMapper extends BaseMapper<JobGrade> {
    IPage page(Page page, @Param("jobGrade") JobGrade jobGrade);


    List<Map> jobGradeList();


    Boolean chkDuplicateJobGradeName(JobGrade jobGrade);

    Boolean chkDuplicateJobGradeCode(JobGrade jobGrade);
}
