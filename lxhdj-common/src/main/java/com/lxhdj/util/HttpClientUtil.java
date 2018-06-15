package com.lxhdj.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
    public static final String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8";
    public static final String CONTENT_ENCODING = "UTF-8";
    // 代理
    public static final String X_FORWARDED_FOR = "";


    public static String getRequest(HttpClient httpClient, String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-Language", ACCEPT_LANGUAGE);
        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("X-Forwarded-For", X_FORWARDED_FOR);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity, "UTF-8");
        return content;
    }

    public static String getRequest(HttpClient httpClient, String url,
                                    Map<String, String> properties) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-Language", ACCEPT_LANGUAGE);
        httpGet.addHeader("User-Agent", USER_AGENT);
        if (properties != null) {
            for (String key : properties.keySet()) {
                httpGet.addHeader(key, properties.get(key));
            }
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity, CONTENT_ENCODING);
        return content;
    }

    public static String postRequest(HttpClient httpClient, String url, List<NameValuePair> parameters,
                                     Map<String, String> properties) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept-Language", ACCEPT_LANGUAGE);
        httpPost.addHeader("User-Agent", USER_AGENT);
        if (properties != null) {
            for (String key : properties.keySet()) {
                httpPost.addHeader(key, properties.get(key));
            }
        }
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, CONTENT_ENCODING);
        httpPost.setEntity(urlEncodedFormEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity, CONTENT_ENCODING);
        return content;
    }
}
