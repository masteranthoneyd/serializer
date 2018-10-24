package com.yangbingdong.serializer.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author ybd
 * @date 18-10-22
 * @contact yangbingdong1994@gmail.com
 */
public class SerializeWrapper<T> {
	private T data;

	public T getData() {
		return data;
	}

	public SerializeWrapper setData(T data) {
		this.data = data;
		return this;
	}

	public SerializeWrapper(T data) {
		this.data = data;
	}

	public static <T> SerializeWrapper<T> of(T data) {
		return new SerializeWrapper<>(data);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<SerializeWrapper<T>> classHolder() {
		return (Class<SerializeWrapper<T>>) ((ParameterizedType)new SerializeWrapperType<SerializeWrapper<T>>(){}.getType()).getRawType();
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<SerializeWrapper<T>> classHolder(SerializeWrapperType<SerializeWrapper<T>> typeReference) {
		return (Class<SerializeWrapper<T>>) ((ParameterizedType) typeReference.getType()).getRawType();
	}

}
