package com.lxhdj.porn;

import java.io.File;
import java.sql.Connection;

public class Video91Rename {
	public static void main(String[] args) {
		renameNull();
	}

	public static void renameNull() {
		File file = new File("F:\\sp");
		for (File f : file.listFiles()) {
			String fileName = f.getName();
			String name1 = fileName.substring(0, fileName.indexOf("."));
			String name2 = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
			if ("null".equals(name2)) {
				File destFile = new File(f.getParent() + "\\" + name1 + ".mp4");
				f.renameTo(destFile);
			}
		}
	}

	public static void rename() {
		Connection conn = DB.getConnection();
		File file = new File("I:\\1");
		for (File f : file.listFiles()) {
			String fileName = f.getName();
			int id = Integer.parseInt(fileName.substring(0, 6));
			String info = Video91Dao.getVideoName(id, conn);
			if (info != null) {
				System.out.println(info);
				File destFile = new File("I:\\156004-156872\\" + id + "_" + info + ".mp4");
				f.renameTo(destFile);
			}
		}

	}

	public static void moveVideo() {
		File file = new File("I:\\156004-157830");
		for (File f : file.listFiles()) {
			String fileName = f.getName();
			String name1 = fileName.substring(0, fileName.indexOf("."));
			if (name1.length() == 6) {
				File destFile = new File("I:\\1\\" + fileName);
				f.renameTo(destFile);
			}
		}
	}
}
