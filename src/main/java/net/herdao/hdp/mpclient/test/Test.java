package net.herdao.hdp.mpclient.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
      /*  String str1 = "001";
        String str2 = "002";
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal num3 = new BigDecimal("1");
        BigDecimal result = num1.add(num3);
        System.out.println(num1);*/
        /*String a1="001033";
        System.out.println(Long.parseLong(a1));*/
        /* a1+
        String a2="001002";
        if (Long.parseLong(a2)>Long.parseLong(a1)){
            System.out.println("yes");
        }

        System.out.println(Integer.parseInt(a2));
      *//*  DecimalFormat decimalFormat=new DecimalFormat("000000");*//*
        String a3 = a2 + 000001;
        System.out.println(a3);
       *//* System.out.println(Long.parseLong(a2)+1);*//*


        Format f1 = new DecimalFormat("#000000");
        f1.format(Long.parseLong(a2));*/

       /* List<String> arr=new ArrayList<>();
        arr.add("001");
        arr.add("004");
        arr.add("002");
        arr.add("003");

        String max="";

        for (String s : arr) {
            if (max.isEmpty()){
                max=s;
            }
            if (Long.parseLong(s)>Long.parseLong(max)){
                max=s;
            }
        }

        System.out.println(max);*/
      /*  String str1 = "001003";
        System.out.println(str1.length());
        Long temp= Long.parseLong(str1)+1;

        String hello="6";
        String c= String.format("%0"+hello+"d", temp);

        System.out.println(c);*/


        List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("1", "a1");
        map1.put("2", "b");
        map1.put("3", "c");
        listMaps.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("11", "aa");
        map2.put("22", "bb");
        map2.put("33", "cc");
        listMaps.add(map2);

        for (Map<String, Object> map : listMaps) {
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                System.out.println(stringObjectEntry.getKey());
            }
        }


    }
}
