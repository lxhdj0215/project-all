package com.lxhdj.porn;

public class Video91Test {

	public static void main(String[] args) {
		String ip ="1.0.1.20";
		System.out.println(addIp(ip));

	}

	public static String addIp(String currentIp) {
		String ip = null;
		String[] ips = currentIp.split("\\.");
		int[] int_ips = { Integer.parseInt(ips[0]), Integer.parseInt(ips[1]), Integer.parseInt(ips[2]), Integer.parseInt(ips[3]) };
		int_ips[3] += 10;
		if (int_ips[3] <= 255) {
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
		int_ips[3] = int_ips[3] % 255;
		int_ips[2]++;
		if (int_ips[2] <= 255) {
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
		int_ips[1]++;
		if (int_ips[1] <= 255) {
			int_ips[2] = 0;
			ip = int_ips[0] + "." + int_ips[1] + "." + int_ips[2] + "." + int_ips[3];
			return ip;
		}
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
