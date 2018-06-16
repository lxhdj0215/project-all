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
import com.lxhdj.constant.Constants;
import com.lxhdj.util.ClassUtil;
import com.lxhdj.util.ConstantPoolUtil;
import com.lxhdj.util.FileUtil;

public class ReadClass {

    public void readClassTest() {
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
        byte[] bytes = FileUtil.readFileToByteArray(file);
        InputStream in = new ByteArrayInputStream(bytes);
        ClassBean classBean = new ClassBean();
        try {
            // 魔数 magic u4
            int magic = ClassUtil.readInteger(in, Constants.CONSTANT_4);
            String strMagic = "0x" + Integer.toHexString(magic).toUpperCase();
            classBean.setMagic(strMagic);
            System.out.println("魔数：" + strMagic);
            // 次版本号 minor_version u2
            int minorVersion = ClassUtil.readInteger(in, 2);
            classBean.setMinorVersion(minorVersion);
            System.out.println("次版本号：" + minorVersion);
            // 主版本号 major_version u2
            int majorVersion = ClassUtil.readInteger(in, 2);
            classBean.setMajorVersion(majorVersion);
            System.out.println("主版本号：" + majorVersion);
            // 常量池容量 constant_pool_count u2
            int constantPoolCount = ClassUtil.readInteger(in, 2);
            classBean.setConstantPoolCount(constantPoolCount);
            System.out.println("常量池容量：" + constantPoolCount);
            Map<Integer, ConstantPool> map = new HashMap<>();
            for (int i = 1; i < constantPoolCount; i++) {
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
                if (tag == Constants.CONSTANT_5 || tag == Constants.CONSTANT_6) {
                    i++;
                }
            }
            // access_flags
            int accessFlags = ClassUtil.readInteger(in, 2);
            System.out.println("access_flags：" + accessFlags);
            ClassParser.classTypeParser(accessFlags);
            // this_class
            int thisClass = ClassUtil.readInteger(in, 2);
            System.out.print("this_class：" + thisClass);
            List<String> listThisClass = ClassUtil.getValue(map, thisClass);
            System.out.println("：" + listThisClass);
            // super_class
            int superClass = ClassUtil.readInteger(in, 2);
            System.out.print("super_class：" + superClass);
            List<String> listSuperClass = ClassUtil.getValue(map, superClass);
            System.out.println("：" + listSuperClass);
            // interfaces_count
            int interfacesCount = ClassUtil.readInteger(in, 2);
            System.out.println("interfaces_count：" + interfacesCount);
            for (int i = 0; i < interfacesCount; i++) {
                // interfaces
                int interfaceIndex = ClassUtil.readInteger(in, 2);
                System.out.print("interface：" + interfaceIndex);
                List<String> listInterface = ClassUtil.getValue(map, interfaceIndex);
                System.out.println("：" + listInterface);
            }
            // fields_count
            int fieldsCount = ClassUtil.readInteger(in, 2);
            System.out.println("fields_count：" + fieldsCount);
            for (int i = 0; i < fieldsCount; i++) {
                // fields
                System.out.println("\t====================");
                FieldParser.readFields(in, map);
                System.out.println("\t====================");
            }
            // methods_count
            int methodsCount = ClassUtil.readInteger(in, 2);
            System.out.println("methods_count：" + methodsCount);
            for (int i = 0; i < methodsCount; i++) {
                // methods
                System.out.println("\t====================");
                MethodParser.readMethods(in, map);
                System.out.println("\t====================");
            }
            // attriutes_count
            int attriutesCount = ClassUtil.readInteger(in, 2);
            System.out.println("attriutes_count：" + attriutesCount);
            for (int i = 0; i < attriutesCount; i++) {
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
            case Constants.CONSTANT_3:
                ConstantPoolParser.readIntegerInfo(in);
                break;
            case Constants.CONSTANT_4:
                ConstantPoolParser.readFloatInfo(in);
                break;
            case Constants.CONSTANT_5:
                ConstantPoolParser.readLongInfo(in);
                i++;
                break;
            case Constants.CONSTANT_6:
                ConstantPoolParser.readDoubleInfo(in);
                i++;
                break;
            case Constants.CONSTANT_7:
                ConstantPoolParser.readClassInfo(in);
                break;
            case Constants.CONSTANT_8:
                ConstantPoolParser.readStringInfo(in);
                break;
            case Constants.CONSTANT_9:
                ConstantPoolParser.readFieldRefInfo(in);
                break;
            case Constants.CONSTANT_10:
                ConstantPoolParser.readMethodRefInfo(in);
                break;
            case Constants.CONSTANT_11:
                ConstantPoolParser.readInterfaceMethodRefInfo(in);
                break;
            case Constants.CONSTANT_12:
                ConstantPoolParser.readNameAndTypeInfo(in);
                break;
            default:
                break;
        }
        return i;
    }

}
