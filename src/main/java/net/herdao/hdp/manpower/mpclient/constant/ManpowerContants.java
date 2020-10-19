package net.herdao.hdp.manpower.mpclient.constant;
/**
 * 人事服务常量
 *
 * @author shuling
 * @date 2020-10-19 10:37:21
 */
public interface ManpowerContants {

    /**
     * 分隔符
     */
    String SEPARATOR = "_";
    
    /**
     * 分隔符(英文)
     */
    String EN_SEPARATOR = ",";
    
    /**
     * 分隔符(中文)
     */
    String CH_SEPARATOR = "，";
    
    /**
     * 操作状态
     */
    String SAVE_SUCCESS = "保存成功！";
    String UPDATE_SUCCESS = "修改成功！";
    String DELETE_SUCCESS = "删除成功！";
    String ENABLE_SUCCESS = "启用成功！";
    String DISABLE_SUCCESS = "停用成功！";
    
    // 使用状态
    Integer USE_STATUS = 0;
    // 删除状态
    Integer DELETE_STATUS = 1;
    // 启用
    Integer ENABLE_STATUS = 2;
    // 禁用
    Integer DISABLE_STATUS = 3;
    
    /**
     * 人力服务
     */
    String TEMPLATE_SERVICE = "hdp-manpower-service";

}
