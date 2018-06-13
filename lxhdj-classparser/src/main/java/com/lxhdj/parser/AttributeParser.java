package com.lxhdj.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.lxhdj.bean.ConstantPool;
import com.lxhdj.common.Constants;
import com.lxhdj.util.ClassUtil;

public class AttributeParser {
    public static void parserAttribute(InputStream in, Map<Integer, ConstantPool> map) throws IOException {
        int attributeNameIndex = ClassUtil.readInteger(in, 2);
        System.out.print("\tattribute_name_index：" + attributeNameIndex);
        List<String> name = ClassUtil.getValue(map, attributeNameIndex);
        System.out.println("：" + name);
        int attributeLength = ClassUtil.readInteger(in, Constants.FOUR);
        System.out.println("\tattribute_length：" + attributeLength);
        if ("Code".equals(name.get(0))) {
            readCode(in, map);
        } else if ("LineNumberTable".equals(name.get(0))) {
            readLineNumberTable(in);
        } else if ("LocalVariableTable".equals(name.get(0))) {
            readLocalVariableTable(in);
        } else if ("SourceFile".equals(name.get(0))) {
            readSourceFile(in, map);
        }
    }

    public static void readLocalVariableTable(InputStream in) throws IOException {
        int localVariableTableLength = ClassUtil.readInteger(in, 2);
        System.out.println("\tlocal_variable_table_length：" + localVariableTableLength);
        for (int i = 0; i < localVariableTableLength; i++) {
            int startPc = ClassUtil.readInteger(in, 2);
            System.out.println("\tstart_pc：" + startPc);
            int length = ClassUtil.readInteger(in, 2);
            System.out.println("\tlength：" + length);
            int nameIndex = ClassUtil.readInteger(in, 2);
            System.out.println("\tname_index：" + nameIndex);
            int descriptorIndex = ClassUtil.readInteger(in, 2);
            System.out.println("\tdescriptor_index：" + descriptorIndex);
            int index = ClassUtil.readInteger(in, 2);
            System.out.println("\tindex：" + index);
        }
    }

    public static void readLineNumberTable(InputStream in) throws IOException {
        int lineNumberTableLength = ClassUtil.readInteger(in, 2);
        System.out.println("\tline_number_table_length：" + lineNumberTableLength);
        for (int i = 0; i < lineNumberTableLength; i++) {
            int startPc = ClassUtil.readInteger(in, 2);
            System.out.println("\tstart_pc：" + startPc);
            int lineNumber = ClassUtil.readInteger(in, 2);
            System.out.println("\tline_number：" + lineNumber);
        }
    }

    public static void readSourceFile(InputStream in, Map<Integer, ConstantPool> map) throws IOException {
        int sourceFileIndex = ClassUtil.readInteger(in, 2);
        System.out.print("\tsourcefile_index：" + sourceFileIndex);
        List<String> name = ClassUtil.getValue(map, sourceFileIndex);
        System.out.println("：" + name);
    }

    public static void readCode(InputStream in, Map<Integer, ConstantPool> map) throws IOException {
        int maxStack = ClassUtil.readInteger(in, 2);
        System.out.println("\tmax_stack：" + maxStack);
        int maxLocals = ClassUtil.readInteger(in, 2);
        System.out.println("\tmax_locals：" + maxLocals);
        int codeLength = ClassUtil.readInteger(in, Constants.FOUR);
        System.out.println("\tcode_length：" + codeLength);
        for (int i = 0; i < codeLength; i++) {
            int code = ClassUtil.readInteger(in, 1);
            System.out.println("\t" + i + ":" + Integer.toHexString(code));
        }
        int exceptionTableLenth = ClassUtil.readInteger(in, 2);
        System.out.println("\texception_table_lenth：" + exceptionTableLenth);
        for (int i = 0; i < exceptionTableLenth; i++) {
            System.out.println(i);
        }
        int attributesCount = ClassUtil.readInteger(in, 2);
        System.out.println("\tattributes_count：" + attributesCount);
        for (int i = 0; i < attributesCount; i++) {
            parserAttribute(in, map);
        }
    }
}
