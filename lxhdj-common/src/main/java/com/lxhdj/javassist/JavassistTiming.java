package com.lxhdj.javassist;

import javassist.*;
import javassist.bytecode.ConstPool;

/**
 * Created by wangguijun1 on 2018/7/4.
 */
public class JavassistTiming {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("java.util.GregorianCalendar");
            ConstPool constPool = ctClass.getClassFile2().getConstPool();
            int size = constPool.getSize();
            System.out.println(size);
            int majorVersion = ctClass.getClassFile().getMajorVersion();
            System.out.println(majorVersion);
            System.out.println(constPool.getClassName());
            System.out.println(constPool.getIntegerInfo(1));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 为方法添加拦截器:
     * <pre>
     *     构造拦截器方法的正文时使用一个 java.lang.StringBuffer 来累积正文文本(这显示了处理 String 的构造的正确方法，
     *     与在 StringBuilder 的构造中使用的方法是相对的)。这种变化取决于原来的方法是否有返回值。
     *     如果它 有返回值，那么构造的代码就将这个值保存在局部变量中，这样在拦截器方法结束时就可以返回它。
     *     如果原来的方法类型为 void ，那么就什么也不需要保存，也不用在拦截器方法中返回任何内容。
     * </pre>
     *
     * @param clazz  方法所属的类
     * @param method 方法名称
     */
    private static void addTiming(CtClass clazz, String method) throws NotFoundException, CannotCompileException {

        //获取方法信息,如果方法不存在，则抛出异常
        CtMethod ctMethod = clazz.getDeclaredMethod(method);
        //将旧的方法名称进行重新命名，并生成一个方法的副本，该副本方法采用了过滤器的方式
        String nname = method + "$impl";
        ctMethod.setName(nname);
        CtMethod newCtMethod = CtNewMethod.copy(ctMethod, method, clazz, null);

        /*
         * 为该方法添加时间过滤器，用来计算时间。
         * 这就需要我们去判断获取时间的方法是否具有返回值
         */
        String type = ctMethod.getReturnType().getName();
        StringBuffer body = new StringBuffer();
        body.append("{\n long start = System.currentTimeMillis();\n");

        if (!"void".equals(type)) {
            body.append(type + " result = ");
        }

        //可以通过$$将传递给拦截器的参数，传递给原来的方法
        body.append(nname + "($$);\n");

        //  finish body text generation with call to print the timing
        //  information, and return saved value (if not void)
        body.append("System.out.println(\"Call to method " + nname + " took \" + \n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
        if (!"void".equals(type)) {
            body.append("return result;\n");
        }

        body.append("}");

        //替换拦截器方法的主体内容，并将该方法添加到class之中
        newCtMethod.setBody(body.toString());
        clazz.addMethod(newCtMethod);

        //输出拦截器的代码块
        System.out.println("拦截器方法的主体:");
        System.out.println(body.toString());
    }

}
