package com.lxhdj.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.lxhdj.bean.ConstantPool;
import com.lxhdj.util.ClassUtil;

public class MethodParser {
	public static void readMethods(InputStream in, Map<Integer, ConstantPool> map) throws IOException {
		int access_flags = ClassUtil.readInteger(in, 2);
		int name_index = ClassUtil.readInteger(in, 2);
		int descriptor_index = ClassUtil.readInteger(in, 2);
		System.out.println("\taccess_flags：" + access_flags);
		System.out.print("\tname_index：" + name_index);
		List<String> name = ClassUtil.getValue(map, name_index);
		System.out.println("：" + name);
		System.out.print("\tdescriptor：" + descriptor_index);
		List<String> descriptor = ClassUtil.getValue(map, descriptor_index);
		System.out.println("：" + descriptor);
		int attributes_count = ClassUtil.readInteger(in, 2);
		System.out.println("\tattributes_count：" + attributes_count);
		for (int i = 0; i < attributes_count; i++) {
			AttributeParser.parserAttribute(in,map);
		}
	}

}
