package com.lxhdj.util;

import com.lxhdj.bean.ConstantPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassUtil {
    public static List<String> getValue(Map<Integer, ConstantPool> map, Integer index) {
        ConstantPool pool = map.get(index);
        Object value = pool.getValue();
        List<String> list = new ArrayList<>();
        Object oldValue = value;
        while (value == null) {
            List<Integer> indexs = pool.getIndexs();
            for (Integer i : indexs) {
                value = getValue(map, i);
                list.addAll((List<String>) value);
            }
        }
        if (oldValue != null) {
            list.add(String.valueOf(value));
        }
        return list;
    }

    public static int readInteger(InputStream in, int len) throws IOException {
        byte[] bytes = new byte[len];
        in.read(bytes);
        int ri = CommonUtil.byteArrayToInt(bytes);
        return ri;
    }

    public static float readFloat(InputStream in) throws IOException {
        byte[] arr = new byte[4];
        in.read(arr);
        float rf = CommonUtil.byteArrayToFloat(arr);
        return rf;
    }

    public static long readLong(InputStream in) throws IOException {
        byte[] arr = new byte[8];
        in.read(arr);
        long rl = CommonUtil.byteArrayToLong(arr);
        return rl;
    }

    public static double readDouble(InputStream in) throws IOException {
        byte[] arr = new byte[8];
        in.read(arr);
        double rd = CommonUtil.byteArrayToDouble(arr);
        return rd;
    }

    public static String readStr(InputStream in, int len) throws IOException {
        byte[] arr = new byte[len];
        in.read(arr);
        String str = new String(arr);
        return str;
    }
}
