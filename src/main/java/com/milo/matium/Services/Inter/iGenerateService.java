package com.milo.matium.Services.Inter;

public interface iGenerateService {

    public void generateJavaFile(String fileStr, String outPutPath);

    public void generateClassFile(String classFilePath, String outPutPath);

    public Class<?> loadClass(String className, String loadPath);

}
