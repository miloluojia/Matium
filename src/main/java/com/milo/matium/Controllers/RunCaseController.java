package com.milo.matium.Controllers;

import com.milo.matium.Services.Inter.iRunCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunCaseController {

    @Autowired
    iRunCaseService runCaseService;

    @RequestMapping("/runtestng")
    public boolean runTestNg(){
        runCaseService.runTestNg();
        return true;
    }

}
