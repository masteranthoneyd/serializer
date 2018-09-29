package com.yangbingdong.serializer.core;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.yangbingdong.serializer.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			HessianOutput ho = new HessianOutput(os);
			ho.writeObject(obj);
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	@SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
		try (ByteArrayInputStream is = new ByteArrayInputStream(data)) {
			HessianInput hi = new HessianInput(is);
			return (T) hi.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
