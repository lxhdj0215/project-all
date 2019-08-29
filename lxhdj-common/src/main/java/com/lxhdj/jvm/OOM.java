package com.lxhdj.jvm;

import com.lxhdj.constant.Constants;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/17.
 */
public class OOM {
    @Test
    public static void oom() {
        List<Byte[]> list = new ArrayList<>();
        while (true) {
            Byte[] bytes = new Byte[Constants.CONSTANT_1K * Constants.CONSTANT_1K];
            list.add(bytes);
        }
    }
}
