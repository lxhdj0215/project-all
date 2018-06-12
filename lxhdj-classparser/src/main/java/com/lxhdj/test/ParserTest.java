package com.lxhdj.test;

import com.lxhdj.parser.ReadClass;

public final class ParserTest extends ReadClass implements Runnable {

	public static void main(String[] args) {
		System.out.println(a);
//		Thread thread = new Thread(new Runnable() {
//			public void run() {
//				System.out.println("hello world");
//			}
//		});
//		Thread thread_ = new Thread(() -> System.out.println("hello world"));
//		thread_.start();
//		File file = new File("");
//		File[] files = file.listFiles((name) -> name.getName().startsWith("aa"));
	}

	// private static final String CONSTANT = "wgj";

	private static int a = 11111999;

	private float f = 10.111F;

	private long l = 1234;

	private double d = 12.34;

	public static void testB() {
		a = 10;
		System.out.println("hello world");
	}

	@Override
	public void run() {
//		this.d;
		// TODO Auto-generated method stub

	}
}
