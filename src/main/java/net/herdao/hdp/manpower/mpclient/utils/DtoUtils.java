package net.herdao.hdp.manpower.mpclient.utils;

import java.lang.reflect.Method;
/**
 * @author yangrr
 * @date 2020-09-24 18:10:29
 */
public class DtoUtils {

    /**
     * 数据实体值传递（传递条件：属性同名）
     * @param obj 源数据
     * @param clz 目标数据对应的类
     */
    public static<T> T transferObject(Object obj,Class clz) {
        T result = null;
        if (obj != null && !obj.equals("")) {
            Method[] methods = obj.getClass().getMethods();
            try {
                result = (T) clz.newInstance();
            } catch (Exception e1) {
                return null;
            }
            Method m;
            for (int i = 0; i < methods.length; i++) {
                m = methods[i];
                try {
                    if (m.getName().startsWith("set")) {
                        String fieldName = m.getName().replaceFirst("set", "");
                        Method method = result.getClass().getMethod(m.getName(), m.getParameterTypes());
                        Method getMethod = obj.getClass().getMethod("get" + fieldName, new Class[]{});
                        method.invoke(result, getMethod.invoke(obj, new Object[]{}));
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return result;
    }
    /*public static void main(String[] args) {
        Staff staff = new Staff();
        staff.setMobile("13800");
        StaffDTO dto = transferObject(staff, StaffDTO.class);
        System.out.println(dto.getMobile());
    }*/
}
