package com.milo.matium.Services.Impl;

import com.milo.matium.Base.DynamicGenerator;
import com.milo.matium.Base.MyClassLoader;
import com.milo.matium.Services.Inter.iGenerateService;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceImpl implements iGenerateService  {

    @Override
    public void generateJavaFile(String fileStr, String outPutPath) {
        DynamicGenerator.generateJavaFile(fileStr,outPutPath);
    }

    @Override
    public void generateClassFile(String classFilePath, String outPutPath) {
        DynamicGenerator.generateClassFile(classFilePath, outPutPath);
    }

    @Override
    public Class<?> loadClass(String className, String loadPath) {
        Class<?> clazz = null;
        try {
            ClassLoader classLoader = new MyClassLoader(loadPath);
            clazz = classLoader.loadClass(className);
//            System.out.println(clazz.getClassLoader()  + ":" + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
