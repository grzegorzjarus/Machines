package com.example.machines;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String testMethod(){
        return "Test";
    }

}
