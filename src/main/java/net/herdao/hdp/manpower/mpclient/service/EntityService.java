package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Map;
@Validated
public interface EntityService<T> extends IService<T> {
    /**
     * 保存核验
     *
     * @param t
     */
    @Validated
    void saveVerify(@Valid T t);

    /**
     * 导入校验
     *
     * @param t
     */
    @Validated
    void importVerify(@Valid T t);
}
