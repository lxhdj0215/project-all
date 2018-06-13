package com.lxhdj.common;

import java.io.*;
import java.util.Properties;

/**
 * Created by wangguijun1 on 2018/4/3.
 */
public class PropertiesUtil {
    private static Properties properties = new Properties();

    static {
        File file = new File("D:\\lxhdj\\config\\jd.properties");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setValue(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

}
