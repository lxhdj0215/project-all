package com.lxhdj.sina;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CommonUtil {
	public static void writeFile(File file,String context) {
		
	}
	
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<String> readFile(File file) {
		List<String> list = new LinkedList<>();
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			in = new FileInputStream(file);
			isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			String line = br.readLine();
			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
