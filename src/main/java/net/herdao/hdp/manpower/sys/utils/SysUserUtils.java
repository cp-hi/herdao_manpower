package net.herdao.hdp.manpower.sys.utils;

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserUtils
 * @Description UserUtils
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/18 16:58
 * @Version 1.0
 */
@Component
public class SysUserUtils {

    private static RemoteUserService remoteUserService;

    @Autowired
    public void setRemoteUserService(RemoteUserService remoteUserService) {
        SysUserUtils.remoteUserService = remoteUserService;
    }

    public static SysUser getSysUser() {
        SysUser sysUser=null;
        if (ObjectUtil.isNotNull(SecurityUtils.getUser())){
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            sysUser = userInfo.getSysUser();
        }
        return sysUser;
    }
}
