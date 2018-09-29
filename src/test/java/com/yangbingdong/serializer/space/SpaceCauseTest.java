package com.yangbingdong.serializer.space;

import com.yangbingdong.serializer.util.RedisOpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
			redisOpUtil.set("Human:" + i, getHuman(i), 0);
		}
		long end = System.currentTimeMillis();

		redisTemplate.execute((RedisCallback) connection -> {
			Object o = connection.info("memory").get("used_memory_human");
			System.out.println("===========> memory use: " + o.toString());
			System.out.println("===========> time use: " + (end - start) + "ms");
			return null;
		});
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
	 *
	 * kryo
	 * serialize:
	 * ===========> memory use: 153.79M
	 * ===========> time use: 37226ms
	 * deserialize:
	 * ===========> time use: 35592ms
	 *
	 * protostuff
	 * serialize:
	 * ===========> memory use: 123.15M
	 * ===========> time use: 35264ms
	 * deserialize:
	 * ===========> time use: 35802ms
	 */
}
