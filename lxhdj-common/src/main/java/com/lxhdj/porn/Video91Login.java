package com.lxhdj.porn;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Video91Login {
	private String userName = "dreamfly020";
	private String password = "meng850215";
	private HttpClient httpClient = HttpClients.createDefault();

	public static void main(String[] args) {
		Video91Login video91Login = new Video91Login();
		JSONObject jsonObject = new JSONObject();
		video91Login.login(jsonObject);
	}

	/**
	 * 登录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public boolean login(JSONObject jsonObject) {
		String url = "http://91porn.com/login.php";
		List<NameValuePair> parameters = getLoginParameters(jsonObject);
		boolean bol = true;
		while (bol) {
			try {
				String content = Video91HttpUtil.postRequest(httpClient, url, parameters, null);
				if (content != null) {
					bol = true;
				}
				System.out.println(content);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CommonUtil.sleep(1000);
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
		parms.add(new BasicNameValuePair("username", userName));
		parms.add(new BasicNameValuePair("password", password));
		parms.add(new BasicNameValuePair("fingerprint", "3144153322"));
		parms.add(new BasicNameValuePair("captcha_input", "2707"));
		parms.add(new BasicNameValuePair("action_login", "Log+In"));
		parms.add(new BasicNameValuePair("x", "42"));
		parms.add(new BasicNameValuePair("y", "17"));
		return parms;
	}
}
