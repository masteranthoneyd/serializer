package com.yangbingdong.serializer.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: benjamin.wuhaixu
 * Date: 2018-06-06
 * Time: 10:54 am
 */
@Data
@NoArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = -255483595925661875L;
	private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
