package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName EntityMapper
 * @Description EntityMapper
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/22 10:22
 * @Version 1.0
 */
public interface EntityMapper<T> extends BaseMapper<T> {

    /**
     * 获取最大编码
     * @param tabel
     * @param field
     * @return
     */
    @Select("select max(${field} ) from ${tabel}")
    String generateEntityCode(String tabel, String field);

    @Select("select * from ${table} where id = #{id, jdbcType=BIGINT} ")
    T selectIgnoreLogic(Long id,String table);
}
