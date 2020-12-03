package net.herdao.hdp.manpower.sys.utils;

import net.herdao.hdp.common.core.util.R;

/**
 * 远程调用工具类
 */
public class RemoteCallUtils {
    public static  <E> E checkData(R<E> r) {
        if(r.getCode()==0){
            return r.getData();
        }
        throw new RuntimeException("远程调用异常");
    }
}
