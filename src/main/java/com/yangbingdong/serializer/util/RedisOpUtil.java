package com.yangbingdong.serializer.util;

import com.yangbingdong.serializer.Serializer;
import com.yangbingdong.serializer.core.ProtostuffSerializer;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author ybd
 * @date 18-9-29
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class RedisOpUtil {

	private Serializer thirdSerializer = new ProtostuffSerializer();

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;

	@SuppressWarnings("ConstantConditions")
	public boolean set(final String key, final Serializable value, final long seconds) {
		return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
			RedisSerializer<String> serializer = getRedisSerializer();
			byte[] byteKey = serializer.serialize(key);
//			byte[] byteValue = CommonUtil.transObj2ByteArray(value);
			byte[] byteValue = thirdSerializer.serialize(value);
			if (seconds > 0) {
				connection.setEx(byteKey, seconds, byteValue);
			} else {
				connection.set(byteKey, byteValue);
			}
			return true;
		});
	}

	public  <T extends Serializable> T get(final String key, final Class<T> clazz) {
		return redisTemplate.execute((RedisCallback<T>) connection -> {
			RedisSerializer<String> serializer = getRedisSerializer();
			byte[] byteKey = serializer.serialize(key);
			byte[] value = connection.get(byteKey);
			if (value == null) {
				return null;
			}
//			return transByteArray2Obj(value, clazz);
			return thirdSerializer.deserialize(value, clazz);
		});
	}

	private RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
}
