package com.lxhdj.porn;

import java.io.*;
import java.sql.Connection;
import java.util.*;


public class Video91Download {

	public static void main(String[] args) {
//		getVideoMsg();
		getVideoLocationsById();
	}

	/**
	 * 视频信息
	 */
	public static void getSingleVideoMsg() {
		String url = "http://91porn.com/view_video.php?viewkey=9cf475f2ef09b66f15ea&page=8&viewtype=basic&category=mr";
		Map<String, String> map = new HashMap<String, String>();
		map.put("link", url);
		Video91HttpUtil.getVideoMsg(map);
		// Util.getLogger().info(map.get("info"));
		System.out.println(map.get("description"));
	}

	public static void updateVideoDownload() {
		Connection conn = DB.getConnection();
		try {
			File file = new File("D:\\1.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				Video91Dao.updateDownload(line, conn);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 视频信息
	 */
	public static void getVideoLocationByDb() {
		Connection conn = DB.getConnection();
		List<Map<String, String>> list = Video91Dao.getVideoMsg(conn);
		Iterator<Map<String, String>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = iterator.next();
			System.out.println(map.get("id"));
			boolean bol = Video91HttpUtil.getVideoMsg(map);
			if (!bol) {
				continue;
			}
			String videoLocation = Video91HttpUtil.getVideoLocation(map);
			while ("mp4".equals(videoLocation.substring(videoLocation.length() - 3, videoLocation.length()))) {
				videoLocation = Video91HttpUtil.getVideoLocation(map);
			}
//			CommonUtil.getLogger().error(videoLocation);

		}
	}

	/**
	 * 精华视频
	 */
	public static void videoEssence() {
		for (int i = 61; i < 80; i = i + 10) {
			String url = null;
			int from = i;
			int to = i + 10;
			List<VideoEssenceThread> list = new ArrayList<VideoEssenceThread>();
			for (int j = from; j < to; j++) {
				url = "http://91porn.com/v.php?category=rf&viewtype=basic&page=" + j;
				VideoEssenceThread videoThread = new VideoEssenceThread(url, j);
				list.add(videoThread);
				Thread thread = new Thread(videoThread);
				thread.start();
			}
			while (true) {
				boolean ok = true;
				Iterator<VideoEssenceThread> iterator = list.iterator();
				while (iterator.hasNext()) {
					VideoEssenceThread videoThread = iterator.next();
					ok = ok && videoThread.isOK();
				}
				if (ok) {
					System.out.println(from + "-" + (to - 1) + "：下载完成！");
					break;
				}
				CommonUtil.sleep(1000 * 10);
				System.out.println(from + "-" + (to - 1) + "：正在下载中......");
			}
		}
	}

	/**
	 * 批量视频地址
	 */
	public static void getVideosLocation() {
		for (int i = 1; i < 2; i++) {
			String url = "http://91porn.com/v.php?next=watch&page=" + i;
			String html = Video91HttpUtil.getHtml(url);
			List<Map<String, String>> list = Video91HttpUtil.getVideoList(html);
			Iterator<Map<String, String>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String, String> map = iterator.next();
				Video91HttpUtil.getVideoMsg(map);
				String videoLocation = Video91HttpUtil.getVideoLocation(map);
				while ("mp4".equals(videoLocation.substring(videoLocation.length() - 3, videoLocation.length()))) {
					videoLocation = Video91HttpUtil.getVideoLocation(map);
				}
//				CommonUtil.getLogger().info(videoLocation);
			}
		}
	}

	/**
	 * 获取视频地址
	 */
	public static void getVideoLocation() {
		String url = "http://91porn.com/view_video.php?viewkey=7e8124f2aca8eef94be8&page=5&viewtype=basic&category=mr";
		Map<String, String> map = new HashMap<String, String>();
		map.put("link", url);
		Video91HttpUtil.getVideoMsg(map);
		String videoLocation = Video91HttpUtil.getVideoLocation(map);
//		CommonUtil.getLogger().error(videoLocation);
	}

	/**
	 * 获取视频地址
	 */
	public static void getVideoLocationsById() {
		Connection conn = DB.getConnection();
		File file = new File("E:\\1.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String id = br.readLine();
			while (id != null) {
				String link = Video91Dao.getVideoLink(id, conn);
				Map<String, String> map = new HashMap<String, String>();
				map.put("link", link);
				Video91HttpUtil.getVideoMsg(map);
				String videoLocation = Video91HttpUtil.getVideoLocation(map);
				while ("mp4".equals(videoLocation.substring(videoLocation.length() - 3, videoLocation.length()))) {
					videoLocation = Video91HttpUtil.getVideoLocation(map);
				}
//				CommonUtil.getLogger().error(videoLocation);
				id = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取视频地址
	 */
	public static void getVideoLocationById() {
		Connection conn = DB.getConnection();
		String id = "159576";
		String link = Video91Dao.getVideoLink(id, conn);
		Map<String, String> map = new HashMap<String, String>();
		map.put("link", link);
		Video91HttpUtil.getVideoMsg(map);
		String videoLocation = Video91HttpUtil.getVideoLocation(map);
		while ("mp4".equals(videoLocation.substring(videoLocation.length() - 3, videoLocation.length()))) {
			videoLocation = Video91HttpUtil.getVideoLocation(map);
		}
//		CommonUtil.getLogger().error(videoLocation);
	}

	/**
	 * 视频信息
	 */
	public static void getVideoMsg() {
		String url = "http://91porn.com/v.php?next=watch&page=2";
		String html = Video91HttpUtil.getHtml(url);
		System.out.println(html);
		List<Map<String, String>> list = Video91HttpUtil.getVideoList(html);
		Iterator<Map<String, String>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = iterator.next();
//			Video91HttpUtil.getVideoMsg(map);
			// Util.getLogger().info(map.get("info"));
			System.out.println(map.get("title"));
		}
	}

	/**
	 * 批量 视频信息
	 */
	public static void getVideosMsg() {

		for (int i = 1; i < 2690; i = i + 10) {
			String url = null;

			int from = i;
			int to = i + 10;
			// from = 185;
			// to = 186;
			if (to > 2690) {
				to = 2690;
			}
			List<VideoThread> list = new ArrayList<VideoThread>();
			for (int j = from; j < to; j++) {
				url = "http://91porn.com/v.php?next=watch&page=" + j;
				VideoThread videoThread = new VideoThread(url, j);
				list.add(videoThread);
				Thread thread = new Thread(videoThread);
				thread.start();
			}
			while (true) {
				boolean ok = true;
				Iterator<VideoThread> iterator = list.iterator();
				while (iterator.hasNext()) {
					VideoThread videoThread = iterator.next();
					if (!videoThread.isOK()) {
						System.out.println("----------页码：" + videoThread.getPage());
					}
					ok = ok && videoThread.isOK();
				}
				if (ok) {
					System.out.println(from + "-" + (to - 1) + "：下载完成！");
					break;
				}
				CommonUtil.sleep(1000 * 10);
				System.out.println(from + "-" + (to - 1) + "：正在下载中......");
			}
		}

	}
}
