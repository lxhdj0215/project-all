package com.lxhdj.util;

import com.lxhdj.constant.Constants;

import java.io.*;

public class FileUtil {

    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[Constants.CONSTANT_4K];
        int bytesRead = 0;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            copy(input, output);
            return output.toByteArray();
        } finally {
            input.close();
        }
    }

    public static void delFile(File file) {
        // 文件处理
        if (!file.isDirectory()) {
            file.delete();
            return;
        }
        File[] files = file.listFiles();
        // 空文件夹删除
        if (files.length == 0) {
            file.delete();
            return;
        }
        // 遍历
        for (int i = 0; i < files.length; i++) {
            delFile(files[i]);
        }
        file.delete();
    }
}
