package com.lxhdj.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantPoolUtil {
	private static final String CONSTANT_UTF8_INFO = "UTF-8编码的字符串";
	private static final String CONSTANT_INTEGER_INFO = "整形字面量";
	private static final String CONSTANT_FLOAT_INFO = "浮点型字面量";
	private static final String CONSTANT_LONG_INFO = "长整形字面量";
	private static final String CONSTANT_DOUBLE_INFO = "双精度浮点型字面量";
	private static final String CONSTANT_CLASS_INFO = "类或接口的符号引用";
	private static final String CONSTANT_STRING_INFO = "字符串类型的字面量";
	private static final String CONSTANT_FIELDREF_INFO = "字段的符号引用";
	private static final String CONSTANT_METHODREF_INFO = "类中方法的符号引用";
	private static final String CONSTANT_INTERFACEMETHODREF_INFO = "接口中方法的符号引用";
	private static final String CONSTANT_NAMEANDTYPE_INFO = "字段和方法的名称以及类型的符号引用";
	private static final String CONSTANT_METHODHANDLE_INFO = "表示方法句柄";
	private static final String CONSTANT_METHODTYPE_INFO = "标识方法类型";
	private static final String CONSTANT_INVOKEDYNAMIC_INFO = "表示一个动态方法调用点";

	private static final String CONSTANT_UTF8_METHOD = "parserUtf8Info";
	private static final String CONSTANT_INTEGER_METHOD = "readIntegerInfo";
	private static final String CONSTANT_FLOAT_METHOD = "readFloatInfo";
	private static final String CONSTANT_LONG_METHOD = "readLongInfo";
	private static final String CONSTANT_DOUBLE_METHOD = "readDoubleInfo";
	private static final String CONSTANT_CLASS_METHOD = "readClassInfo";
	private static final String CONSTANT_STRING_METHOD = "readStringInfo";
	private static final String CONSTANT_FIELDREF_METHOD = "readFieldRefInfo";
	private static final String CONSTANT_METHODREF_METHOD = "readMethodRefInfo";
	private static final String CONSTANT_INTERFACEMETHODREF_METHOD = "readInterfaceMethodRefInfo";
	private static final String CONSTANT_NAMEANDTYPE_METHOD = "readNameAndTypeInfo";
	private static final String CONSTANT_METHODHANDLE_METHOD = "表示方法句柄";
	private static final String CONSTANT_METHODTYPE_METHOD = "标识方法类型";
	private static final String CONSTANT_INVOKEDYNAMIC_METHOD = "表示一个动态方法调用点";

	private static Map<Integer, String> mapInfo = new HashMap<>();
	private static Map<Integer, String> mapMethod = new HashMap<>();

	static {
		mapInfo.put(1, CONSTANT_UTF8_INFO);
		mapInfo.put(3, CONSTANT_INTEGER_INFO);
		mapInfo.put(4, CONSTANT_FLOAT_INFO);
		mapInfo.put(5, CONSTANT_LONG_INFO);
		mapInfo.put(6, CONSTANT_DOUBLE_INFO);
		mapInfo.put(7, CONSTANT_CLASS_INFO);
		mapInfo.put(8, CONSTANT_STRING_INFO);
		mapInfo.put(9, CONSTANT_FIELDREF_INFO);
		mapInfo.put(10, CONSTANT_METHODREF_INFO);
		mapInfo.put(11, CONSTANT_INTERFACEMETHODREF_INFO);
		mapInfo.put(12, CONSTANT_NAMEANDTYPE_INFO);
		mapInfo.put(15, CONSTANT_METHODHANDLE_INFO);
		mapInfo.put(16, CONSTANT_METHODTYPE_INFO);
		mapInfo.put(18, CONSTANT_INVOKEDYNAMIC_INFO);

		mapMethod.put(1, CONSTANT_UTF8_METHOD);
		mapMethod.put(3, CONSTANT_INTEGER_METHOD);
		mapMethod.put(4, CONSTANT_FLOAT_METHOD);
		mapMethod.put(5, CONSTANT_LONG_METHOD);
		mapMethod.put(6, CONSTANT_DOUBLE_METHOD);
		mapMethod.put(7, CONSTANT_CLASS_METHOD);
		mapMethod.put(8, CONSTANT_STRING_METHOD);
		mapMethod.put(9, CONSTANT_FIELDREF_METHOD);
		mapMethod.put(10, CONSTANT_METHODREF_METHOD);
		mapMethod.put(11, CONSTANT_INTERFACEMETHODREF_METHOD);
		mapMethod.put(12, CONSTANT_NAMEANDTYPE_METHOD);
		mapMethod.put(15, CONSTANT_METHODHANDLE_METHOD);
		mapMethod.put(16, CONSTANT_METHODTYPE_METHOD);
		mapMethod.put(18, CONSTANT_INVOKEDYNAMIC_METHOD);
	}

	public static String getConstantPoolType(int tag) {
		return mapInfo.get(tag);
	}

	public static String getConstantPoolMethod(int tag) {
		return mapMethod.get(tag);
	}

}
