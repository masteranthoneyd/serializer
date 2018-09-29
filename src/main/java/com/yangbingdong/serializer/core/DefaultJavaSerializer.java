package com.yangbingdong.serializer.core;

import com.yangbingdong.serializer.Serializer;
import com.yangbingdong.serializer.exception.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class DefaultJavaSerializer implements Serializer {

	@Override
	public <T> byte[] serialize(T obj) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new SerializationException("Non-serializable object.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
			 ObjectInputStream ois = new ObjectInputStream(bis)) {
			return (T) ois.readObject();
		} catch (IOException | ReflectiveOperationException e) {
			throw new SerializationException("Can't-deserialize object", e);
		}
	}
}
