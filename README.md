# 序列化性能测试

![](http://ojoba1c98.bkt.clouddn.com/img/spring-boot-redis/serialize-performance.png)



100W对象测试：

```
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
```

