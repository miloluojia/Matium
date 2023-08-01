package com.milo.matium.Base;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicGenerator {

//    生成.java
    public static void generateJavaFile(String fileStr, String outputPath) {
        try {
            Writer writer = new FileWriter(outputPath);
            writer.write(fileStr);
            writer.close(); // 一定要close，否则生成文件没有内容
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

//    生成.class
    public static void generateClassFile(String classFilePath, String outPutDir ){
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int complieResult = javaCompiler.run(null, null, null, "-d", outPutDir, classFilePath);
    }

//    反射调用方法
    public static void dynamicCall(Class<?> clazz, String className, String methodName){
        try {
            // class在target目录时反射可直接使用className；
            // class在其他非标准目录时，需要先用类加载器加载并传入clazz才可以使用反射，不可以直接用className
            if (clazz == null){
                clazz = Class.forName(className);
            }

            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = constructor.newInstance();

            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(obj);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {

        // TODO: 2022/11/28   Demo String替换
        String fileStr = "package com.milo.matium.TestNgClasses;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class OOMDemo_Test {\n" +
                "\n" +
                "    private OOMDemo_Test(){}\n" +
                "\n" +
                "    private static final List<Student> list = new ArrayList<>();\n" +
                "    public static void main(String[] args) {\n" +
                "        for(;;) {\n" +
                "            api();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void api() {\n" +
                "        Student stu = new Student();\n" +
                "        list.add(stu);\n" +
                "    }\n" +
                "\n" +
                "    private static class Student {\n" +
                "        private byte[] data = new byte[1024*1024];\n" +
                "    }\n" +
                "\n" +
                "    public void genarteMethod_test(){\n" +
                "        System.out.println(\"成功调用生成类的方法！！！\");\n" +
                "    }\n" +
                "\n" +
                "}";

//        String outPutPath = "src/main/java/org/example/OOMDemo_Test.java";
        String outPutPath = "src/main/java/com/milo/matium/TestNgClasses/OOMDemo_Test.java";
        generateJavaFile(fileStr, outPutPath);
//
//        String outPutDir = "/Users/zp/IdeaProjects/ApiBenchMark/target/classes/";
        String outPutDir = "/Users/milo/MatiumProject/target/classes/";
        String sourceFilePath = outPutPath;
        generateClassFile(outPutDir, sourceFilePath);

//        ClassLoader classLoader = new DynamicGeneratorClassLoader("/Users/zp/IdeaProjects/ApiBenchMark/target/classes");
//        ClassLoader classLoader = new MyClassLoader("/Users/milo/Desktop");
//        Class<?> clazz = classLoader.loadClass("org.example.OOMDemo_Test");
//        System.out.println(clazz.getClassLoader()  + ":" + clazz.getName());

        String className = "com.milo.matium.TestNgClasses.OOMDemo_Test";
        String methodName = "genarteMethod_test";
        dynamicCall(null, className, methodName);

    }
}
