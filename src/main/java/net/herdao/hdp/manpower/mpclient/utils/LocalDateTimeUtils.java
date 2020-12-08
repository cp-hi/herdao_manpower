package net.herdao.hdp.manpower.mpclient.utils;

import org.springframework.security.core.parameters.P;

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
}
