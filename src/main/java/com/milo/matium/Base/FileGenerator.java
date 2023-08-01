package com.milo.matium.Base;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class FileGenerator
{
    public FileGenerator(String templateFile, String outPutPath, VelocityContext context)
    {
        try {
//            初始化引擎
            Velocity.init();

//            配置上下文
//            VelocityContext context = new VelocityContext();

//            加载模板
            Template template =  null;
            try {
                template = Velocity.getTemplate(templateFile);
            } catch( ResourceNotFoundException rnfe ) {
                System.out.println("FileGenerator : error : cannot find template " + templateFile );
            } catch( ParseErrorException pee ) {
                System.out.println("FileGenerator : Syntax error in template " + templateFile + ":" + pee );
            }

//            输出文件
            Writer writer = new FileWriter(outPutPath);
            if ( template != null){
                template.merge(context, writer);
            }

            writer.flush();
            writer.close();
        } catch( Exception e ) {

            System.out.println(e);

        }
    }

}