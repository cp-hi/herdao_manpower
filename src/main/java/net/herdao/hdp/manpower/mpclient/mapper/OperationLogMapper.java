package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
    @Select(" select * from SYS_OPERATION_LOG where obj_id = #{objId, jdbcType=BIGINT} and entity_class= #{clazz, jdbcType=VARCHAR}")
    List<OperationLog> findByEntity(@Param("objId") Long objId,@Param("clazz") String clazz);
}
