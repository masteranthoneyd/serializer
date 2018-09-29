package com.yangbingdong.serializer.core;

import com.yangbingdong.serializer.Serializer;
import org.nustaq.serialization.FSTConfiguration;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class FSTSerializer implements Serializer {
	private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

	@Override
	public <T> byte[] serialize(T obj) {
		return conf.asByteArray(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return (T) conf.asObject(data);
	}
}
