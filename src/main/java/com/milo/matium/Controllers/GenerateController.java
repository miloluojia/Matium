package com.milo.matium.Controllers;

import com.milo.matium.Base.DynamicGenerator;
import com.milo.matium.Base.FileGenerator;
import com.milo.matium.Services.Inter.iGenerateService;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GenerateController {

    @Autowired
    iGenerateService generateService;

    @RequestMapping("/generate")
    public boolean generate(){
        // TODO: 2022/11/28   Demo String根据入参替换成testng格式
        String fileStr = "package com.milo.matium.TestNgClasses;\n" +
                "\n" +
                "import org.testng.Assert;\n" +
                "import org.testng.annotations.Test;\n" +
                "\n" +
                "public class OOMDemo_Test {\n" +
                "    \n" +
                "    @Test\n" +
                "    public void genarteMethod_test(){\n" +
                "        System.out.println(\"成功调用生成类的方法！！！\");\n" +
                "        Assert.assertEquals(1,1);\n" +
                "    }\n" +
                "\n" +
                "}";

        String outPutPath = "src/main/java/com/milo/matium/TestNgClasses/OOMDemo_Test.java";
        generateService.generateJavaFile(fileStr, outPutPath);

        String outPutDir = "/Users/milo/MatiumProject/target/classes/";
        String sourceFilePath = outPutPath;
        generateService.generateClassFile(sourceFilePath, outPutDir);

//        String className = "com.milo.matium.TestNgClasses.OOMDemo_Test";
//        String methodName = "genarteMethod_test";
//        DynamicGenerator.dynamicCall(null, className, methodName);

//        修改xml
        VelocityContext context = new VelocityContext();
        context.put("classes", getNames());
        FileGenerator fileGenerator = new FileGenerator("src/main/java/com/milo/matium/testng.vm","src/main/java/com/milo/matium/testng_test.xml", context);

        return true;
    }

    // TODO: 12/12/22 待换成根据入参调整的逻辑
    public ArrayList getNames()
    {
        ArrayList list = new ArrayList();
        list.add("com.milo.matium.TestNgClasses.OOMDemo_Test");
//        list.add("org.example.Test1");
//        list.add("org.example.Test2");

        return list;
    }
}
