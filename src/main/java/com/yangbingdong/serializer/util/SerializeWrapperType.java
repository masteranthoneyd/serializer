package com.yangbingdong.serializer.util;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ybd
 * @date 18-10-24
 * @contact yangbingdong1994@gmail.com
 */
public class SerializeWrapperType<T> {
	@SuppressWarnings("unchecked")
	public static final Class<SerializeWrapper<List<String>>> LIST_STRING = ((Class<SerializeWrapper<List<String>>>) ((ParameterizedType)new SerializeWrapperType<SerializeWrapper<List<String>>>(){}.getType()).getRawType());
	private static final Class LIST_CLASS = List.class;
	private static final Class WRAPPER_CLASS = SerializeWrapper.class;
	private static ConcurrentMap<Class<?>, Type> listClassTypeCache = new ConcurrentHashMap<>(16);

	protected final Type type;

	protected SerializeWrapperType(){
		Type superClass = getClass().getGenericSuperclass();
		type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
	}

	public Type getType() {
		return type;
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<SerializeWrapper<List<T>>> getListTypeClass(Class<T> clazz) {
		Type type = listClassTypeCache.get(clazz);
		if (type == null) {
			Class[] classes = new Class[]{clazz};
		    ParameterizedType innerType = ParameterizedTypeImpl.make(LIST_CLASS, classes, null);
			Type[] types = new Type[]{innerType};
		    ParameterizedType outerType = ParameterizedTypeImpl.make(WRAPPER_CLASS, types, null);
			listClassTypeCache.putIfAbsent(clazz, outerType.getRawType());
			type = listClassTypeCache.get(clazz);
		}
		return (Class<SerializeWrapper<List<T>>>)type;
	}
}
