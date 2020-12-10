package net.herdao.hdp.manpower.mpclient.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DateUtils 日期时间工具类
 * @author andy
 */
public class DateUtils {

    /**
     * 获取当前系统时间的小时(HH)
     *
     * @return int 当前系统小时HH格式
     * @throws Exception
     */
    public static int getHH() throws Exception {
        DateFormat df = new SimpleDateFormat("HH");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return Integer.parseInt(str);
    }

    /**
     * 获取当前系统时间的分钟数(mm)
     *
     * @return int 当前系统分钟mm格式
     * @throws Exception
     */
    public static int getMM() throws Exception {
        DateFormat df = new SimpleDateFormat("mm");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        // return Integer.parseInt(str.split(":")[0]) * 4 +
        // Integer.parseInt(str.split(":")[1]) / 15;
        return Integer.parseInt(str);
    }

    /**
     * 获取当前系统时间的秒数(ss)
     *
     * @return int 当前系统时间的秒数(ss)
     * @throws Exception
     */
    public static int getSS() throws Exception {
        DateFormat df = new SimpleDateFormat("ss");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        // return Integer.parseInt(str.split(":")[0]) * 4 +
        // Integer.parseInt(str.split(":")[1]) / 15;
        return Integer.parseInt(str);
    }

    /**
     * 获取输入日期的前后日期
     *
     * @param date
     *      基准日期 yyyy-MM-dd
     * @param dayMark
     *      +代表往后,-代表往前
     * @return String 前后日期（yyyy-MM-dd）
     * @throws Exception
     */
    public static String getOtherDay(String date, int dayMark) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DAY_OF_MONTH, dayMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在周的第一天(周一)
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @return String 周一（yyyy-MM-dd）
     * @throws Exception
     *
     * */
    public static String getWeekFirstDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int days = c.get(Calendar.DAY_OF_WEEK);
        String strStart = "";
        if (days == 1) {
            strStart = getOtherDay(date, -days - 5);
        } else {
            strStart = getOtherDay(date, -days + 2);
        }
        return strStart;
    }

    /**
     * 获取日期所在周的最后一天（周日）
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @return String（yyyy-MM-dd）
     * @throws Exception
     *
     * */
    public static String getWeekLastDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int days = c.get(Calendar.DAY_OF_WEEK);
        String strStart = "";
        if (days == 1) {
            strStart = getOtherDay(date, 0);
        } else {
            strStart = getOtherDay(date, 8 - days);
        }
        return strStart;
    }

    /**
     * 获取日期所在周（年的周数）的前后周的周一
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @param weekMark找基准日期
     *      +代表往后,-代表往前
     * @return String 前后周的周一（yyyy-MM-dd）
     * @throws Exception
     *
     * */
    public static String getOtherWeekFirstDate(String date, int weekMark)
            throws Exception {
        String firstDate = getWeekFirstDate(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(firstDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.WEEK_OF_YEAR, weekMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在季的第一天
     * @param date基准日期yyyy
     *      -MM-dd
     * @return String
     * @throws Exception
     *
     * */
    public static String getSeasonFirstDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        if (month < 3) {
            month = 0;
        } else if (month >= 3 && month < 6) {
            month = 3;
        } else if (month >= 6 && month < 9) {
            month = 6;
        } else if (month >= 9 && month < 12) {
            month = 9;
        }
        c.set(year, month, 1);

        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在季的前后季度的第一天（xxxx-xx-01）
     *
     * @param date
     *      基准日期yyyy-MM-dd
     * @param seasonMark
     *      找基准日期+代表往后,-代表往前
     * @return String
     * @throws Exception
     *
     * */
    public static String getOtherSeasonFirstDate(String date, int seasonMark)
            throws Exception {
        String firstDate = getSeasonFirstDate(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(firstDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int month = seasonMark * 3;
        if (month != 0) {
            c.add(Calendar.MONTH, month);
        }
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在月的第一天 date基准日期
     *
     * @param date
     *      yyyy-MM-dd
     * @return String （yyyy-MM）
     * @throws Exception
     *
     * */
    public static String getMonthFirstDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(year, month, 1);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在月的最后一天
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @return String yyyy-MM-dd
     * @throws Exception
     *
     * */
    public static String getMonthLastDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayNum = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(year, month, dayNum);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在月的前后月份的第一天（yyyy-MM-01）
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @param monthMark找基准日期
     *      +代表往后,-代表往前
     * @return String
     * @throws Exception
     *
     * */
    public static String getOtherMonthFirstDate(String date, int monthMark)
            throws Exception {
        String firstDate = getMonthFirstDate(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(firstDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, monthMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在年的第一天
     *
     * @param date基准日期yyyy
     *      -MM-dd
     * @return String
     * @throws Exception
     *
     * */
    public static String getYearFirstDate(String date) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(Calendar.YEAR);
        c.set(year, 0, 1);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);
        return strStart;
    }

    /**
     * 获取日期所在年的前后年的第一天（yyyy-01-01）
     *
     * @param date
     *      基准日期yyyy-MM-dd
     * @param monthMark
     *      找基准日期+代表往后,-代表往前
     * @return String
     * @throws Exception
     *
     * */
    public static String getOtherYearFirstDate(String date, int yearMark)
            throws Exception {
        String firstDate = getMonthFirstDate(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(firstDate);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, yearMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime.substring(0, 4);
        return strStart + "-01-01";
    }

    /**
     * 取得同期日期 年同期
     *
     * @param date
     *      yyyy-MM-dd
     * @param year
     *      年份
     * @return 年同期日期yyyy-MM-dd
     * @throws Exception
     */
    public static String getYearTqDay(String date, int year) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, year);
        String mDateTime = df.format(c.getTime());
        return mDateTime;
    }

    /**
     * 取得同期日期 月同期
     *
     * @param date
     *      yyyy-MM-dd
     * @param month
     *      月份
     * @return 月同期日期yyyy-MM-dd
     * @throws Exception
     */
    public static String getMonthTqDay(String date, int month) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, month);
        String mDateTime = df.format(c.getTime());
        return mDateTime;
    }

    /**
     * 取得同比月份
     *
     * @param month
     *      月份
     * @return 同比月份
     * @throws Exception
     */
    public static String getTbMonth(String month) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date dt = df.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, -1);
        String mDateTime = df.format(c.getTime());
        return mDateTime;
    }

    /**
     * 取得环比月份
     *
     * @param month
     *      月份
     * @return 环比月份
     * @throws Exception
     */
    public static String getHbMonth(String month) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date dt = df.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, -1);
        String mDateTime = df.format(c.getTime());
        return mDateTime;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param sDate
     *      -- 起始日期yyyy-MM-dd
     * @param eDate
     *      -- 结束日期yyyy-MM-dd
     * @return int--天数
     * @throws Exception
     * */
    public static int getDaysOfTwoDate(String sDate, String eDate)
            throws Exception {

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df1.parse(sDate);
        Date date2 = df2.parse(eDate);
        if (null == date1 || null == date2) {
            return -1;
        }
        long intervalMilli = date2.getTime() - date1.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取两个月份之间的月数
     *
     * @param sDate
     *      -- 起始月yyyy-MM
     * @param eDate
     *      -- 结束月yyyy-MM
     * @return int--天数
     * @throws Exception
     * */
    public static int getMonthOfTwoMonth(String sDate, String eDate)
            throws Exception {

        DateFormat df = new SimpleDateFormat("yyyy-MM");

        Date date1 = df.parse(sDate);
        Date date2 = df.parse(eDate);
        if (null == date1 || null == date2) {
            return -1;
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        int month1 = c1.get(Calendar.YEAR) * 12 + c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.YEAR) * 12 + c2.get(Calendar.MONTH);

        return month2 - month1;
    }

    /**比较两个日期
     * @param sDate 起始日期
     * @param eDate 结束日期
     * @param pattern 日期格式
     * @return boolean 返回比较结果
     * @throws Exception
     */
    public static boolean compareDate(String sDate, String eDate, String pattern){
        try {
            DateFormat df1 = new SimpleDateFormat(pattern);
            Date date1 = df1.parse(sDate);
            Date date2 = df1.parse(eDate);
            if (null == date1 || null == date2) {
                return false;
            }
            long intervalMilli = date2.getTime() - date1.getTime();
            if (intervalMilli > 0) {
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    /**获取两个日期之间的分钟数
     * @param sDate 起始日期 yyyy-MM-dd HH:mm:ss
     * @param eDate 结束日期 yyyy-MM-dd HH:mm:ss
     * @return int--分钟数
     * @throws Exception
     */
    public static int getMinsOfTwoDate(String sDate, String eDate)
            throws Exception {

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = df1.parse(sDate);
        Date date2 = df2.parse(eDate);
        if (null == date1 || null == date2) {
            return -1;
        }
        long intervalMilli = date2.getTime() - date1.getTime();
        return (int) (intervalMilli / (60 * 1000));
    }

    /**
     * 获取当前系统时间的字符串
     *
     * @return String -- 当天的整个日期字符串，年月日时分秒，返回格式yyyy-MM-dd HH:mm:ss
     * @throws Exception
     * */
    public static String getToDayAllStr() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**
     * 获取当前系统日期的字符串
     *
     * @return String-- 当天的年月日字符串，返回格式 yyyy-MM-dd
     * @throws Exception
     * */
    public static String getToDayDateStr() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**
     * 获取当前系统日期的字符串
     *
     * @return String -- 当天的年月日字符串，返回格式 yyyyMMdd
     * */
    public static String getToDayYmd() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**获取当前系统时间的指定类型字符串
     * @param pattern 指定的格式
     * @return String-- 当天的指定类型的字符串
     * @throws Exception
     */
    public static String getToDayStrByPattern(String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**
     * 获取当前系统时间的字符串
     *
     * @return String 当天的时分秒字符串，返回格式HH:mm:ss
     * */
    public static String getToDayHmsStr() throws Exception {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**
     * 获取当前系统时间的字符串
     *
     * @return String -- 当天的时分秒字符串，返回格式HHmmss
     * */
    public static String getToDayHms() throws Exception {
        DateFormat df = new SimpleDateFormat("HHmmss");
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**获取当前系统指定格式的时间的字符串
     * @param pattern 指定格式
     * @return String 当前系统指定格式时间字符串
     * @throws Exception
     */
    public static String getToDayHmsByPattern(String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        Date date = new Date(System.currentTimeMillis());
        String str = df.format(date);
        return str;
    }

    /**
     * 获取指定日期的时刻字符串
     *
     * @param dayStr
     *      (yyyy-MM-dd HH:mm:ss)
     * @return String -- 当天的时分秒字符串，返回格式：HHmmss
     * */
    public static String getHmsStrForDateTime(String dayStr) throws Exception {
        DateFormat df = new SimpleDateFormat("HHmmss");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(df2.parse(dayStr));
        return str;
    }

    /**
     * 日期格式转换 oldPattern 转成 newPattern
     *
     * @param str 要转换格式的日期字符串
     * @param oldPattern 原有格式
     * @param newPattern 目标格式
     * @return String转换格式化后的字符串
     * @throws Exception
     */
    public static String changeDateType(String str, String oldPattern, String newPattern) throws Exception {
        DateFormat df = new SimpleDateFormat(oldPattern);
        DateFormat df1 = new SimpleDateFormat(newPattern);
        return df1.format(df.parse(str));
    }


    /**
     * 获取输入日期的前后几小时的日期时间
     *
     * @param date基准日期yyyy-MM-dd HH:mm:ss
     * @param dayMark找基准日期
     *      +代表往后,-代表往前
     * @return
     * @throws Exception
     *
     * */
    public static String getOtherHour(String date, int dayMark)
            throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.HOUR_OF_DAY, dayMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime;
        return strStart;
    }

    /**
     * 获取前后分钟数
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @param minuteMark 前后标识+-数值
     * @return 返回
     * @throws Exception
     */
    public static String getOtherMinute(String date, int minuteMark)
            throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = df.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MINUTE, minuteMark);
        String mDateTime = df.format(c.getTime());
        String strStart = mDateTime;
        return strStart;
    }


    /**
     * 解析字符串为Date类型
     * @param date 要被解析的日期字符串
     * @param pattern 类型格式，默认yyyy-MM-dd HH:mm:ss
     * @return Date 被解析后的日期
     * @throws Exception
     */
    public static Date parseDate(String date, String pattern)  {
        Date returnDate = null;
        try {
             if (pattern == null || pattern.equals("") || pattern.equals("null")) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
            try {
                returnDate = sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return returnDate;
    }

    /**
     * 格式化Date类型日期
     * @param date Date类型日期
     * @param pattern 类型格式
     * @return String，被格式化后的日期
     * @throws Exception
     */
    public static String formatDate(Date date, String pattern)  {
        String returnDate = null;

        if (date == null) {
            return "";
        }

        if (pattern == null || pattern.equals("") || pattern.equals("null")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
        returnDate = sdf.format(date);

        return returnDate;
    }

    /**
     * 得到当前月份yyyy-MM；
     *
     * @return String
     */
    public static String getSystemMonth() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String mDateTime1 = formatter.format(date);
        return mDateTime1;
    }

    /**
     * 获取月所在年的最后一个月
     * @param month 月份
     * @param m
     * @return
     * @throws Exception
     */
    public static String getYearLastMonth(String month, int m) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        Date newDate = new Date();
        newDate = format.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c.add(Calendar.YEAR, m);

        newDate.setTime(c.getTimeInMillis());

        return format.format(newDate).substring(0, 4) + "-12";
    }

    /**
     * 获取前后月份
     * + 往后推迟m月
     *
     * @param month
     * @param m
     * @return
     * @throws Exception
     */
    public static String getOtherMonth(String month, int m) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        Date newDate = new Date();
        newDate = format.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c.add(Calendar.MONTH, m);

        newDate.setTime(c.getTimeInMillis());

        return format.format(newDate);
    }

    /**
     * 获取前后月份+ 往后推迟m月
     *
     * @param month
     * @param m
     * @return
     * @throws Exception
     */
    public static String getOtherMonthYMD(String month, int m) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate = new Date();
        newDate = format.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c.add(Calendar.MONTH, m);

        newDate.setTime(c.getTimeInMillis());

        return format.format(newDate);
    }

    /**
     * 根据年月字符串得到那个月的总天数
     * @param monthStr yyyy-MM
     * @return int 总天数
     * @throws Exception
     */
    public static int getDaysOfMonth(String monthStr) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = format.parse(monthStr);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return dayNum;
    }

    /**
     * 获取指定时刻前后指定步长的时刻
     *
     * @param time
     *      时刻HH:mm:ss
     * @param m
     *      步长(+-m) 0:输出原来的时刻，+1输出后一个时刻，-1输出前一个时刻
     * @return String HH:mm:ss
     * @throws Exception
     */
    public static String getBeforeAfterTimeForMin(String time, int m)
            throws Exception {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date newDate = new Date();
        newDate = format.parse(time);
        now.setTime(newDate);
        now.add(Calendar.MINUTE, m);
        return format.format(now.getTime());
    }

    /**
     * 获取指定格式的前后时间
     *
     * @param time
     * @param m
     *      (0:输出原来的时刻，+1输出后一个时刻，-1输出前一个时刻)
     * @param pattern
     *      类型的格式必须被time的格式所包含
     * @return
     * @throws Exception
     */
    public static String getBeforeAfterDateTimeByTime(String time, int m,
                                                      String pattern) throws Exception {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Date newDate = new Date();
        newDate = sdf.parse(time);
        now.setTime(newDate);
        now.add(Calendar.MINUTE, m);

        return sdf.format(now.getTime());
    }

    /**
     * 获取localDateTime格式的当前时间
     * @return
     */
    public static LocalDateTime getNowLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    /**
     * 计算起始日期和当前日期相差多少个月
     * @param stDate
     * @return
     */
    public static Integer getMonthByNow(String stDate) {
        int month = 0, day = 0;
        try {
            LocalDateTime nowLocalDateTime = DateUtils.getNowLocalDateTime();
            Calendar endCalendar = Calendar.getInstance();
            String currentTime = getCurrentTime();
            endCalendar.setTime(stringToDate(currentTime, "yyyy-MM-dd"));
            int enDay = endCalendar.get(Calendar.DATE);

            Calendar lastMonthCalendar = Calendar.getInstance();
            lastMonthCalendar.setTime(stringToDate(currentTime, "yyyy-MM-dd"));
            lastMonthCalendar.set(Calendar.MONTH, endCalendar.get(Calendar.MONTH) - 1);
            int dayOfMonth = lastMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            Calendar stCalendar = Calendar.getInstance();
            stCalendar.setTime(stringToDate(stDate, "yyyy-MM-dd"));

            month = getMonthSpace(stDate, currentTime);
            return  month ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return month;
    }

    /**
     * 计算起始日期和当前日期相差多少个年（向下取整，例如：18个月，不足两年算一年）
     * @param stDate
     * @return
     */
    public static Integer getYearByNowFloor(String stDate) {
        Integer year=0;
        Integer month = getMonthByNow(stDate);
        if (month!=null){
            Double totalYear = Math.floor(month / 12);
            year = totalYear.intValue();
        }

        return year;
    }


    /**
     * 计算连个日期相差多少月+天
     * @param stDate
     * @param endDate
     * @return
     */
    public static String getDayAndMonth(String stDate, String endDate) {
        int month = 0, day = 0;
        try {
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(stringToDate(endDate, "yyyy-MM-dd"));
            int enDay = endCalendar.get(Calendar.DATE);

            Calendar lastMonthCalendar = Calendar.getInstance();
            lastMonthCalendar.setTime(stringToDate(endDate, "yyyy-MM-dd"));
            lastMonthCalendar.set(Calendar.MONTH, endCalendar.get(Calendar.MONTH) - 1);
            int dayOfMonth = lastMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            Calendar stCalendar = Calendar.getInstance();
            stCalendar.setTime(stringToDate(stDate, "yyyy-MM-dd"));

            if (stCalendar.after(endCalendar)) {
                return "起始日期大约结束日期";
            }

            int stDay = stCalendar.get(Calendar.DATE);
            month = getMonthSpace(stDate, endDate);

            if (enDay < stDay) {
                day = dayOfMonth - stDay + enDay;
                month = month - 1;
            } else {
                day = enDay - stDay;
            }
            return  month + "月" + day + "天";
        } catch (ParseException e) {
            e.printStackTrace();
            return "计算失败";
        }
    }

    /**
     * 计算两个日期相差多少月
     * @param stDate <String>
     * @param endDate <String>
     * @return int
     */
    public static int getMonthSpace(String stDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(sdf.parse(stDate));
            aft.setTime(sdf.parse(endDate));
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            return Math.abs(month + result);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * strTime的时间格式必须要与formatType的时间格式相同
     * @param strTime <String>
     * @param formatType <String>
     * @return int
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String now = sdf.format(date);
        return now;
     }


    /**
     * 判断日期格式是否正确  yyyy/mm/dd 或者 yyyy-mm-dd 或者  yyyy.mm.dd
     * @param sDate
     * @return
     */
    public static boolean isLegalDate(String sDate,String pattern) {
        if (sDate == null) {
            return false;
        }

        DateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Date date = formatter.parse(sDate);
            String formatStr = formatter.format(date);
            boolean status = sDate.trim().equals(formatStr.trim());
            return status;
        } catch (Exception e) {
            return false;
        }
    }
 }