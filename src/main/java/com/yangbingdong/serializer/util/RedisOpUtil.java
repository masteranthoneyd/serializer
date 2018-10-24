package com.yangbingdong.serializer.util;

import com.yangbingdong.serializer.Serializer;
import com.yangbingdong.serializer.core.ProtostuffSerializer;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

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
	public boolean set(final String key, final Serializable value) {
		return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
			RedisSerializer<String> serializer = getRedisSerializer();
			byte[] byteKey = serializer.serialize(key);
			byte[] byteValue = thirdSerializer.serialize(value);
			return connection.set(byteKey, byteValue);
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
			return thirdSerializer.deserialize(value, clazz);
		});
	}

	public void hset(final String key, final Map<byte[], byte[]> value) {
		redisTemplate.execute((RedisCallback<Boolean>) connection -> {
			RedisSerializer<String> serializer = getRedisSerializer();
			byte[] byteKey = serializer.serialize(key);
			connection.hMSet(byteKey, value);
			return true;
		});
	}

	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
}
