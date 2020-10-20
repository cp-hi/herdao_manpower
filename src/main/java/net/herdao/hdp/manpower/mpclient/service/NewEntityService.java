package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @ClassName NewEntityService
 * @Description NewEntityService
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/19 18:11
 * @Version 1.0
 */
public interface NewEntityService<T>  extends EntityService<T> {

    IPage page(Page page, T t);

}