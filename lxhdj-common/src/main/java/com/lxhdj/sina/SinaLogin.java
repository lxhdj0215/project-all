package com.lxhdj.sina;

import com.alibaba.fastjson.JSONObject;
import com.lxhdj.util.HttpClientUtil;
import com.lxhdj.util.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

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
        jsonObject = encodePassword(jsonObject);
        if (jsonObject == null) {
            System.out.println("密码加密失败！");
            return null;
        }
        jsonObject = login(jsonObject);
        if (jsonObject == null) {
            System.out.println("登录失败！");
            return null;
        }
        jsonObject = passwort(jsonObject);
        if (jsonObject == null) {
            System.out.println("登录失败！");
            return null;
        }
        System.out.println("登录成功");
        return httpClient;
    }

    public JSONObject passwort(JSONObject jsonObject) {
        String location = jsonObject.getString("location");
        try {
            String html = HttpClientUtil.getRequest(httpClient, location);
            Pattern pattern = Pattern.compile("feedBackUrlCallBack\\((.+?)\\);");
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                String resultStr = matcher.group(1);
                JSONObject resultJSONObject = JSONObject.parseObject(resultStr);
                boolean result = (boolean) resultJSONObject.get("result");
                jsonObject.put("result", result);
                return jsonObject;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录
     *
     * @param jsonObject
     * @return
     */
    public JSONObject login(JSONObject jsonObject) {
        String url = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)";
        List<NameValuePair> parameters = getLoginParameters(jsonObject);
        try {
            String content = HttpClientUtil.postRequest(httpClient, url, parameters, null);
            Pattern pattern = Pattern.compile("location\\.replace\\('(.+?)'\\);");
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                String location = matcher.group(1);
                jsonObject.put("location", location);
                return jsonObject;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        parms.add(new BasicNameValuePair("pagerefer", "http://login.sina.com.cn/sso/logout.php?" +
                "entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%2F"));
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
        parms.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?" +
                "framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
        parms.add(new BasicNameValuePair("returntype", "META"));
        return parms;
    }

    /**
     * 密码加密
     *
     * @param jsonObject
     */
    public JSONObject encodePassword(JSONObject jsonObject) {
        String servertime = jsonObject.getString("servertime");
        String nonce = jsonObject.getString("nonce");
        String pubkey = jsonObject.getString("pubkey");
        String pwdString = servertime + "\t" + nonce + "\n" + password;
        try {
            String sp = RsaUtil.encryptByPublicKey(pubkey, "10001", pwdString);
            jsonObject.put("sp", sp);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取相关信息
     */
    public JSONObject preLogin() {
        JSONObject jsonObject = null;
        try {
            String su = Base64.encodeBase64String(URLEncoder.encode(userName, "utf-8").getBytes());
            String url = "http://login.sina.com.cn/sso/prelogin.php?" +
                    "entry=weibo&rsakt=mod&checkpin=1&client=ssologin.js(v1.4.5)&_=" + System.currentTimeMillis();
            url += "&su=" + su;
            String content = HttpClientUtil.getRequest(httpClient, url);
            jsonObject = JSONObject.parseObject(content);
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
