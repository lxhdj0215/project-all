package com.lxhdj.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
	private static String userAgent = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
	private static String acceptLanguage = "zh-CN,zh;q=0.8";

	public static String getRequest(HttpClient httpClient, String url) throws ClientProtocolException, IOException {
		String content = null;
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept-Language", acceptLanguage);
		httpGet.addHeader("User-Agent", userAgent);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		content = EntityUtils.toString(httpEntity, "GBK");
		return content;
	}
}
