package com.lxhdj.sina;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sina {

	public static void main(String[] args) {
		Connection conn = null;
//		Connection conn = DB.getConntion();

		Sina sina = new Sina();
		HttpClient httpClient = sina.getHttpClient();

//		String url = "http://weibo.com/weiboantispam?refer_flag=1005050007_&is_hot=1";
//		String content = sina.getHtml(httpClient, url);
//		Map<String, String> map1 = sina.getFollowMsgPublic(content, "", "");
//		for (String key : map1.keySet()) {
//			System.out.println(key + ":" + map1.get(key));
//		}

		String homeUrl = "http://weibo.com/u/5869502624/home";
		String homeContent = sina.getHtml(httpClient, homeUrl);
//		System.out.println(homeContent);
		Map<String, String> homeMap = sina.getHomeMsg(homeContent);

		String homeFollowsContent = sina.getHtml(httpClient, homeMap.get("followLink"));
		List<Map<String, String>> homeFollowslist = sina.getHomeFollowsList(httpClient, homeFollowsContent);

		Iterator<Map<String, String>> iterator = homeFollowslist.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = iterator.next();
			String user = map.get("user");
			String userLink = map.get("userLink");
			System.out.println("用户：" + user);
			if (SinaDao.isExist(conn, user)) {
				continue;
			}
			sina.getFollowMsg(conn, httpClient, user, userLink);
		}
	}

	public void getFollowMsg(Connection conn, HttpClient httpClient, String user, String url) {
		String content = getHtml(httpClient, url);
		Map<String, String> map = getFollowMsg(content, user, url);
//		for (String key : map.keySet()) {
//			System.out.println(key + ":" + map.get(key));
//		}
		boolean bol = SinaDao.saveDb(map, conn);
		if (!bol) {
			return;
		}
		if("".equals(map.get("followLink"))){
			return;
		}
		String followsUrl = map.get("followLink");
		String followContent = getHtml(httpClient, followsUrl);
		List<Map<String, String>> list = getFollowsList(httpClient, followContent);

		Iterator<Map<String, String>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, String> _map = iterator.next();
			String _user = _map.get("user");
			String _userLink = _map.get("userLink");
			System.out.println("用户：" + _user);
			if (SinaDao.isExist(conn, _user)) {
				continue;
			}
			getFollowMsg(conn, httpClient, _user, _userLink);
		}
	}

	public List<Map<String, String>> getFollowsList(HttpClient httpClient, String content) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		getFollowsListByPage(content, list);
		boolean bol = true;
		int i = 0;
		while (bol && i < 5) {
			Pattern pattern_next_page = Pattern.compile("<a bpfilter=\"page\" class=\"page next S_txt1 S_line1\" href=\"(.+?)\"><span>下一页</span></a>");
			Matcher matcher_next_page = pattern_next_page.matcher(content);
			if (matcher_next_page.find()) {
				String next_page = matcher_next_page.group(1);
				String nextPageUrl = "http://weibo.com" + next_page;
				content = getHtml(httpClient, nextPageUrl);
				getFollowsListByPage(content, list);
				i++;
			} else {
				bol = false;
			}
		}
		return list;
	}

	public void getFollowsListByPage(String content, List<Map<String, String>> list) {
		// 关注
		Pattern pattern_follow = Pattern.compile("<a class=\"S_txt1\" target=\"_blank\"  usercard=\".+?\" href=\"(.+?)\" >(.+?)</a>");
		Matcher matcher_follow = pattern_follow.matcher(content);
		while (matcher_follow.find()) {
			String userLink = matcher_follow.group(1);
			String user = matcher_follow.group(2);
			Map<String, String> map = new HashMap<String, String>();
			map.put("userLink", "http://weibo.com" + userLink);
			map.put("user", user);
			list.add(map);
		}
	}

	public Map<String, String> getFollowMsgPublic(String content, String user, String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user);
		map.put("userLink", url);
		// 关注
		Pattern pattern_follow = Pattern.compile("<tbody>rntttttttttt<tr>rnttttttttttttttttttttt<td class=\"S_line1\">rntttttttttttttttttttttt<strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">关注");
		Matcher matcher_follow = pattern_follow.matcher(content);
		while (matcher_follow.find()) {
			String follow = matcher_follow.group(1);
			map.put("followLink", "");
			map.put("follow", follow);
		}
		// 粉丝
		Pattern pattern_fans = Pattern.compile("关注</span>rntttttttttttttttttttttttttttttttt<td class=\"S_line1\">rntttttttttttttttttttttt<strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">粉丝");
		Matcher matcher_fans = pattern_fans.matcher(content);
		while (matcher_fans.find()) {
			String fans = matcher_fans.group(1);
			map.put("fansLink", "");
			map.put("fans", fans);
		}
		// 微博
		Pattern pattern_weibo = Pattern.compile("粉丝</span>rntttttttttttttttttttttttttttttttt<td class=\"S_line1\">rntttttttttttttttttttttt<strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">微博");
		Matcher matcher_weibo = pattern_weibo.matcher(content);
		while (matcher_weibo.find()) {
			String weibo = matcher_weibo.group(1);
			map.put("weiboLink", "");
			map.put("weibo", weibo);
		}
		return map;
	}

	public Map<String, String> getFollowMsg(String content, String user, String url) {
		Map<String, String> map = null;
		Pattern pattern_follow = Pattern.compile("<tbody>rntttttttttt<tr>rnttttttttttttttttttttt<td class=\"S_line1\">rntttttttttttttttttttttt<strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">关注");
		Matcher matcher_follow = pattern_follow.matcher(content);
		if (matcher_follow.find()) {
			map = getFollowMsgPublic(content, user, url);
		} else {
			map = getFollowMsgPrivate(content, user, url);
		}
		return map;
	}

	public Map<String, String> getFollowMsgPrivate(String content, String user, String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user);
		map.put("userLink", url);
		// 关注
		Pattern pattern_follow = Pattern.compile("<a bpfilter=\"page_frame\"  class=\"t_link S_txt1\" href=\"(.+?)\" ><strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">关注");
		Matcher matcher_follow = pattern_follow.matcher(content);
		while (matcher_follow.find()) {
			String followLink = matcher_follow.group(1);
			String follow = matcher_follow.group(2);
			map.put("followLink", followLink);
			map.put("follow", follow);
		}
		// 粉丝
		Pattern pattern_fans = Pattern.compile("关注.+?<a bpfilter=\"page_frame\"  class=\"t_link S_txt1\" href=\"(.+?)\" ><strong class=\"W_f1.\">(.+?)</strong><span class=\"S_txt2\">粉丝");
		Matcher matcher_fans = pattern_fans.matcher(content);
		while (matcher_fans.find()) {
			String fansLink = matcher_fans.group(1);
			String fans = matcher_fans.group(2);
			map.put("fansLink", fansLink);
			map.put("fans", fans);
		}
		// 微博
		Pattern pattern_weibo = Pattern.compile("<a bpfilter=\"page_frame\"  class=\"t_link S_txt1\" href=\"(.+?)\" ><strong class=\"W_f1.\">(.+?)</strong>");
		Matcher matcher_weibo = pattern_weibo.matcher(content);
		while (matcher_weibo.find()) {
			String weiboLink = matcher_weibo.group(1);
			String weibo = matcher_weibo.group(2);
			map.put("weiboLink", weiboLink);
			map.put("weibo", weibo);
		}
		return map;
	}

	public List<Map<String, String>> getHomeFollowsList(HttpClient httpClient, String content) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		getHomeFollowsListByPage(content, list);
		boolean bol = true;
		while (bol) {
			Pattern pattern_next_page = Pattern.compile("<a bpfilter=\"page\" class=\"page next S_txt1 S_line1\" href=\"(.+?)\"><span>下一页</span></a>");
			Matcher matcher_next_page = pattern_next_page.matcher(content);
			if (matcher_next_page.find()) {
				String next_page = matcher_next_page.group(1);
				String nextPageUrl = "http://weibo.com" + next_page;
				content = getHtml(httpClient, nextPageUrl);
				getHomeFollowsListByPage(content, list);
			} else {
				bol = false;
			}
		}
		return list;
	}

	public void getHomeFollowsListByPage(String content, List<Map<String, String>> list) {
		// 关注
		Pattern pattern_follow = Pattern.compile("<a target=\"_blank\" action-type=\"ignore_list\" node-type=\"screen_name\"  href=\"(.+?)\" class=\"S_txt1\" title=\"(.+?)\" usercard=\"id=.+?\" >.+?</a>");
		Matcher matcher_follow = pattern_follow.matcher(content);
		while (matcher_follow.find()) {
			String userLink = matcher_follow.group(1);
			String user = matcher_follow.group(2);
			Map<String, String> map = new HashMap<String, String>();
			map.put("userLink", "http://weibo.com" + userLink);
			map.put("user", user);
			list.add(map);
		}
	}

	public Map<String, String> getHomeMsg(String content) {
		Map<String, String> map = new HashMap<String, String>();
		// 关注
		Pattern pattern_follow = Pattern.compile("<li class=\"S_line1\"><a bpfilter=\"page_frame\" href=\"(.+?)\" class=\"S_txt1\"><strong node-type=\"follow\">(.+?)</strong><span class=\"S_txt2\">关注");
		Matcher matcher_follow = pattern_follow.matcher(content);
		while (matcher_follow.find()) {
			String followLink = matcher_follow.group(1);
			String follow = matcher_follow.group(2);
			map.put("followLink", "http://weibo.com" + followLink);
			map.put("follow", follow);
		}
		// 粉丝
		Pattern pattern_fans = Pattern.compile("关注</span></a></li>n          <li class=\"S_line1\"><a bpfilter=\"page_frame\" href=\"(.+?)\" class=\"S_txt1\"><strong node-type=\"fans\">(.+?)</strong><span class=\"S_txt2\">粉丝");
		Matcher matcher_fans = pattern_fans.matcher(content);
		while (matcher_fans.find()) {
			String fansLink = matcher_fans.group(1);
			String fans = matcher_fans.group(2);
			map.put("fansLink", "http://weibo.com" + fansLink);
			map.put("fans", fans);
		}
		// 微博
		Pattern pattern_weibo = Pattern.compile("粉丝</span></a></li>n          <li class=\"S_line1\"><a bpfilter=\"page_frame\" href=\"(.+?)\" class=\"S_txt1\"><strong node-type=\"weibo\">(.+?)</strong>");
		Matcher matcher_weibo = pattern_weibo.matcher(content);
		while (matcher_weibo.find()) {
			String weiboLink = matcher_weibo.group(1);
			String weibo = matcher_weibo.group(2);
			map.put("weiboLink", "http://weibo.com" + weiboLink);
			map.put("weibo", weibo);
		}
		// 微博
		Pattern pattern_name = Pattern.compile("<div class=\"nameBox\"><a bpfilter=\"page_frame\" href=\".+?\" class=\"name S_txt1\" title=\"(.+?)\">.+?</a><a action-type=\"ignore_list\" title=\"微博会员\"");
		Matcher matcher_name = pattern_name.matcher(content);
		while (matcher_name.find()) {
			String name = matcher_name.group(1);
			map.put("name", name);
		}
		return map;
	}

	public HttpClient getHttpClient() {
		SinaLogin sinaLogin = new SinaLogin();
		HttpClient httpClient = sinaLogin.login();
		return httpClient;
	}

	/**
	 * 主页内容
	 *
	 * @param httpClient
	 * @return
	 */
	public String getHtml(HttpClient httpClient, String url) {
		String content = null;
		while (true) {
			try {
				content = SinaHttpUtil.getRequest(httpClient, url);
				content = content.replace("\\", "");
				File file = new File("D:\\wang.txt");
				CommonUtil.writeFile(file, content);
				CommonUtil.sleep(100);
			} catch (Exception e) {
				CommonUtil.sleep(1000);
			}
			if (content != null) {
				break;
			}
		}
		return content;
	}

	public void addComment(HttpClient httpClient) {
		try {
			String url = "http://weibo.com/aj/v6/comment/add?ajwvr=6&__rnd=" + System.currentTimeMillis();
			List<NameValuePair> parameters = SinaHttpUtil.getWeiboCommentParameterByList("1111111");
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("Referer", "http://weibo.com/5869502624/profile?profile_ftype=1&is_all=1");
			String content = SinaHttpUtil.postRequest(httpClient, url, parameters, properties);
			System.out.println(content);
			CommonUtil.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWeibo(HttpClient httpClient) {
		try {
			String url = "http://weibo.com/aj/mblog/add?ajwvr=6&__rnd=" + System.currentTimeMillis();
			List<NameValuePair> parameters = SinaHttpUtil.getAddWeiboParameterByList("4567");
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("Referer", "http://weibo.com/u/5869502624/home?topnav=1&wvr=6");
			String content = SinaHttpUtil.postRequest(httpClient, url, parameters, properties);
			System.out.println(content);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
