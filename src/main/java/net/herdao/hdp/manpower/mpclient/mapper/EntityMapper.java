package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @ClassName EntityMapper
 * @Description EntityMapper
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 21:32
 * @Version 1.0
 */
public interface EntityMapper<T> extends BaseMapper<T> {

    /**
     * 通过ID获取实体，忽略逻辑删除
     * @param id
     * @return
     */
    T selectIgnoreDel(Long id);

    /**
     * 分页，默认用自身实体可传所有参数，
     * 如有其他参数需自己另写方法
     * @param page
     * @param t
     * @return
     */
    IPage page(IPage page, T t);


}
