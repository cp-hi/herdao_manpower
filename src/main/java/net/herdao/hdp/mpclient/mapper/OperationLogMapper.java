package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.sys.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
    @Select(" select * from SYS_OPERATION_LOG where obj_id = #{objId, jdbcType=LONG} and entity_class= #{clazz, jdbcType=VARCHAR}")
    List<OperationLog> findByEntity(Long objId, String clazz);
}
