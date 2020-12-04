package net.herdao.hdp.manpower.mpclient.utils;

/**
 * @Description 二维码工具
 * @author: yejf
 * @date: 2020/11/30
 */
public class QrCodeUtils {

/**  
 public static void main(String[] args) throws Exception {
	 // 调用方式参考： https://gitee.com/iherus/qrext4j?_from=gitee_search
     //这里设置自定义网站url，跳转人力门户需要带上租户信息
	 
       Integer tenantId = SecurityUtils.getUser().getTenantId();
       String content = "https://baike.baidu.com/item/%E5%97%B7%E5%A4%A7%E5%96%B5/19817560?fr=aladdin&http://www.baidu.com?tenantId="+tenantId;
       QrcodeConfig config = new QrcodeConfig()
    			.setBorderSize(2)
    			.setPadding(10)
    			.setMasterColor("#00BFFF")
    			.setLogoBorderColor("#B0C4DE");

    	String content = "https://baike.baidu.com/item/%E5%97%B7%E5%A4%A7%E5%96%B5/19817560?fr=aladdin";

    	new SimpleQrcodeGenerator(config).setLogo("d:\\aaa.jpg").generate(content).toFile("d:\\zzzz.png");
    	
      } 
     */
    
}
