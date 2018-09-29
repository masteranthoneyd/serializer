package com.yangbingdong.serializer.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * User: benjamin.wuhaixu
 * Date: 2018-06-11
 * Time: 6:12 pm
 */
@Data
@NoArgsConstructor
public class MessageObj implements Serializable {
	private static final long serialVersionUID = 4297877806943040426L;
	private String strObj;
	private float floatObj;
	private List<Double> doubleObjList;
	private boolean boolObj;
	private byte[] bytesObj;
	private int int32Obj;
	private long int64Obj;
	private InnerMessageObj innerMessageObj;
}
