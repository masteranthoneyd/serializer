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
public class Address implements Serializable {
	private static final long serialVersionUID = 8910444770492732841L;
	private String country;
	private String city;
}
