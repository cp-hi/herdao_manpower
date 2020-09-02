package net.herdao.hdp.hdpclientdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.hdpclientdemo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * demo 表
 *
 * @author liang
 * @date 2020-08-06 15:18:40
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

}
