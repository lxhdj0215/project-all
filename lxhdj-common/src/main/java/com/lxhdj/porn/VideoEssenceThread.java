package com.lxhdj.porn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VideoEssenceThread implements Runnable {
	private String url;
	private int page;
	private boolean OK = false;

	public VideoEssenceThread(String url, int page) {
		this.url = url;
		this.page = page;
	}

	public void run() {
		Connection conn = DB.getConnection();
		String html = Video91HttpUtil.getHtml(url);
		List<Map<String, String>> list = Video91HttpUtil.getVideoList(html);
		Iterator<Map<String, String>> iterator = list.iterator();
		int i = 1;
		while (iterator.hasNext()) {
			Map<String, String> map = iterator.next();
			String viewKey = map.get("viewkey");
			Video91Dao.updateEssence(viewKey, conn);
			System.out.println("页码：" + page + " 编号：" + i + " ID：" + map.get("id"));
			i++;
		}
		OK = true;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isOK() {
		return OK;
	}

	public void setOK(boolean oK) {
		OK = oK;
	}
}
