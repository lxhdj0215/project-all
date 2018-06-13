package com.lxhdj.sina;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinaLogin {
	private String userName = "582385546@qq.com";
	private String password = "meng850215";
	private HttpClient httpClient = HttpClients.createDefault();

	public HttpClient login() {
		JSONObject jsonObject = preLogin();
		if (jsonObject == null) {
			System.out.println("登录初始化失败！");
			return null;
		}
		boolean bol = encodePassword(jsonObject);
		if (!bol) {
			System.out.println("密码加密失败！");
			return null;
		}
		bol = login(jsonObject);
		if (!bol) {
			System.out.println("登录失败！");
			return null;
		}
		bol = passwort(jsonObject);
		if (!bol) {
			System.out.println("登录失败！");
			return null;
		}
		System.out.println("登录成功");
		return httpClient;
	}

	public boolean passwort(JSONObject jsonObject) {
		String location = jsonObject.getString("location");
		try {
			@SuppressWarnings("unused")
			String result = SinaHttpUtil.getRequest(httpClient, location);
			return true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 登录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public boolean login(JSONObject jsonObject) {
		String url = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)";
		List<NameValuePair> parameters = getLoginParameters(jsonObject);
		try {
			String content = SinaHttpUtil.postRequest(httpClient, url, parameters, null);
			Pattern pattern = Pattern.compile("location\\.replace\\('(.+?)'\\);");
			Matcher matcher = pattern.matcher(content);
			if (matcher.find()) {
				String location = matcher.group(1);
				jsonObject.put("location", location);
				return true;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取登录参数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<NameValuePair> getLoginParameters(JSONObject jsonObject) {
		List<NameValuePair> parms = new ArrayList<NameValuePair>();
		parms.add(new BasicNameValuePair("entry", "weibo"));
		parms.add(new BasicNameValuePair("geteway", "1"));
		parms.add(new BasicNameValuePair("from", ""));
		parms.add(new BasicNameValuePair("savestate", "7"));
		parms.add(new BasicNameValuePair("useticket", "1"));
		parms.add(new BasicNameValuePair("pagerefer", "http://login.sina.com.cn/sso/logout.php?entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%2F"));
		parms.add(new BasicNameValuePair("vsnf", "1"));
		parms.add(new BasicNameValuePair("su", jsonObject.getString("su")));
		parms.add(new BasicNameValuePair("service", "miniblog"));
		parms.add(new BasicNameValuePair("servertime", jsonObject.getString("servertime")));
		parms.add(new BasicNameValuePair("nonce", jsonObject.getString("nonce")));
		parms.add(new BasicNameValuePair("pwencode", "rsa2"));
		parms.add(new BasicNameValuePair("rsakv", jsonObject.getString("rsakv")));
		parms.add(new BasicNameValuePair("sp", jsonObject.getString("sp")));
		parms.add(new BasicNameValuePair("encoding", "UTF-8"));
		parms.add(new BasicNameValuePair("prelt", "182"));
		parms.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
		parms.add(new BasicNameValuePair("returntype", "META"));
		return parms;
	}

	/**
	 * 密码加密
	 * 
	 * @param jsonObject
	 */
	public boolean encodePassword(JSONObject jsonObject) {
		String servertime = jsonObject.getString("servertime");
		String nonce = jsonObject.getString("nonce");
		String pubkey = jsonObject.getString("pubkey");
		String pwdString = servertime + "\t" + nonce + "\n" + password;
		// System.out.println(pubkey);
		// System.out.println(pwdString);
		try {
			String sp = SinaRsa.encryptByPublicKey(pubkey, "10001", pwdString);
			jsonObject.put("sp", sp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取相关信息
	 */
	public JSONObject preLogin() {
		JSONObject jsonObject = null;
		try {
//			String su = base64Encoder.encode(URLEncoder.encode(userName, "utf-8").getBytes());
			String su = Base64.encodeBase64String(URLEncoder.encode(userName, "utf-8").getBytes());
			String url = "http://login.sina.com.cn/sso/prelogin.php?entry=weibo&rsakt=mod&checkpin=1&client=ssologin.js(v1.4.5)&_=" + System.currentTimeMillis();
			url += "&su=" + su;
			String content = SinaHttpUtil.getRequest(httpClient, url);
//			jsonObject = JSONObject.fromObject(content);
			jsonObject = (JSONObject) JSONObject.stringToValue(content);
			jsonObject.put("su", su);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
