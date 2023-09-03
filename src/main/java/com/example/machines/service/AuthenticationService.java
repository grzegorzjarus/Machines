package com.example.machines.service;

import com.example.machines.controller.AuthenticationRequest;
import com.example.machines.controller.AuthenticationResponse;
import com.example.machines.controller.RegisterRequest;
import com.example.machines.exception.UserNotFoundException;
import com.example.machines.model.Role;
import com.example.machines.repository.UserRepository;
import com.example.machines.security.JwtService;
import lombok.RequiredArgsConstructor;

import com.example.machines.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                . build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                . build();
    }
}
