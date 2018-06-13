package com.lxhdj.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.lxhdj.bean.ConstantPool;
import com.lxhdj.util.ClassUtil;

public class MethodParser {
    public static void readMethods(InputStream in, Map<Integer, ConstantPool> map) throws IOException {
        int accessFlags = ClassUtil.readInteger(in, 2);
        int nameIndex = ClassUtil.readInteger(in, 2);
        int descriptorIndex = ClassUtil.readInteger(in, 2);
        System.out.println("\taccess_flags：" + accessFlags);
        System.out.print("\tname_index：" + nameIndex);
        List<String> name = ClassUtil.getValue(map, nameIndex);
        System.out.println("：" + name);
        System.out.print("\tdescriptor：" + descriptorIndex);
        List<String> descriptor = ClassUtil.getValue(map, descriptorIndex);
        System.out.println("：" + descriptor);
        int attributesCount = ClassUtil.readInteger(in, 2);
        System.out.println("\tattributes_count：" + attributesCount);
        for (int i = 0; i < attributesCount; i++) {
            AttributeParser.parserAttribute(in, map);
        }
    }

}
