package com.lxhdj.porn;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Video91HttpUtil {

	private static String userAgent = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
	private static String acceptLanguage = "zh-CN,zh;q=0.8";
	private static String contentEncoding = "GBK";
	private static String xForwardedFor = "1.0.1.64";
	private static boolean isXForward = true;

	public static String getRequest(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		String content = null;
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept-Language", acceptLanguage);
		httpGet.addHeader("User-Agent", userAgent);
		httpGet.addHeader("X-Forwarded-For", xForwardedFor);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		content = EntityUtils.toString(httpEntity, contentEncoding);
		return content;
	}

	public static String postRequest(HttpClient httpClient, String url, List<NameValuePair> parameters,
			Map<String, String> properties) throws ClientProtocolException, IOException {
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

	public synchronized static String getHtml(String url) {
		String html = null;
		while (true) {
			try {
				html = getRequest(url);
			} catch (IOException e) {
				System.err.println("连接失败" + url);
				CommonUtil.sleep(1000 * 5);
			}
			if (html != null) {
				break;
			}
		}
		return html;
	}

	public synchronized static String getHtml(String url, int page, int i) {
		String html = null;
		i = 0;
		while (true) {
			try {
				System.out.println("=====a=====页码：" + page + " 编号：" + i);
				html = getRequest(url);
				System.out.println("=====b=====页码：" + page + " 编号：" + i);
			} catch (IOException e) {
				if (i == 5) {
					System.err.println("连接失败" + url);
					i = 0;
				}
				i++;
				CommonUtil.sleep(1000 * 5);
			}
			if (html != null) {
				break;
			}
		}
		return html;
	}

	/**
	 * 视频地址
	 *
	 * @param map
	 * @return
	 */
	public static String getVideoLocation(Map<String, String> map) {
		String url = getVideoUrl(map);
		String html = getHtml(url);
		int fromIndex = html.indexOf("=") + 1;
		int endIndex = html.indexOf("&domainUrl");
		String videoLocation = html.substring(fromIndex, endIndex);
		try {
			videoLocation = URLDecoder.decode(videoLocation, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return videoLocation;
	}

	public static String getVideoUrl(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://91porn.com/getfile.php?VID=");
		sb.append(map.get("id"));
		sb.append("&mp4=0&seccode=");
		sb.append(map.get("seccode"));
		sb.append("&max_vid=");
		sb.append(map.get("max_vid"));
		return sb.toString();
	}

	/**
	 * 视频信息 作�?信息
	 *
	 * @param map
	 */
	public static boolean getVideoMsg(Map<String, String> map, int page, int i) {
		String url = map.get("link");
		if (url == null) {
			return false;
		}
		System.out.println("=====1=====页码：" + page + " 编号：" + i);
		String html = Video91HttpUtil.getHtml(url, page, i);
		if (html.contains("视频不存在")) {
			return false;
		}
		System.out.println("=====2=====页码：" + page + " 编号：" + i);
		while (!html.contains("seccode") && isXForward) {
			String ip = Video91Util.addIp(getxForwardedFor());
			System.out.println(ip);
			setxForwardedFor(ip);
			html = Video91HttpUtil.getHtml(url);
		}
		// System.out.println(html);
		// 添加时间
		Pattern pattern_video = Pattern
				.compile(".*<span class=\"info\">添加时间: </span><span class=\"title\">(.*)</span>");
		Matcher matcher_video = pattern_video.matcher(html);
		while (matcher_video.find()) {
			map.put("added", matcher_video.group(1));
		}
		// 作�?信息
		Pattern pattern_from = Pattern.compile(
				".*<span class=\"title\">注册:(.*)&nbsp;&nbsp;级别:(.*)&nbsp;&nbsp;积分:(.*)&nbsp;&nbsp;视频:<a href=(.*)>(.*)</a> &nbsp;&nbsp;粉丝:(.*)&nbsp;&nbsp;关注:(.*)");
		Matcher matcher_from = pattern_from.matcher(html);
		while (matcher_from.find()) {
			map.put("signup", matcher_from.group(1));
			map.put("level", matcher_from.group(2));
			map.put("frompoint", matcher_from.group(3));
			map.put("fromvideo", "http://91porn.com/" + matcher_from.group(4));
			map.put("videos", matcher_from.group(5));
			map.put("followed", matcher_from.group(6));
			String following = matcher_from.group(7);
			if (following != null) {
				following = following.substring(0, following.indexOf("<"));
			}
			map.put("following", following);
		}
		// id
		int fromIndex = html.indexOf("fxFeatureVideo");
		int toIndex = html.indexOf(")", fromIndex + 18);
		String id = html.substring(fromIndex + 18, toIndex);
		map.put("id", id);
		if (html.contains("作为游客，你每天只可观看10个视频")) {
			fromIndex = html.indexOf("</span><br/>");
			toIndex = html.indexOf("</div>", fromIndex);
			String description = html.substring(fromIndex + 13, toIndex - 1);
			System.out.println(html.substring(fromIndex + 10, toIndex - 1));
			description = description.replace("<br>", "");
			description = description.replace("<br/>", "");
			map.put("description", description);
		} else {
			fromIndex = html.indexOf("</span><br/>");
			toIndex = html.indexOf("<form", fromIndex);
			String description = html.substring(fromIndex + 13, toIndex - 6);
			description = description.replace("<br>", "");
			description = description.replace("<br/>", "");
			map.put("description", description);
		}

		// max_vid
		Pattern pattern_max_vid = Pattern.compile(".*so.addVariable\\('max_vid','(.*)'\\);");
		Matcher matcher_max_vid = pattern_max_vid.matcher(html);
		while (matcher_max_vid.find()) {
			map.put("max_vid", matcher_max_vid.group(1));
			// System.out.println(matcher_max_vid.group(1));
		}
		// seccode
		Pattern pattern_seccode = Pattern.compile(".*so.addVariable\\('seccode','(.*)'\\);");
		Matcher matcher_seccode = pattern_seccode.matcher(html);
		while (matcher_seccode.find()) {
			map.put("seccode", matcher_seccode.group(1));
			// System.out.println(matcher_seccode.group(1));
		}
		System.out.println("=====3=====页码：" + page + " 编号：" + i);
		return true;
	}

	/**
	 * 视频信息 作�?信息
	 * 
	 * @param map
	 */
	public static boolean getVideoMsg(Map<String, String> map) {
		String url = map.get("link");
		if (url == null) {
			return false;
		}
		String html = Video91HttpUtil.getHtml(url);
		if (html.contains("视频不存在")) {
			return false;
		}
		while (!html.contains("seccode") && isXForward) {
			String ip = Video91Util.addIp(getxForwardedFor());
			System.out.println(ip);
			setxForwardedFor(ip);
			html = Video91HttpUtil.getHtml(url);
		}
		// System.out.println(html);
		// 添加时间
		Pattern pattern_video = Pattern
				.compile(".*<span class=\"info\">添加时间: </span><span class=\"title\">(.*)</span>");
		Matcher matcher_video = pattern_video.matcher(html);
		while (matcher_video.find()) {
			map.put("added", matcher_video.group(1));
		}
		// 作�?信息
		Pattern pattern_from = Pattern.compile(
				".*<span class=\"title\">注册:(.*)&nbsp;&nbsp;级别:(.*)&nbsp;&nbsp;积分:(.*)&nbsp;&nbsp;视频:<a href=(.*)>(.*)</a> &nbsp;&nbsp;粉丝:(.*)&nbsp;&nbsp;关注:(.*)");
		Matcher matcher_from = pattern_from.matcher(html);
		while (matcher_from.find()) {
			map.put("signup", matcher_from.group(1));
			map.put("level", matcher_from.group(2));
			map.put("frompoint", matcher_from.group(3));
			map.put("fromvideo", "http://91porn.com/" + matcher_from.group(4));
			map.put("videos", matcher_from.group(5));
			map.put("followed", matcher_from.group(6));
			String following = matcher_from.group(7);
			if (following != null) {
				following = following.substring(0, following.indexOf("<"));
			}
			map.put("following", following);
		}
		// id
		int fromIndex = html.indexOf("fxFeatureVideo");
		int toIndex = html.indexOf(")", fromIndex + 18);
		String id = html.substring(fromIndex + 18, toIndex);
		map.put("id", id);
		if (html.contains("作为游客，你每天只可观看10个视频")) {
			fromIndex = html.indexOf("</span><br/>");
			toIndex = html.indexOf("</div>", fromIndex);
			String description = html.substring(fromIndex + 13, toIndex - 1);
			description = description.replace("<br>", "");
			description = description.replace("<br/>", "");
			map.put("description", description);
		} else {
			// fromIndex = html.indexOf("</span><br/>");
			// toIndex = html.indexOf("<form", fromIndex);
			// String description = html.substring(fromIndex + 13, toIndex - 6);
			// description = description.replace("<br>", "");
			// description = description.replace("<br/>", "");
			// map.put("description", description);
		}

		// max_vid
		Pattern pattern_max_vid = Pattern.compile(".*so.addVariable\\('max_vid','(.*)'\\);");
		Matcher matcher_max_vid = pattern_max_vid.matcher(html);
		while (matcher_max_vid.find()) {
			map.put("max_vid", matcher_max_vid.group(1));
			// System.out.println(matcher_max_vid.group(1));
		}
		// seccode
		Pattern pattern_seccode = Pattern.compile(".*so.addVariable\\('seccode','(.*)'\\);");
		Matcher matcher_seccode = pattern_seccode.matcher(html);
		while (matcher_seccode.find()) {
			map.put("seccode", matcher_seccode.group(1));
			// System.out.println(matcher_seccode.group(1));
		}
		return true;
	}

	public static List<Map<String, String>> getVideoList(String html) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 20; i++) {
			Map<String, String> map = new HashMap<String, String>();
			list.add(map);
		}
		// 标题
		Pattern pattern_title = Pattern.compile(".*<span class=\"title\">(.*)</span>");
		Matcher matcher_title = pattern_title.matcher(html);
		int i = 0;
		while (matcher_title.find()) {
			list.get(i).put("title", matcher_title.group(1));
			i++;
		}
		// 链接
		Pattern pattern_link = Pattern.compile(".*<a target=blank href=\"(.*)\" title=\"(.*)\">");
		Matcher matcher_link = pattern_link.matcher(html);
		i = 0;
		while (matcher_link.find()) {
			String link = matcher_link.group(1);
			list.get(i).put("link", link);
			int fromIndex = link.indexOf("=");
			int toIndex = link.indexOf("&");
			String viewkey = link.substring(fromIndex + 1, toIndex);
			list.get(i).put("info", matcher_link.group(2));
			list.get(i).put("viewkey", viewkey);
			i++;
		}
		// 时长
		Pattern pattern_runtime = Pattern.compile(".*<span class=\"info\">时长:</span>(.*)");
		Matcher matcher_runtime = pattern_runtime.matcher(html);
		i = 0;
		while (matcher_runtime.find()) {
			list.get(i).put("runtime", matcher_runtime.group(1));
			i++;
		}
		// 作�?
		Pattern pattern_from = Pattern.compile(".*<a href=\"(.*)\" target=\"_parent\">(.*)</a><br/>");
		Matcher matcher_from = pattern_from.matcher(html);
		i = 0;
		while (matcher_from.find()) {
			list.get(i).put("fromlink", matcher_from.group(1));
			list.get(i).put("from", matcher_from.group(2));
			i++;
		}
		// 查看 收藏
		Pattern pattern_view = Pattern
				.compile(".*<span class=\"info\">查看:</span> (.*)&nbsp; <span class=\"info\">收藏:</span> (.*)<br/>");
		Matcher matcher_view = pattern_view.matcher(html);
		i = 0;
		while (matcher_view.find()) {
			list.get(i).put("views", matcher_view.group(1));
			list.get(i).put("favorites", matcher_view.group(2));
			i++;
		}
		// 留言 积分
		Pattern pattern_comment = Pattern
				.compile(".*<span class=\"info\">留言:</span> (.*)&nbsp;<span class=\"info\">积分:</span> (.*)");
		Matcher matcher_comment = pattern_comment.matcher(html);
		i = 0;
		while (matcher_comment.find()) {
			list.get(i).put("comments", matcher_comment.group(1));
			list.get(i).put("videopoint", matcher_comment.group(2));
			i++;
		}
		return list;
	}

	public static String getUserAgent() {
		return userAgent;
	}

	public static void setUserAgent(String userAgent) {
		Video91HttpUtil.userAgent = userAgent;
	}

	public static String getAcceptLanguage() {
		return acceptLanguage;
	}

	public static void setAcceptLanguage(String acceptLanguage) {
		Video91HttpUtil.acceptLanguage = acceptLanguage;
	}

	public static String getContentEncoding() {
		return contentEncoding;
	}

	public static void setContentEncoding(String contentEncoding) {
		Video91HttpUtil.contentEncoding = contentEncoding;
	}

	public static String getxForwardedFor() {
		return xForwardedFor;
	}

	public static void setxForwardedFor(String xForwardedFor) {
		Video91HttpUtil.xForwardedFor = xForwardedFor;
	}

}
