package com.lxhdj.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/17.
 */
public class OOM {
    public static void oom() {
        List<Byte[]> list = new ArrayList<>();
        while (true) {
            Byte[] bytes = new Byte[1024 * 1024];
            list.add(bytes);
        }
    }
}
