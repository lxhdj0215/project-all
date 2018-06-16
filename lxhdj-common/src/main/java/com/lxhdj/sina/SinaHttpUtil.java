package com.lxhdj.sina;

import com.lxhdj.constant.Constants;
import com.lxhdj.util.CommonUtil;
import com.lxhdj.util.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SinaHttpUtil {

    public static List<NameValuePair> getAddWeiboParameterByList(String text) throws UnsupportedEncodingException {
        List<NameValuePair> parameters = new ArrayList<>();
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
        List<NameValuePair> parameters = new ArrayList<>();
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
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : parameters.keySet()) {
            if (i != 0) {
                param.append("&");
            }
            param.append(key).append("=").append(parameters.get(key));
            i++;
        }
        String length = String.valueOf(param.length());
        return length;
    }

    public static String getHtml(HttpClient httpClient, String url) {
        String content = null;
        while (true) {
            try {
                content = HttpClientUtil.getRequest(httpClient, url);
            } catch (Exception e) {
                CommonUtil.sleep(Constants.CONSTANT_1000);
            }
            if (content != null) {
                break;
            }
        }
        return content;
    }

}
