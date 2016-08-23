/**
 * 实体模型包
 */
package com.dbquery.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 * 用户对模型的快速生成和解析
 */
public class Model {

	
	/**
	 * 将Map键值对模型化
	 * @param mapPair 键值对
	 * @param className 目标类型
	 * @return 返回创建的对象
	 * */
	public static Object parseObject(Map<String, Object> mapPair, Class<?> className) {
		if (null == mapPair) {
			return null;
		}
		try {
			Object destObj = className.newInstance();// 创建对象实例
			Field[] fields = destObj.getClass().getDeclaredFields(); // 获取声明的属性域
	        for (Field field : fields) {
	            int mod = field.getModifiers();
	            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
	                continue;    
	            }    
	  
	            field.setAccessible(true);    
	            Object fieldValue = mapPair.get(field.getName());// 获取属性值
	            if (null == fieldValue) { // 属性值为空不加入到属性中
	            	continue;
	            }
	            try {
					field.set(destObj, fieldValue);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return destObj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将实体类序列化为键值对
	 * @param object 要序列化的实体
	 * @return 返回序列化后的键值对
	 * */
	public static Map<String, Object> serializeModel(Object object) {
		if (null == object) 
			return null; // 防止空对象
		try {
			Field[] allDeclaredFields = object.getClass().getDeclaredFields();// 获取以声明的属性域
			Map<String, Object> objectPair = new HashMap<>();// 键值对容器
			
			for (Field field : allDeclaredFields) { // 遍历属性
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) { 
					continue;
				}
				
				// 设置可访问性
				field.setAccessible(true);
				Object fieldValue = field.get(object); // 获取到object的该属性值
				if (null == fieldValue) {
					// 属性值为空
					continue;
				}
				
				// 属性值非空
				String fieldName = field.getName();
				objectPair.put(fieldName, fieldValue);
				
			}
			return objectPair;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
