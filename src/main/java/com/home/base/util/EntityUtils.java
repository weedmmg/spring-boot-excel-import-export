package com.home.base.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;


/**
 * @author chenxf
 * @date 3/8/2018
 * @description
 */
public class EntityUtils {
	/**
	 * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
	 * 
	 * @param entity 实体bean
	 */
	public static <T> void setCreatAndUpdatInfo(T entity) {
		setCreateInfo(entity);
		setUpdatedInfo(entity);
	}
	
	/**
	 * 快速将bean的crtUser、crtHost、crtTime附上相关值
	 * 
	 * @param entity 实体bean
	 */
	public static <T> void setCreateInfo(T entity){
		// 默认属性
		String[] fields = {"crtName","crtUser","crtHost","crtTime"};
		setValues(entity,fields);
	}

	/**
	 * 快速将bean的updUser、updHost、updTime附上相关值
	 * 
	 * @param entity 实体bean
	 */
	public static <T> void setUpdatedInfo(T entity){

		// 默认属性
		String[] fields = {"updName","updUser","updHost","updTime"};

		setValues(entity,fields);

	}

	private static  <T> void setValues(T entity,String[] fields){
		String hostIp = "";
		String name = "";
		String id = "";
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			if(request!=null) {
				hostIp = String.valueOf(getIp(request));
				name = String.valueOf(request.getHeader("userName"));
				id = String.valueOf(request.getHeader("userId"));
			}
		} catch (Exception e) {

		}
		Object [] value = null;
		Field field = ReflectionUtils.getAccessibleField(entity, "updTime");
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{name,id,hostIp,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}
	/**
	 * 依据对象的属性数组和值数组对对象的属性进行赋值
	 * 
	 * @param entity 对象
	 * @param fields 属性数组
	 * @param value 值数组
	 */
	private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
		for(int i=0;i<fields.length;i++){
			String field = fields[i];
			if(ReflectionUtils.hasField(entity, field)){
				if(value[i]!=null && StringUtils.isNotBlank(value[i].toString()) &&!"null".equals(value[i].toString())){
					ReflectionUtils.invokeSetter(entity, field, value[i]);
				}

			}
		}
	}
	/**
	 * 根据主键属性，判断主键是否值为空
	 * 
	 * @param entity
	 * @param field
	 * @return 主键为空，则返回false；主键有值，返回true
	 */
	public static <T> boolean isPKNotNull(T entity,String field){
		if (!ReflectionUtils.hasField(entity, field)) {
			return false;
		}
		Object value = ReflectionUtils.getFieldValue(entity, field);
		return value!=null&&!"".equals(value);
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
