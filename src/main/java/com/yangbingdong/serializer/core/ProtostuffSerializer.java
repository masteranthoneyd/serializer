package com.yangbingdong.serializer.core;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.yangbingdong.serializer.Serializer;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import static com.dyuproject.protostuff.runtime.RuntimeSchema.getSchema;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class ProtostuffSerializer implements Serializer {
	private static Objenesis objenesis = new ObjenesisStd(true);


	@Override
	@SuppressWarnings("unchecked")
	public <T> byte[] serialize(T obj) {
		Class<T> cls = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		return ProtostuffIOUtil.toByteArray(obj, getSchema(cls), buffer);
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> cls) {
		T message = objenesis.newInstance(cls);
		ProtostuffIOUtil.mergeFrom(data, message, getSchema(cls));
		return message;
	}

}
