package com.lxhdj.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class FileUtilTest {

    @Test
    public void deleteFileTest() {
        File file = new File("D:\\1");
        FileUtil.delFile(file);
    }

    @Test
    public void readFileTest() {
        File file = new File("D:\\1.txt");
        try {
            FileUtil.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
