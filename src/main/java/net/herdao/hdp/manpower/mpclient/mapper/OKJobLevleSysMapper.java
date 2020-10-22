package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.OKJobLevleSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.OKJobLevleSys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName OKJobLevleSysMapper
 * @Description OKJobLevleSysMapper
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 9:02
 * @Version 1.0
 */
@Mapper
public interface OKJobLevleSysMapper extends BaseMapper<OKJobLevleSys> {
    @Select("select * from  mp_ok_job_level_sys")
    List<OKJobLevleSys> findAll();

    OKJobLevleSysDTO findDetail(Long id);
}
