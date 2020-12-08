package net.herdao.hdp.manpower.mpclient.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author Liu Chang
 * @Date 2020/12/8 9:56 上午
 */
public abstract class LocalDateTimeUtils {
    public static LocalDateTime convert2LocalDateTime (Long from) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(from), ZoneId.systemDefault());
    }

    public static Long convert2Long (LocalDateTime from) {
        return Timestamp.valueOf(from).getTime();
    }

    public static String convert2String (LocalDateTime from) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(from);
    }
}
