package com.lxhdj.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lxhdj.bean.ConstantPool;
import com.lxhdj.util.ClassUtil;

public class ConstantPoolParser {
	// UTF-8 编码的字符串：1
	public static ConstantPool parserUtf8Info(InputStream in) throws IOException {
		int length = ClassUtil.readInteger(in, 2);
		String str = ClassUtil.readStr(in, length);
		System.out.println("\t名称：" + str);
		ConstantPool pool = new ConstantPool();
		pool.setType(1);
		pool.setValue(str);
		return pool;
	}

	// 整形字面量：3
	public static ConstantPool readIntegerInfo(InputStream in) throws IOException {
		int i = ClassUtil.readInteger(in, 4);
		System.out.println(i);
		ConstantPool pool = new ConstantPool();
		pool.setType(3);
		pool.setValue(i);
		return pool;

	}

	// 浮点型字面量：4
	public static ConstantPool readFloatInfo(InputStream in) throws IOException {
		float f = ClassUtil.readFloat(in);
		System.out.println(f);
		ConstantPool pool = new ConstantPool();
		pool.setType(4);
		pool.setValue(f);
		return pool;
	}

	// 长整形字面量：5
	public static ConstantPool readLongInfo(InputStream in) throws IOException {
		long l = ClassUtil.readLong(in);
		System.out.println(l);
		ConstantPool pool = new ConstantPool();
		pool.setType(5);
		pool.setValue(l);
		return pool;
	}

	// 双精度浮点型字面量：6
	public static ConstantPool readDoubleInfo(InputStream in) throws IOException {
		double d = ClassUtil.readDouble(in);
		System.out.println(d);
		ConstantPool pool = new ConstantPool();
		pool.setType(6);
		pool.setValue(d);
		return pool;
	}

	// 类或接口的符号引用：7
	public static ConstantPool readClassInfo(InputStream in) throws IOException {
		// name_index
		int index = ClassUtil.readInteger(in, 2);
		System.out.println("\t索引值：" + index);
		ConstantPool pool = new ConstantPool();
		pool.setType(7);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index);
		pool.setIndexs(indexs);
		return pool;
	}

	// 字符串类型字面量：8
	public static ConstantPool readStringInfo(InputStream in) throws IOException {
		// name_index
		int index = ClassUtil.readInteger(in, 2);
		System.out.println("\t索引值：" + index);
		ConstantPool pool = new ConstantPool();
		pool.setType(8);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index);
		pool.setIndexs(indexs);
		return pool;
	}

	// 字段的符号引用：9
	public static ConstantPool readFieldRefInfo(InputStream in) throws IOException {
		int index1 = ClassUtil.readInteger(in, 2);
		int index2 = ClassUtil.readInteger(in, 2);
		System.out.print("：" + index1);
		System.out.println("：" + index2);
		ConstantPool pool = new ConstantPool();
		pool.setType(9);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index1);
		indexs.add(index2);
		pool.setIndexs(indexs);
		return pool;
	}

	// 类中方法的符号引用：10
	public static ConstantPool readMethodRefInfo(InputStream in) throws IOException {
		int index1 = ClassUtil.readInteger(in, 2);
		int index2 = ClassUtil.readInteger(in, 2);
		System.out.print("：" + index1);
		System.out.println("：" + index2);
		ConstantPool pool = new ConstantPool();
		pool.setType(10);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index1);
		indexs.add(index2);
		pool.setIndexs(indexs);
		return pool;
	}

	// 接口中方法的符号引用：11
	public static ConstantPool readInterfaceMethodRefInfo(InputStream in) throws IOException {
		int index1 = ClassUtil.readInteger(in, 2);
		int index2 = ClassUtil.readInteger(in, 2);
		System.out.print("：" + index1);
		System.out.println("：" + index2);
		ConstantPool pool = new ConstantPool();
		pool.setType(11);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index1);
		indexs.add(index2);
		pool.setIndexs(indexs);
		return pool;
	}

	// 字段或方法的符号引用：12
	public static ConstantPool readNameAndTypeInfo(InputStream in) throws IOException {
		int index1 = ClassUtil.readInteger(in, 2);
		int index2 = ClassUtil.readInteger(in, 2);
		System.out.print("：" + index1);
		System.out.println("：" + index2);
		ConstantPool pool = new ConstantPool();
		pool.setType(12);
		List<Integer> indexs = new ArrayList<>(1);
		indexs.add(index1);
		indexs.add(index2);
		pool.setIndexs(indexs);
		return pool;

	}
}
