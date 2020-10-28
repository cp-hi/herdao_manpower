package net.herdao.hdp.manpower.mpclient.utils;

import net.herdao.hdp.manpower.mpclient.constant.ManpowerContants;
/**
 * @description StringBuffer 工具类
 * @author      shuling
 * @date        2020-10-26 10:26:45
 *
 */
public class StringBufferUtils {

	/**
	 * StringBuffer 不存在创建
	 * 
	 * @param bf
	 * @return
	 */
	public static StringBuffer getStringBuffer(StringBuffer bf) {
		if (bf == null) {
			bf = new StringBuffer();
		}
		return bf;
	}
    
    /**
     * 拼接bf信息
     * 
     * @param stringBuffer
     * @param msg
     */
	 public static void appendStringBuffer(StringBuffer bf, String msg) {

		if (bf == null) {
			bf = getStringBuffer(null);
		}

		bf.append(bf.length() > 0 ? ManpowerContants.CH_SEMICOLON + msg  : msg);
	}


	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
