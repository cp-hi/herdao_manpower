package net.herdao.hdp.manpower.mpclient.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * uuid 工具类
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
public class UUIDUtil {

    /**
     * 获取一个UUID值
     * @return UUID值[String]
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 获取多个UUID值
     * @param number 所需个数
     * @return UUID集合
     */
    public static List<String> getUUID(Integer number){
        List<String> list = new ArrayList<>();
        while (0 <= (number--)){
            list.add(getUUID());
        }
        return list;
    }
}
