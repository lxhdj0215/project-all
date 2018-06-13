package com.lxhdj.porn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class VideoThread implements Runnable {
	private String url;
	private int page;
	private boolean OK = false;

	public VideoThread(String url, int page) {
		this.url = url;
		this.page = page;
	}

	public void run() {
		Connection conn = DB.getConnection();
		String html = Video91HttpUtil.getHtml(url);
		List<Map<String, String>> list = Video91HttpUtil.getVideoList(html);
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println("页码：" + page + " viewkey" +
		// list.get(i).get("viewkey"));
		// }
		Iterator<Map<String, String>> iterator = list.iterator();
		int i = 1;
		while (iterator.hasNext()) {
			Map<String, String> map = iterator.next();
			// String viewKey = map.get("viewkey");
			// boolean bol = Video91Dao.isExist(viewKey, conn);
			// if (bol) {
			// i++;
			// continue;
			// }
			try {
				Video91HttpUtil.getVideoMsg(map, page,i);
				// Video91Dao.saveDb(map, conn);
				Video91Dao.updateDescription(conn, map.get("description"), map.get("id"));
			} catch (Exception e) {
				System.out.println("异常-----页码：" + page + " 编号：" + i + " ID：" + map.get("id"));
				e.printStackTrace();
			}
			// System.out.println(map.get("description"));
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
