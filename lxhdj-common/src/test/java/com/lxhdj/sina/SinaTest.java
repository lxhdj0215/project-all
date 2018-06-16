package com.lxhdj.sina;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;

import static org.junit.Assert.assertTrue;

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


    @Test
    public void addWeiboTest() {
        Sina sina = new Sina();
        HttpClient httpClient = sina.getHttpClient();
        boolean result = sina.addWeibo(httpClient);
        assertTrue(result);
    }

    @Test
    public void addCommentTest() {
        Sina sina = new Sina();
        HttpClient httpClient = sina.getHttpClient();
        boolean result = sina.addComment(httpClient);
        assertTrue("添加评论失败", result);
    }
}
