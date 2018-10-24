package com.yangbingdong.serializer.pojo;

import com.yangbingdong.serializer.util.SerializeWrapper;
import com.yangbingdong.serializer.util.SerializeWrapperType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * User: benjamin.wuhaixu
 * Date: 2018-06-06
 * Time: 10:54 am
 */
@Data
@NoArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = -255483595925661875L;
	private static final SerializeWrapperType<SerializeWrapper<Person>> wrapperType = new SerializeWrapperType<SerializeWrapper<Person>>(){};
	private int age;
    private String name;
    private List<String> ss;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

	public List<String> getSs() {
		return ss;
	}

	public Person setSs(List<String> ss) {
		this.ss = ss;
		return this;
	}

	public static void main(String[] args) {
		System.out.println(wrapperType);
	}
}
