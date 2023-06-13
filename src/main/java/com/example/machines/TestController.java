package com.example.machines;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String testMethod(){
        return "Test";
    }

}
