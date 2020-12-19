package net.herdao.hdp.manpower.mpclient.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Author Liu Chang
 * @Date 2020/12/8 9:56 上午
 */
public abstract class LocalDateTimeUtils {
	
    public static LocalDateTime convert2LocalDateTime (Long from) {
        if (from != null) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(from), ZoneId.systemDefault());
        }
        return null;
    }

    public static Long convert2Long (LocalDateTime from) {
        if (from != null) {
            return Timestamp.valueOf(from).getTime();
        }
        return null;
    }

    public static String convert2String (LocalDateTime from) {
        if (from != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dtf.format(from);
        }
        return null;
    }
    
    /**
     * 字符串日期转 LocalDateTime
     * 
     * @param dateTime 字符串日期
     * @param format 格式
     * @return
     */
	public static LocalDateTime convertStr2DateTime(String dateTime, String format) {
		if (!ObjectUtil.isAllEmpty(dateTime, format)) {
			DateTimeFormatter dfm = DateTimeFormatter.ofPattern(format);
			return LocalDateTime.parse(dateTime, dfm);
		}
		return null;
	}
}
