package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

public interface EntityService<T> extends IService<T> {
    /**
     * 保存核验
     * @param t
     */
    void saveVerify(T t);

    /**
     * 导入校验
     * @param t
     */
    void importVerify(T t);
}
