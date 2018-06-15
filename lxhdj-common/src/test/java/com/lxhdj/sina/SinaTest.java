package com.lxhdj.sina;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class SinaTest {

    @Test
    public void userInfoTest() {
        Sina sina = new Sina();
        HttpClient httpClient = sina.getHttpClient();
        String homeUrl = "https://weibo.com/5869502624/profile?rightmod=1&wvr=6&mod=personinfo&is_all=1";
        String homeHtml = SinaHttpUtil.getHtml(httpClient, homeUrl);
        List<JSONObject> list = sina.getUserInfo(homeHtml);
        System.out.println(list);
    }
}
