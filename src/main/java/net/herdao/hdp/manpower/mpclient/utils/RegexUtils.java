package net.herdao.hdp.manpower.mpclient.utils;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的正则表达式
 * Created by tookbra on 2016/4/7.
 */
public class RegexUtils {
    /**
     * 判断是否是正确的IP地址
     *
     * @param ip
     * @return boolean true,通过，false，没通过
     */
    public static boolean isIp(String ip) {
        if (Strings.isNullOrEmpty(ip))
            return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(regex);
    }
    /**
     * 判断是否是正确的邮箱地址
     *
     * @param email
     * @return boolean true,通过，false，没通过
     */
    public static boolean isEmail(String email) {
        if (Strings.isNullOrEmpty(email))
            return false;
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
    /**
     * 判断是否含有中文，仅适合中国汉字，不包括标点
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean isChinese(String text) {
        if (Strings.isNullOrEmpty(text))
            return false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(text);
        return m.find();
    }
    /**
     * 判断是否正整数
     *
     * @param number
     *      数字
     * @return boolean true,通过，false，没通过
     */
    public static boolean isNumber(String number) {
        if (Strings.isNullOrEmpty(number))
            return false;
        String regex = "[0-9]*";
        return number.matches(regex);
    }
    /**
     * 判断几位小数(正数)
     *
     * @param decimal
     *      数字
     * @param count
     *      小数位数
     * @return boolean true,通过，false，没通过
     */
    public static boolean isDecimal(String decimal, int count) {
        if (Strings.isNullOrEmpty(decimal))
            return false;
        String regex = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){" + count
                + "})?$";
        return decimal.matches(regex);
    }
    /**
     * 判断是否是移动手机号码
     *
     * @param phoneNumber
     *      移动手机号码
     * @return boolean true,通过，false，没通过
     */
    public static boolean isMobilePhoneNumber(String phoneNumber) {
        if (Strings.isNullOrEmpty(phoneNumber))
            return false;
        String regex = "^((13[0-9])|(15[0-9])|(18[1-9]))\\d{8}$";
        return phoneNumber.matches(regex);
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     *      移动手机号码
     * @return boolean true,通过，false，没通过
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (Strings.isNullOrEmpty(phoneNumber))
            return false;
        String regex = "^1\\d{10}$";
        return phoneNumber.matches(regex);
    }
    /**
     * 判断是否含有特殊字符
     *
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean hasSpecialChar(String text) {
        if (Strings.isNullOrEmpty(text))
            return false;
        if (text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            // 如果不包含特殊字符
            return true;
        }
        return false;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
