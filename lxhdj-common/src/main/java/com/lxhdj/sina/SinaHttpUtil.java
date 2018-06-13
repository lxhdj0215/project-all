package com.lxhdj.sina;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SinaHttpUtil {
	private static String userAgent = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
	private static String acceptLanguage = "zh-CN,zh;q=0.8";
	private static String contentEncoding = "GBK";

	public static String getRequest(HttpClient httpClient, String url) throws ClientProtocolException, IOException {
		String content = null;
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept-Language", acceptLanguage);
		httpGet.addHeader("User-Agent", userAgent);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		content = EntityUtils.toString(httpEntity, contentEncoding);
		return content;
	}
	
	public static String getRequest(HttpClient httpClient, String url,Map<String, String> properties) throws ClientProtocolException, IOException {
		String content = null;
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept-Language", acceptLanguage);
		httpGet.addHeader("User-Agent", userAgent);
		if (properties != null) {
			for (String key : properties.keySet()) {
				httpGet.addHeader(key, properties.get(key));
			}
		}
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		content = EntityUtils.toString(httpEntity, contentEncoding);
		return content;
	}
	
	public static String postRequest(HttpClient httpClient, String url, List<NameValuePair> parameters, Map<String, String> properties) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept-Language", acceptLanguage);
		httpPost.addHeader("User-Agent", userAgent);
		if (properties != null) {
			for (String key : properties.keySet()) {
				httpPost.addHeader(key, properties.get(key));
			}
		}
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
		httpPost.setEntity(urlEncodedFormEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		String content = EntityUtils.toString(httpEntity, contentEncoding);
		return content;
	}

	public static List<NameValuePair> getAddWeiboParameterByList(String text) throws UnsupportedEncodingException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("location", "v6_content_home"));
		parameters.add(new BasicNameValuePair("appkey", ""));
		parameters.add(new BasicNameValuePair("style_type", "1"));
		parameters.add(new BasicNameValuePair("pic_id", ""));
		parameters.add(new BasicNameValuePair("text", text));
		parameters.add(new BasicNameValuePair("pdetail", ""));
		parameters.add(new BasicNameValuePair("rank", "0"));
		parameters.add(new BasicNameValuePair("rankid", ""));
		parameters.add(new BasicNameValuePair("module", "stissue"));
		parameters.add(new BasicNameValuePair("pub_source", "main_"));
		parameters.add(new BasicNameValuePair("pub_type", "dialog"));
		parameters.add(new BasicNameValuePair("_t", "0"));
		return parameters;
	}

	public static List<NameValuePair> getWeiboCommentParameterByList(String text) throws UnsupportedEncodingException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("act", "post"));
		parameters.add(new BasicNameValuePair("mid", "3971464383796145"));
		parameters.add(new BasicNameValuePair("uid", "5869502624"));
		parameters.add(new BasicNameValuePair("forward", "0"));
		parameters.add(new BasicNameValuePair("isroot", "0"));
		parameters.add(new BasicNameValuePair("content", text));
		parameters.add(new BasicNameValuePair("location", "page_100505_home"));
		parameters.add(new BasicNameValuePair("module", "scommlist"));
		parameters.add(new BasicNameValuePair("group_source", ""));
		parameters.add(new BasicNameValuePair("pdetail", "1005055869502624"));
		parameters.add(new BasicNameValuePair("_t", "0"));
		return parameters;
	}

	public static String parametersLength(Map<String, String> parameters) {
		String length = "";
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : parameters.keySet()) {
			if (i != 0)
				param.append("&");
			param.append(key).append("=").append(parameters.get(key));
			i++;
		}
		length = String.valueOf(param.length());
		return length;
	}

}
