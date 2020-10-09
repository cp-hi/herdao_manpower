package net.herdao.hdp.manpower.mpclient.utils;

import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  当前用户工具类
 * @author andy
 */
@AllArgsConstructor
@Component
public class UserUtils{
    private  RemoteUserService remoteUserService;
    private  static RemoteUserService userService;

    @PostConstruct
    public void init() {
        userService = remoteUserService;
    }

    /**
     * 获取当前用户
     * @return
     */
    public static SysUser getUser(){
       UserInfo userInfo = userService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
       SysUser sysUser = userInfo.getSysUser();
       return sysUser;
    }

    /**
     * 获取当前用户姓名
     * @return
     */
    public static String getUsername(){
        UserInfo userInfo = userService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        SysUser sysUser = userInfo.getSysUser();
        String username = sysUser.getUsername();
        return username;
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Integer getUserId(){
        UserInfo userInfo = userService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        SysUser sysUser = userInfo.getSysUser();
        Integer userId = sysUser.getUserId().intValue();
        return userId;
    }

}