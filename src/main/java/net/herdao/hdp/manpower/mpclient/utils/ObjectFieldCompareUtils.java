package net.herdao.hdp.manpower.mpclient.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象属性对比 TODO
 * 
 * @author shuling
 * @date 2020-10-20 11:37:27
 */
public class ObjectFieldCompareUtils {

	/**
	 * 获取两个对象同名属性内容不相同的列表
	 * 
	 * @param class1 对象1
	 * @param class2 对象2
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 */
	public static List<Map<String, Object>> compareObject(Object cls1, Object cls2) {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			// 获取对象的class
			Class<?> clazz1 = cls1.getClass();
			Class<?> clazz2 = cls2.getClass();
			// 获取对象的属性列表
			Field[] field1 = clazz1.getDeclaredFields();
			Field[] field2 = clazz2.getDeclaredFields();
			// 遍历属性列表field1
			for (int i = 0; i < field1.length; i++) {
				// 遍历属性列表field2
				for (int j = 0; j < field2.length; j++) {
					// 如果field1[i]属性名与field2[j]属性名内容相同
					if (field1[i].getName().equals(field2[j].getName())) {
						field1[i].setAccessible(true);
						field2[j].setAccessible(true);
						// 如果field1[i]属性值与field2[j]属性值内容不相同
						if (!compareTwo(field1[i].get(cls1), field2[j].get(cls2))) {
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("name", field1[i].getName());
							map2.put("oldVal", field1[i].get(cls1));
							map2.put("newVal", field2[j].get(cls2));
							dataList.add(map2);
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * 对比两个数据是否内容相同
	 * 
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean compareTwo(Object object1, Object object2) {

		if (object1 == null && object2 == null) {
			return true;
		}
		if (object1 == null && object2 != null) {
			return false;
		}
		if (object1.equals(object2)) {
			return true;
		}
		return false;
	}
}
