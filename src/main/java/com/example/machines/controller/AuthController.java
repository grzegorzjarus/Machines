package com.example.machines.controller;

import com.example.machines.pojo.AuthenticationRequest;
import com.example.machines.pojo.AuthenticationResponse;
import com.example.machines.pojo.RegisterRequest;
import com.example.machines.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authenticationService;


    @GetMapping("/register")
    public ResponseEntity<String> saHello(){
        return ResponseEntity.ok("Hello from register GET method");
    }

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(value = "/authenticate", consumes = {"application/json"})
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){

        
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

