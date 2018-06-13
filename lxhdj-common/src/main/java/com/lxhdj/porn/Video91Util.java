package com.lxhdj.porn;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video91Util {

	public static Time stringToSqlDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Time sql_time = null;
		try {
			Date util_date = sdf.parse(date);
			sql_time = new Time(util_date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sql_time;
	}

	public static int getSecond(String runtime) {
		String[] runtimes = runtime.split(":");
		int second = Integer.parseInt(runtimes[1]) + Integer.parseInt(runtimes[0]) * 60;
		return second;
	}

	public static String addIp(String currentIp) {
		String ip = null;
		String[] ips = currentIp.split("\\.");
		int[] int_ips = { Integer.parseInt(ips[0]), Integer.parseInt(ips[1]), Integer.parseInt(ips[2]), Integer.parseInt(ips[3]) };
		if (int_ips[3] < 255) {
			int_ips[3]++;
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
		if (int_ips[2] < 255) {
			int_ips[3] = 0;
			int_ips[2]++;
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
		if (int_ips[1] < 255) {
			int_ips[3] = 0;
			int_ips[2] = 0;
			int_ips[1]++;
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
		int_ips[3] = 0;
		int_ips[2] = 0;
		int_ips[1] = 0;
		int_ips[0]++;
		if (int_ips[0] == 10 || int_ips[0] == 172 || int_ips[0] == 192) {
			int_ips[0]++;
		}
		ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
		return ip;
	}
}
