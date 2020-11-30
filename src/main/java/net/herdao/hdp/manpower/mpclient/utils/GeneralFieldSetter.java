package net.herdao.hdp.manpower.mpclient.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author Liu Chang
 * @Date 2020/11/30 10:19 上午
 */
@Component
public class GeneralFieldSetter implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        SysUser sysUser = SysUserUtils.getSysUser();
        this.setFieldValByName("creatorCode", sysUser.getUserId(), metaObject);
        this.setFieldValByName("creatorName", sysUser.getUsername(), metaObject);
        this.setFieldValByName("creatorTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SysUser sysUser = SysUserUtils.getSysUser();
        this.setFieldValByName("modifierCode", sysUser.getUserId(), metaObject);
        this.setFieldValByName("modifierName", sysUser.getUsername(), metaObject);
        this.setFieldValByName("modifierTime", LocalDateTime.now(), metaObject);
    }
}
