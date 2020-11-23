package net.herdao.hdp.manpower.sys.utils;

/**
 * @ClassName ExceptionUtils
 * @Description ExceptionUtils
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/23 9:33
 * @Version 1.0
 */
public class ExceptionUtils {
    // 获取嵌套异常中的最终信息
    public static String getRealMessage(Throwable e) {
        // 如果e不为空，则去掉外层的异常包装
        while (e != null) {
            Throwable cause = e.getCause();
            if (cause == null) {
                return e.getMessage();
            }
            e = cause;
        }
        return "";
    }

}
