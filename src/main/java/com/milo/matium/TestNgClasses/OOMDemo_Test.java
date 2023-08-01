package com.milo.matium.TestNgClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OOMDemo_Test {
    
    @Test
    public void genarteMethod_test(){
        System.out.println("成功调用生成类的方法！！！");
        Assert.assertEquals(1,1);
    }

}