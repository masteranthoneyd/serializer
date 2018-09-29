package com.yangbingdong.serializer.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

public class CommonUtil {


    /**
     * 关闭输出流代码
     *
     * @param is
     * @author shenxufei
     */
    public static void closeInpputStream(InputStream is) {
        try {
			if(nonNull(is)) is.close();
        } catch (Exception e) {
        }
    }

    /**
     * 关闭输出流代码
     *
     * @param os
     * @author shenxufei
     */
    public static void closeOutputStream(OutputStream os) {
        try {
        	if(nonNull(os)) {
				os.close();
			}
        } catch (Exception e) {
        }
    }

    /**
     * 对象转数组
     *
     * @param input
     * @return
     */
    public static byte[] transObj2ByteArray(Serializable input) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(input);
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        } finally {
            closeOutputStream(oos);
            closeOutputStream(baos);
        }
    }

    /**
     * 数组转换成对象
     *
     * @param input
     * @param clazz
     * @return
     */
    public static <T extends Serializable> T transByteArray2Obj(byte[] input, Class<T> clazz) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(input);
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            return clazz.cast(obj);
        } catch (Exception e) {
            return null;
        } finally {
            closeInpputStream(bis);
            closeInpputStream(ois);
        }
    }


    /**
     * 根据文件后缀得到MIME
     * @param suffix
     * @return
     */
    public static String getMIME(String suffix) {
        switch (suffix) {
            case ".mp4":
                return "video/mp4";
            case ".flv":
                return "flv-application/octet-stream";
            default:
                return "video/mp4";
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
