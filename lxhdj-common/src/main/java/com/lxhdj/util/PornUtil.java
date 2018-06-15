package com.lxhdj.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class PornUtil {

    public static List<NameValuePair> getLoginParameters(String userName, String password) {
        List<NameValuePair> parms = new ArrayList<>();
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
