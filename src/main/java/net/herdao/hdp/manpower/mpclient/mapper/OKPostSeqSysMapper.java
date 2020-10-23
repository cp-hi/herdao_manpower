package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.OKPostSeqSysDTO;
import net.herdao.hdp.manpower.mpclient.entity.OKPostSeqSys;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OKPostSeqSysMapper
 * @Description OKPostSeqSysMapper
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/23 11:15
 * @Version 1.0
 */
@Mapper
public interface OKPostSeqSysMapper extends BaseMapper<OKPostSeqSys> {
    OKPostSeqSysDTO findDetail(Long id);
}
