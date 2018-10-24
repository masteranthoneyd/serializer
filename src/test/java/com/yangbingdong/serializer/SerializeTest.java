package com.yangbingdong.serializer;

import com.yangbingdong.serializer.core.DefaultJavaSerializer;
import com.yangbingdong.serializer.core.FSTSerializer;
import com.yangbingdong.serializer.core.FastJsonSerializer;
import com.yangbingdong.serializer.core.HessianSerializer;
import com.yangbingdong.serializer.core.KryoPoolSerializer;
import com.yangbingdong.serializer.core.KryoSerializer;
import com.yangbingdong.serializer.core.ProtostuffSerializer;
import com.yangbingdong.serializer.pojo.EnumTypeObj;
import com.yangbingdong.serializer.pojo.InnerMessageObj;
import com.yangbingdong.serializer.pojo.MessageObj;
import com.yangbingdong.serializer.pojo.Person;
import com.yangbingdong.serializer.util.SerializeWrapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.yangbingdong.serializer.util.SerializeWrapper.classHolder;
import static com.yangbingdong.serializer.util.SerializeWrapperType.LIST_STRING;
import static com.yangbingdong.serializer.util.SerializeWrapperType.getListTypeClass;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
@SuppressWarnings("WeakerAccess")
public class SerializeTest {
	public Serializer javaSerializer = new DefaultJavaSerializer();
	public Serializer fastJsonSerializer = new FastJsonSerializer();
	public Serializer hessianSerializer = new HessianSerializer();
	public Serializer protostuffSerializer = new ProtostuffSerializer();
	public Serializer kryoSerializer = new KryoSerializer();
	public Serializer kryoPoolSerializer = new KryoPoolSerializer();
	public Serializer fSTSerializer = new FSTSerializer();

	MessageObj pojoBean = getPojoBean();

	byte[] javaSerializeBytes = javaSerializer.serialize(pojoBean);
	byte[] hessianSerializeBytes = hessianSerializer.serialize(pojoBean);
	byte[] fastJsonSerializeBytes = fastJsonSerializer.serialize(pojoBean);
	byte[] protostuffSerializeBytes = protostuffSerializer.serialize(pojoBean);
	byte[] kryoSerializeBytes = kryoSerializer.serialize(pojoBean);
	byte[] kryoPoolSerializeBytes = kryoPoolSerializer.serialize(pojoBean);
	byte[] fSTSerializeBytes = fSTSerializer.serialize(pojoBean);


	@Test
	public void sizeTest() {
		System.out.println("Java-Serialize序列化bytes长度：" + javaSerializeBytes.length);
		System.out.println("Hessian序列化bytes长度：" + hessianSerializeBytes.length);
		System.out.println("FastJson序列化bytes长度：" + fastJsonSerializeBytes.length);
		System.out.println("Protostuff序列化bytes长度" + protostuffSerializeBytes.length);
		System.out.println("Kryo序列化bytes长度" + kryoSerializeBytes.length);
		System.out.println("KryoPool序列化bytes长度" + kryoPoolSerializeBytes.length);
		System.out.println("FST序列化bytes长度" + fSTSerializeBytes.length);
	}

	@Test
	public void testJavaSerializer() {
		Person person = getPerson();
		Serializer serializer = new DefaultJavaSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testFastJsonSerializer() {
		Person person = getPerson();
		Serializer serializer = new FastJsonSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testHessianSerializer() {
		Person person = getPerson();
		Serializer serializer = new HessianSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testKryoSerializer() {
		Person person = getPerson();
		Serializer serializer = new KryoSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testKryoSerializerV2() {
		Person person = getPerson();
		Serializer serializer = new KryoPoolSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testProtostuffSerializer() {
		Person person = getPerson();
		Serializer serializer = new ProtostuffSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void testFSTSerializer() {
		Person person = getPerson();
		Serializer serializer = new FSTSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	@Test
	public void wrapperTest() {
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			personList.add(getPerson());
		}
		Serializer serializer = new ProtostuffSerializer();
		SerializeWrapper<List<Person>> listWrapper = SerializeWrapper.of(personList);
		byte[] bytes = serializer.serialize(listWrapper);
		System.out.println(bytes.length);

		SerializeWrapper<List<Person>> deserialize = serializer.deserialize(bytes, getListTypeClass(Person.class));
		List<Person> list = deserialize.getData();
		System.out.println(list.get(0));
	}

	@Test
	public void wrapperTest1() {
		Person person = getPerson();
		Serializer serializer = new ProtostuffSerializer();
		SerializeWrapper<Person> personWrapper = SerializeWrapper.of(person);
		byte[] bytes = serializer.serialize(personWrapper);
		System.out.println(bytes.length);

		SerializeWrapper<Person> deserialize = serializer.deserialize(bytes, classHolder());
		Person list = deserialize.getData();
		System.out.println(list);
	}

	@Test
	public void wrapperTest2() {
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			personList.add(getPerson());
		}
		Map<String, List<Person>> map = new HashMap<>();
		map.put("list", personList);
		Serializer serializer = new ProtostuffSerializer();
		SerializeWrapper<Map<String, List<Person>>> personWrapper = SerializeWrapper.of(map);
		byte[] bytes = serializer.serialize(personWrapper);
		System.out.println(bytes.length);

		SerializeWrapper<Map<String, List<Person>>> deserialize = serializer.deserialize(bytes, classHolder());
		Map<String, List<Person>> map1 = deserialize.getData();
		System.out.println(map1);
	}

	@Test
	public void wrapperTest3() {
		List<String> stringList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			stringList.add(Integer.toString(i));
		}
		Serializer serializer = new ProtostuffSerializer();
		SerializeWrapper<List<String>> personWrapper = SerializeWrapper.of(stringList);
		byte[] bytes = serializer.serialize(personWrapper);
		System.out.println(bytes.length);

		SerializeWrapper<List<String>> deserialize = serializer.deserialize(bytes, LIST_STRING);
		List<String> data = deserialize.getData();
		System.out.println(data);
	}

	private Person getPerson() {
		Person ben = new Person(21, "ben");
		ben.setSs(IntStream.range(0, 100).mapToObj(String::valueOf).collect(Collectors.toList()));
		return ben;
	}

	private MessageObj getPojoBean() {
		InnerMessageObj innerMessageObj = new InnerMessageObj();
		innerMessageObj.setId(1);
		innerMessageObj.setName("inner");
		innerMessageObj.setEnumTypeObj(EnumTypeObj.PRODUCTS);

		MessageObj msg = new MessageObj();
		msg.setStrObj("message");
		msg.setFloatObj(1f);
		List<Double> doubleList = new ArrayList<>();
		doubleList.add(1d);
		doubleList.add(2d);
		msg.setDoubleObjList(doubleList);
		msg.setBoolObj(true);
		msg.setBytesObj(new byte[] {1, 2, 3});
		msg.setInt32Obj(32);
		msg.setInt64Obj(64L);
		msg.setInnerMessageObj(innerMessageObj);
		return msg;
	}
}
