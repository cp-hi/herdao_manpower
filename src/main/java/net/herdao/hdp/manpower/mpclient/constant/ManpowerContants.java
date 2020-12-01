package net.herdao.hdp.manpower.mpclient.constant;
/**
 * 人事服务常量
 *
 * @author shuling
 * @date 2020-10-19 10:37:21
 */
public interface ManpowerContants {

	 /**
     * 人力服务
     */
    String TEMPLATE_SERVICE = "hdp-manpower-service";
    
    /**
     * 分隔符
     */
    String SEPARATOR = "_";
    
    /**
     * 分隔符（英文）
     */
    String EN_SEPARATOR = ",";
    
    /**
     * 分隔符（中文）
     */
    String CH_SEPARATOR = "，";
    
    /**
     * 分号（中文）
     */
    String CH_SEMICOLON = "；";
    
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
    Integer ENABLE_STATUS = 0;
    //停用
    Integer DISABLE_STATUS = 1;
    
    // excel 导入说明 enum
    enum ImportTypeEnum {
		
		ADD(0, "新增"), UPDATE(1, "编辑");

		private final int importType;
		
		private final String description;

		ImportTypeEnum(int importType, String description) {
			this.importType = importType;
			this.description = description;
		}

		public int getImportType() {
			return importType;
		}

		public String getDescription() {
			return description;
		}
		
		public static String getInstance(Integer importType) {
	        for (ImportTypeEnum em : ImportTypeEnum.values()) {
	            if (em.getImportType() == importType) {
	                return em.getDescription();
	            }
	        }
	        return null;
	    }

	}
    
}
