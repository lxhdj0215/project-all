package com.lxhdj.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class PornUtilTest {

    @Test
    public void pornLoginTest() {
        String userName = "dreamfly020";
        String password = "meng850215";
        String url = "http://91porn.com/login.php";
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> list = PornUtil.getLoginParameters(userName, password);
        try {
            String content = HttpClientUtil.postRequest(httpClient, url, list, null);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
