package com.lxhdj.sina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SinaDao {
	public static boolean saveDb(Map<String, String> map, Connection conn) {
		String sql_user = "insert into sinauser values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql_user);
			pstm.setString(1, map.get("name"));
			pstm.setString(2, map.get("followLink"));
			pstm.setInt(3, Integer.valueOf(map.get("follow")));
			pstm.setInt(4, Integer.valueOf(map.get("fans")));
			pstm.setString(5, map.get("fansLink"));
			pstm.setInt(6, Integer.valueOf(map.get("weibo")));
			pstm.setString(7, map.get("weiboLink"));
			pstm.setString(8, map.get("userLink"));
			pstm.execute();
			pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isExist(Connection conn, String userName) {
		boolean bol = false;
		String sql_user = "select * from sinauser where username = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql_user);
			pstm.setString(1, userName);
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
}
