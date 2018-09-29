package com.yangbingdong.serializer.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.yangbingdong.serializer.Serializer;

import java.io.ByteArrayOutputStream;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class KryoPoolSerializer implements Serializer {
//	private KryoPool pool = new KryoPool.Builder(Kryo::new).softReferences().build();
	private KryoPool pool = new KryoPool.Builder(Kryo::new).build();

	@Override
	public <T> byte[] serialize(T obj) {
		return pool.run(kryo -> {
			try (Output output = new Output(new ByteArrayOutputStream())) {
				kryo.writeClassAndObject(output, obj);
				return output.toBytes();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return pool.run(kryo -> {
			try (Input input = new Input(data)) {
				return (T) kryo.readClassAndObject(input);
			}
		});
	}
}
