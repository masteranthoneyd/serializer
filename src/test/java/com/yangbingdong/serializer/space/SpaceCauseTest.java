package com.yangbingdong.serializer.space;

import com.yangbingdong.serializer.Serializer;
import com.yangbingdong.serializer.core.DefaultJavaSerializer;
import com.yangbingdong.serializer.util.RedisOpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yangbingdong.serializer.space.Human.getHuman;

/**
 * @author ybd
 * @date 18-9-29
 * @contact yangbingdong1994@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpaceCauseTest {

	@Resource
	private RedisTemplate redisTemplate;

	@Resource
	private RedisOpUtil redisOpUtil;

	@Test
	public void batchSetTest() throws InterruptedException {

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			redisOpUtil.set("Human:" + i, getHuman(i));
		}
		long end = System.currentTimeMillis();

		printResult(start, end);
	}

	@Test
	public void batchGetTest() throws InterruptedException {

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Human human = redisOpUtil.get("Human:" + i, Human.class);
		}
		long end = System.currentTimeMillis();

		System.out.println("===========> time use: " + (end - start) + "ms");
	}

	/**
	 * JDK
	 * serialize:
	 * ===========> memory use: 550.52M
	 * ===========> time use: 41738ms
	 * deserialize:
	 * ===========> time use: 51151ms
	 * <p>
	 * kryo
	 * serialize:
	 * ===========> memory use: 153.79M
	 * ===========> time use: 37226ms
	 * deserialize:
	 * ===========> time use: 35592ms
	 * <p>
	 * protostuff
	 * serialize:
	 * ===========> memory use: 123.15M
	 * ===========> time use: 35264ms
	 * deserialize:
	 * ===========> time use: 35802ms
	 */


	/**
	 * ===========> memory use: 15.11M
	 * ===========> time use: 414570ms
	 */
	@Test
	public void setToMap() {
		RedisSerializer<String> redisSerializer = redisOpUtil.getRedisSerializer();
		Serializer thirdSerializer = new DefaultJavaSerializer();
		Coupon4User coupon4User;
		Map<byte[], byte[]> map = new HashMap<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			coupon4User = getCoupon4User();
			map.put(redisSerializer.serialize("id"), thirdSerializer.serialize(coupon4User.getId()));
			map.put(redisSerializer.serialize("dGreater"), thirdSerializer.serialize(coupon4User.getIdGreater()));
			map.put(redisSerializer.serialize("uid"), thirdSerializer.serialize(coupon4User.getUid()));
			map.put(redisSerializer.serialize("actId"), thirdSerializer.serialize(coupon4User.getActId()));
			map.put(redisSerializer.serialize("coupConfId"), thirdSerializer.serialize(coupon4User.getCoupConfId()));
			map.put(redisSerializer.serialize("status"), thirdSerializer.serialize(coupon4User.getStatus()));
			map.put(redisSerializer.serialize("stsTime"), thirdSerializer.serialize(coupon4User.getStsTime()));
			map.put(redisSerializer.serialize("createTime"), thirdSerializer.serialize(coupon4User.getCreateTime()));
			map.put(redisSerializer.serialize("giveExpiredTime"), thirdSerializer.serialize(coupon4User.getGiveExpiredTime()));
			map.put(redisSerializer.serialize("giveFlag"), thirdSerializer.serialize(coupon4User.getGiveFlag()));
			map.put(redisSerializer.serialize("remark"), thirdSerializer.serialize(coupon4User.getRemark()));
			String key = "setToMap" + i;
			redisTemplate.execute((RedisCallback<Boolean>) connection -> {
				byte[] byteKey = redisSerializer.serialize(key);
				connection.hMSet(byteKey, map);
				return true;
			});
		}
		long end = System.currentTimeMillis();
		printResult(start, end);
	}

	/**
	 * ===========> memory use: 2.52M
	 * ===========> time use: 904ms
	 */
	@Test
	public void setCoupon4UserToString() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			redisOpUtil.set("Coupon4User:" + i, getCoupon4User());
		}
		long end = System.currentTimeMillis();

		printResult(start, end);
	}

	private void printResult(long start, long end) {
		redisTemplate.execute((RedisCallback) connection -> {
			Object o = connection.info("memory").get("used_memory_human");
			System.out.println("===========> memory use: " + o.toString());
			System.out.println("===========> time use: " + (end - start) + "ms");
			return null;
		});
	}

	public Coupon4User getCoupon4User() {
		Coupon4User coupon4User = new Coupon4User();
		Date createTime = new Date();
		coupon4User.setId(666666L)
				   .setIdGreater(true)
				   .setUid(123123)
				   .setActId(5467567L)
				   .setCoupConfId(234456234L)
				   .setStatus((byte) 1)
				   .setStsTime("balabalabalabalabala")
				   .setCreateTime(createTime)
				   .setGiveExpiredTime(createTime)
				   .setGiveFlag(1)
				   .setRemark("balabalabalabalabala");
		return coupon4User;

	}
}
