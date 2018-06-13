package com.lxhdj.porn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video91Dao {
	public static void saveDb(Map<String, String> map, Connection conn) throws Exception {
		String sql_video = "insert into video91 values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql_author = "insert into author91 values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql_video);
			pstm.setString(1, map.get("title"));
			pstm.setString(2, map.get("info"));
			pstm.setString(3, map.get("link"));
			pstm.setInt(4, Video91Util.getSecond(map.get("runtime")));
			pstm.setString(5, map.get("from"));
			pstm.setInt(6, Integer.valueOf(map.get("views")));
			pstm.setInt(7, Integer.valueOf(map.get("favorites")));
			pstm.setInt(8, Integer.valueOf(map.get("comments")));
			pstm.setInt(9, Integer.valueOf(map.get("videopoint")));
			pstm.setTime(10, Video91Util.stringToSqlDate(map.get("added")));
			pstm.setString(11, map.get("description"));
			pstm.setString(12, map.get("viewkey"));
			pstm.setInt(13, Integer.parseInt(map.get("id")));
			pstm.execute();
			pstm.close();
			pstm = conn.prepareStatement(sql_author);
			pstm.setString(1, map.get("from"));
			pstm.setString(2, map.get("signup"));
			pstm.setInt(3, Integer.valueOf(map.get("level")));
			pstm.setInt(4, Integer.valueOf(map.get("frompoint")));
			pstm.setString(5, map.get("fromvideo"));
			pstm.setInt(6, Integer.valueOf(map.get("videos")));
			pstm.setInt(7, Integer.valueOf(map.get("followed")));
			pstm.setInt(8, Integer.valueOf(map.get("following")));
			pstm.setString(9, map.get("fromlink"));
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			String msg = e.getMessage();
			System.out.print(msg);
			// if (!msg.contains("ORA-00001")) {
			// e.printStackTrace();
			// }
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean updateDescription(Connection conn, String description, String id) {
		String sql = "update video91 set videodescription = ? where id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, description);
			pstm.setInt(2, Integer.parseInt(id));
			pstm.execute();
			pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 视频信息
	 * 
	 * @param conn
	 * @return
	 */
	public static String getVideoMsg(Connection conn, String id) {
		String link = null;
		String sql = "select videolink from video91 where id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(id));
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				link = rs.getString("videolink");
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return link;
	}

	/**
	 * 视频信息
	 * 
	 * @param conn
	 * @return
	 */
	public static List<Map<String, String>> getVideoMsg(Connection conn) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String sql = "select * from video91 where id > 157808 and isdownload = '0' order by id";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String link = rs.getString("videolink");
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				map.put("link", link);
				list.add(map);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 下载
	 * 
	 * @param viewKey
	 * @param conn
	 * @return
	 */
	public static String getVideoLink(String id, Connection conn) {
		String link = null;
		String sql = "select videolink from video91 where id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(id));
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				link = rs.getString(1);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return link;
	}
	
	/**
	 * 下载
	 * 
	 * @param viewKey
	 * @param conn
	 * @return
	 */
	public static boolean updateDownload(String id, Connection conn) {
		boolean bol = false;
		String sql = "update video91 set isdownload = '1' where id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(id));
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bol;
	}

	/**
	 * 更新精华
	 * 
	 * @param viewKey
	 * @param conn
	 * @return
	 */
	public static boolean updateEssence(String viewKey, Connection conn) {
		boolean bol = false;
		String sql = "update video91 set isEssence = '1' where viewkey = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, viewKey);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bol;
	}

	public static boolean isExist(String viewKey, Connection conn) {
		boolean bol = false;
		String sql = "select * from video91 where viewkey = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, viewKey);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				bol = true;
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bol;
	}

	public static String getVideoName(int id, Connection conn) {
		String videoName = null;
		String sql = "select info from video91 where id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				videoName = rs.getString(1);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videoName;
	}
}
