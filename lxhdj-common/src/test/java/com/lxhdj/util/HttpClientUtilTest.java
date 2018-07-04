package com.lxhdj.util;

import com.lxhdj.util.HttpClientUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class HttpClientUtilTest {

    @Test
    public void test() {
        String html = null;
        String url = "http://www.baidu.com";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            httpClient.execute(httpGet);
            html = httpGet.toString();
            System.out.println(html);
            httpGet.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestTest() {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://item.jd.hk/4962188.html";
        try {
            String proxy = "10.186.129.108";
            String html = HttpClientUtil.getRequest(httpClient, url, proxy);
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
