package com.lxhdj.util;

import com.lxhdj.util.HttpClientUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class HttpClientUtilTest {
    @Test
    public void getRequestTest() {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://www.baidu.com";
        try {
            String html = HttpClientUtil.getRequest(httpClient, url);
//            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
