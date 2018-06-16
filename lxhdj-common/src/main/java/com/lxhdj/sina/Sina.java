package com.lxhdj.sina;

import com.alibaba.fastjson.JSONObject;
import com.lxhdj.constant.Constants;
import com.lxhdj.util.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sina {

    public static List<JSONObject> getUserInfo(String homeHtml) {
        List<JSONObject> list = new ArrayList<>();
        Document docHome = Jsoup.parse(homeHtml);
        Elements scripts = docHome.select("script");
        Element elementUser = scripts.get(Constants.CONSTANT_13);
        String elementUserStr = elementUser.toString();
        String jsonUserStr = elementUserStr.substring(Constants.CONSTANT_16,
                elementUserStr.length() - Constants.CONSTANT_10);
        JSONObject jsonObjectUser = JSONObject.parseObject(jsonUserStr);
        String userHtml = (String) jsonObjectUser.get("html");
        Document docUser = Jsoup.parse(userHtml);
        Elements as = docUser.select("a");
        for (Element element : as) {
            Element strong = element.selectFirst("strong");
            Element span = element.selectFirst("span");
            String type = span.text();
            String num = strong.text();
            String link = element.attr("href");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("num", num);
            jsonObject.put("address", "https:" + link);
            list.add(jsonObject);
        }
        return list;
    }


    public HttpClient getHttpClient() {
        SinaLogin sinaLogin = new SinaLogin();
        HttpClient httpClient = sinaLogin.login();
        return httpClient;
    }


    public boolean addComment(HttpClient httpClient) {
        try {
            String url = "https://weibo.com/aj/v6/comment/add?ajwvr=6&__rnd=" + System.currentTimeMillis();
            List<NameValuePair> parameters = SinaHttpUtil.getWeiboCommentParameterByList("3111111");
            Map<String, String> properties = new HashMap<>();
            properties.put("Referer", "https://weibo.com/5869502624/profile?profile_ftype=1&is_all=1");
            String content = HttpClientUtil.postRequest(httpClient, url, parameters, properties);
            JSONObject jsonObject = JSONObject.parseObject(content);
            String code = (String) jsonObject.get("code");
            if (Constants.ADD_WEIBO_SUCCESS.equals(code)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWeibo(HttpClient httpClient) {
        try {
            String url = "https://weibo.com/aj/mblog/add?ajwvr=6&__rnd=" + System.currentTimeMillis();
            List<NameValuePair> parameters = SinaHttpUtil.getAddWeiboParameterByList("4567");
            Map<String, String> properties = new HashMap<>();
            properties.put("Referer", "https://weibo.com/u/5869502624/home?topnav=1&wvr=6");
            String content = HttpClientUtil.postRequest(httpClient, url, parameters, properties);
            JSONObject jsonObject = JSONObject.parseObject(content);
            String code = (String) jsonObject.get("code");
            if (Constants.ADD_WEIBO_SUCCESS.equals(code)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
