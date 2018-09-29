package com.yangbingdong.serializer.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.yangbingdong.serializer.Serializer;

import java.io.ByteArrayOutputStream;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class KryoSerializer implements Serializer {

	private static final ThreadLocal<Kryo> KRYOS = ThreadLocal.withInitial(Kryo::new);

	@Override
	public <T> byte[] serialize(T obj) {
		Kryo kryo = getKryo(obj.getClass());
		try (Output output = new Output(new ByteArrayOutputStream())) {
			kryo.writeClassAndObject(output, obj);
			return output.toBytes();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		Kryo kryo = getKryo(clazz);
		try (Input input = new Input(data)) {
			return (T) kryo.readClassAndObject(input);
		}
	}

	private Kryo getKryo(Class<?> clazz) {
		Kryo kryo = KRYOS.get();
		kryo.setReferences(false);
		kryo.register(clazz);
		return kryo;
	}
}
