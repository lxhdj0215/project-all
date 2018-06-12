package com.lxhdj.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] bytes = new byte[1024 * 1024];
            list.add(bytes);
        }
    }
}
