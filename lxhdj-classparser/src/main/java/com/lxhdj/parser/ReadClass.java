package com.lxhdj.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxhdj.bean.ClassBean;
import com.lxhdj.bean.ConstantPool;
import com.lxhdj.util.ClassUtil;
import com.lxhdj.util.CommonUtil;
import com.lxhdj.util.ConstantPoolUtil;

public class ReadClass {
	public static void main(String[] args) {
		try {
			read();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	public static void read() throws IOException, ReflectiveOperationException, SecurityException {
		File file = new File("D:\\BaseCartAction.class");
		byte[] bytes = CommonUtil.readFileToByteArray(file);
		InputStream in = new ByteArrayInputStream(bytes);
		ClassBean classBean = new ClassBean();
		try {
			// 魔数 magic u4
			int magic = ClassUtil.readInteger(in, 4);
			String str_magic = "0x" + Integer.toHexString(magic).toUpperCase();
			classBean.setMagic(str_magic);
			System.out.println("魔数：" + str_magic);
			// 次版本号 minor_version u2
			int minor_version = ClassUtil.readInteger(in, 2);
			classBean.setMinor_version(minor_version);
			System.out.println("次版本号：" + minor_version);
			// 主版本号 major_version u2
			int major_version = ClassUtil.readInteger(in, 2);
			classBean.setMajor_version(major_version);
			System.out.println("主版本号：" + major_version);
			// 常量池容量 constant_pool_count u2
			int constant_pool_count = ClassUtil.readInteger(in, 2);
			classBean.setConstant_pool_count(constant_pool_count);
			System.out.println("常量池容量：" + constant_pool_count);
			Map<Integer, ConstantPool> map = new HashMap<>();
			for (int i = 1; i < constant_pool_count; i++) {
				// 常量池类型 tag
				int tag = ClassUtil.readInteger(in, 1);
				System.out.print("序号：" + i);
				System.out.print("\t常量类型：");
				System.out.print(tag);
				System.out.print("\t描述：" + ConstantPoolUtil.getConstantPoolType(tag));
				String methodName = ConstantPoolUtil.getConstantPoolMethod(tag);
				Class<ConstantPoolParser> cls = ConstantPoolParser.class;
				Method method = cls.getDeclaredMethod(methodName, InputStream.class);
				ConstantPool pool = (ConstantPool) method.invoke(null, in);
				pool.setSerialNumber(i);
				map.put(i, pool);
				if (tag == 5 || tag == 6) {
					i++;
				}
			}
			// access_flags
			int access_flags = ClassUtil.readInteger(in, 2);
			System.out.println("access_flags：" + access_flags);
			ClassParser.classTypeParser(access_flags);
			// this_class
			int this_class = ClassUtil.readInteger(in, 2);
			System.out.print("this_class：" + this_class);
			List<String> listThisClass = ClassUtil.getValue(map, this_class);
			System.out.println("：" + listThisClass);
			// super_class
			int super_class = ClassUtil.readInteger(in, 2);
			System.out.print("super_class：" + super_class);
			List<String> listSuperClass = ClassUtil.getValue(map, super_class);
			System.out.println("：" + listSuperClass);
			// interfaces_count
			int interfaces_count = ClassUtil.readInteger(in, 2);
			System.out.println("interfaces_count：" + interfaces_count);
			for (int i = 0; i < interfaces_count; i++) {
				// interfaces
				int interface_ = ClassUtil.readInteger(in, 2);
				System.out.print("interface：" + interface_);
				List<String> listInterface = ClassUtil.getValue(map, interface_);
				System.out.println("：" + listInterface);
			}
			// fields_count
			int fields_count = ClassUtil.readInteger(in, 2);
			System.out.println("fields_count：" + fields_count);
			for (int i = 0; i < fields_count; i++) {
				// fields
				System.out.println("\t====================");
				FieldParser.readFields(in,map);
				System.out.println("\t====================");
			}
			// methods_count
			int methods_count = ClassUtil.readInteger(in, 2);
			System.out.println("methods_count：" + methods_count);
			for (int i = 0; i < methods_count; i++) {
				// methods
				System.out.println("\t====================");
				MethodParser.readMethods(in,map);
				System.out.println("\t====================");
			}
			// attriutes_count
			int attriutes_count = ClassUtil.readInteger(in, 2);
			System.out.println("attriutes_count：" + attriutes_count);
			for (int i = 0; i < attriutes_count; i++) {
				// attributes
				AttributeParser.parserAttribute(in, map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int parser(InputStream in, Map<Integer, ConstantPool> map, int i, int tag) throws IOException {
		switch (tag) {
		case 1:
			ConstantPool pool = ConstantPoolParser.parserUtf8Info(in);
			pool.setSerialNumber(i);
			map.put(i, pool);
			break;
		case 3:
			ConstantPoolParser.readIntegerInfo(in);
			break;
		case 4:
			ConstantPoolParser.readFloatInfo(in);
			break;
		case 5:
			ConstantPoolParser.readLongInfo(in);
			i++;
			break;
		case 6:
			ConstantPoolParser.readDoubleInfo(in);
			i++;
			break;
		case 7:
			ConstantPoolParser.readClassInfo(in);
			break;
		case 8:
			ConstantPoolParser.readStringInfo(in);
			break;
		case 9:
			ConstantPoolParser.readFieldRefInfo(in);
			break;
		case 10:
			ConstantPoolParser.readMethodRefInfo(in);
			break;
		case 11:
			ConstantPoolParser.readInterfaceMethodRefInfo(in);
			break;
		case 12:
			ConstantPoolParser.readNameAndTypeInfo(in);
			break;
		default:
			break;
		}
		return i;
	}

}
