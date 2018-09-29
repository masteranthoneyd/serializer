package com.yangbingdong.serializer.space;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ybd
 * @date 18-9-29
 * @contact yangbingdong1994@gmail.com
 */
@Data
@Accessors(chain = true)
public class Human implements Serializable {
	private static final long serialVersionUID = 484787512979080147L;
	private int id;
	private String name;
	private int age;
	private Sex sex;
	private double height;
	private double weight;
	private Address address;

	public enum Sex {
		Man, Woman
	}

	public static Human getHuman(int id) {
		return new Human().setId(id)
						  .setAge(24)
						  .setHeight(168.68)
						  .setWeight(99.99)
						  .setName("yangbingdong")
						  .setSex(Sex.Man)
						  .setAddress(new Address().setCountry("CN").setCity("Guangzhou"));

	}

}
