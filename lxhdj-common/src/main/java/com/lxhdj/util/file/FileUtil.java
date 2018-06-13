package com.lxhdj.util.file;

import java.io.File;

public class FileUtil {
	/**
	 * 扫描文件夹
	 * 
	 * @param file
	 */
	public void delFile(File file) {
		// 文件处理
		if (!file.isDirectory()) {
			file.delete();
			return;
		}
		File[] files = file.listFiles();
		// 空文件夹删除
		if (files.length == 0) {
			file.delete();
			return;
		}
		// 遍历
		for (int i = 0; i < files.length; i++) {
			delFile(files[i]);
		}
		file.delete();
	}
}
