package com.example.machines;

import com.example.machines.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.*;


@Controller

public class TestController {

//
//    private final OwnerRepository ownerRepository;
//
//    @Autowired
//    public TestController(OwnerRepository ownerRepository) {
//        this.ownerRepository = ownerRepository;
//    }

    @GetMapping("/")
    @ResponseBody
    public String testMethod() {

        return "Test";
    }

}
