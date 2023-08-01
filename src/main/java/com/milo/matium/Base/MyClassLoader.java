package com.milo.matium.Base;

import java.io.*;

//生成字节码保存在target目录时，不需要使用类加载器加载，是因为jvm能按需在target目录加载class；在其他目录时，则需要使用类加载器
public class MyClassLoader extends ClassLoader{

    private String rootDir;

    public MyClassLoader(String rootDir){
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String path = rootDir + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        byte[] byteCode = null;
        try{
            InputStream in = new FileInputStream(path);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int readSize = 0;
            while ((readSize = in.read(buffer)) != -1){
                byteStream.write(buffer, 0, readSize);
            }
            byteCode = byteStream.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (byteCode == null){
            throw new ClassNotFoundException("class name:" + name);
        }else {
            return defineClass(name, byteCode, 0, byteCode.length);
        }

    }
}
