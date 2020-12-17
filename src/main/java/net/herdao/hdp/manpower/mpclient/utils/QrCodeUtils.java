package net.herdao.hdp.manpower.mpclient.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.iherus.codegen.qrcode.QrcodeGenerator;
import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description 二维码工具
 * @author: yejf
 * @date: 2020/11/30
 */
@Slf4j
public class QrCodeUtils {

	/**
	 * 生成二维码的Base64编码
	 * @param content 生成内容
	 * @return
	 */
	public static String createBase64QrCode(String content) {
		String result="";
		Integer tenantId = SecurityUtils.getUser().getTenantId();
		if (ObjectUtil.isNotNull(tenantId)){
			//填写手机端极速入职接口
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try {
				QrcodeGenerator generator = new SimpleQrcodeGenerator();
				BufferedImage image = generator.generate(content).getImage();

				ImageIO.write(image, "png", stream);
				String base64 = Base64.encode(stream.toByteArray());

				result="data:image/png;base64,"+base64;
			}catch (Exception ex){
				log.error("生成二维码的Base64编码失败",ex);
 			}finally {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 下载二维码图片
	 * @param response
	 */
	public static void downloadQrCode(HttpServletResponse response) {
		Integer tenantId = SecurityUtils.getUser().getTenantId();
		if (ObjectUtil.isNotNull(tenantId)){
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try {
				//手机端极速入职页面地址
				String address="http://10.1.69.173:8076/#/login?tenantId="+tenantId;
				QrcodeGenerator generator = new SimpleQrcodeGenerator();
				BufferedImage image = generator.generate(address).getImage();

				//BufferedImage 转 InputStream
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
				ImageIO.write(image, "png", imageOutput);
				InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				long length = imageOutput.length();

				//设置response
				response.setContentType("image/png");
				response.setContentLength((int)length);
				String fileName="qrCode.png";
				response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));

				//输出流
				byte[] bytes = new byte[1024];
				OutputStream outputStream = response.getOutputStream();
				long count = 0;
				while(count < length){
					int len = inputStream.read(bytes, 0, 1024);
					count +=len;
					outputStream.write(bytes, 0, len);
				}
				outputStream.flush();
			}catch (Exception ex){
				log.error("生成二维码的Base64编码失败",ex);
			}finally {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

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
