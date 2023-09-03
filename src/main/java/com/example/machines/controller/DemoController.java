package com.example.machines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@CrossOrigin(originPatterns = "*")
public class DemoController {

    @Secured("OWNER")
    @GetMapping("/demo-owner")
    public ResponseEntity<String> saHello(){
       return ResponseEntity.ok("Hello owner from secured endpoint");
    }

    @Secured("USER")
    @GetMapping("/demo-user")
    public ResponseEntity<String> saHelloToUser(){
        return ResponseEntity.ok("Hello user from secured endpoint");
    }
}
