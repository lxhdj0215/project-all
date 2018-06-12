package com.lxhdj.parser;

import com.lxhdj.bean.ClassType;

public class ClassParser {

	public static void classTypeParser(int access_flags) {
		int flag;
		// 是否为public 类型
		flag = access_flags & ClassType.ACC_PUBLIC.getValue();
		if (flag == ClassType.ACC_PUBLIC.getValue()) {
			System.out.println("public：是");
		}
		// final
		flag = access_flags & ClassType.ACC_FINAL.getValue();
		if (flag == ClassType.ACC_FINAL.getValue()) {
			System.out.println("final：是");
		}
		// interface
		flag = access_flags & ClassType.ACC_INTERFACE.getValue();
		if (flag == ClassType.ACC_INTERFACE.getValue()) {
			System.out.println("interface：是");
		}
		// abstract
		flag = access_flags & ClassType.ACC_ABSTRACT.getValue();
		if (flag == ClassType.ACC_ABSTRACT.getValue()) {
			System.out.println("abstract：是");
		}
		// synthetic
		flag = access_flags & ClassType.ACC_SYNTHETIC.getValue();
		if (flag == ClassType.ACC_SYNTHETIC.getValue()) {
			System.out.println("synthetic：是");
		}
		// annotation
		flag = access_flags & ClassType.ACC_ANNOTATION.getValue();
		if (flag == ClassType.ACC_ANNOTATION.getValue()) {
			System.out.println("annotation：是");
		}
		// enum
		flag = access_flags & ClassType.ACC_ENUM.getValue();
		if (flag == ClassType.ACC_ENUM.getValue()) {
			System.out.println("enum：是");
		}
	}
}
