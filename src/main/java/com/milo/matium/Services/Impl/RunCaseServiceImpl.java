package com.milo.matium.Services.Impl;

import com.milo.matium.Services.Inter.iRunCaseService;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunCaseServiceImpl implements iRunCaseService {

    @Override
    public void runTestNg() {
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("src/main/java/com/milo/matium/testng_test.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }

    public static void main(String[] args) {
        RunCaseServiceImpl impl = new RunCaseServiceImpl();
        impl.runTestNg();
    }
}
