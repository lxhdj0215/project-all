package com.lxhdj.sina;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SinaUtil {

	public static Map<String, String> getCookieByFile() {
		Map<String, String> map = new HashMap<String, String>();
		File file = new File("D:\\cookies\\weibo.txt");
		List<String> list = CommonUtil.readFile(file);
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			String[] lines = line.split("	");
			map.put(lines[0], lines[1]);
		}
		return map;
	}

	public static String getCookie() {
		Map<String, String> cookieMap = getCookieByFile();
		String cookie = "";
		for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
			cookie = cookie + entry.getKey() + "=" + entry.getValue() + ";";
		}
		return cookie;
	}
}
