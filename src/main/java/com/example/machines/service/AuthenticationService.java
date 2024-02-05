package com.example.machines.service;

import com.example.machines.pojo.AuthenticationRequest;
import com.example.machines.pojo.AuthenticationResponse;
import com.example.machines.pojo.RegisterRequest;
import com.example.machines.model.Owner;
import com.example.machines.model.Renter;
import com.example.machines.model.Role;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.repository.RenterRepository;
import com.example.machines.repository.UserRepository;
import com.example.machines.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



import com.example.machines.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    @Autowired
    private final HttpSession session;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = null;

        if (("OWNER").equals(request.getRole())) {

             user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.OWNER)
                    .build();

            userRepository.save(user);

            Owner owner = Owner.builder()
                    .email(request.getEmail())
                    .name(request.getFirstName())
                    .surname(request.getLastName())
                    .phoneNumber(request.getPhoneNumber())
                    .companyName(request.getCompanyName())
                    .build();

            ownerRepository.save(owner);
        }
        else

        if (("RENTER").equals(request.getRole())) {
             user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.RENTER)
                    .build();

            userRepository.save(user);

            Renter renter = Renter.builder()
                    .email(request.getEmail())
                    .name(request.getFirstName())
                    .surname(request.getLastName())
                    .phoneNumber(request.getPhoneNumber())
                    .companyName(request.getCompanyName())
                    .build();
            System.out.println("Utworzono użytkowniak o roli RENTER "+ renter.getCompanyName());

            renterRepository.save(renter);
        }
        var jwtToken = jwtService.generateToken(user);

        //System.out.println("Users Owner from register method "+ user.getOwner().getSurname());


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest request2) {
        // HttpSession session = request2.getSession();
        session.setMaxInactiveInterval(1000*60*10);// 10 minutes
        session.setAttribute("email", request.getEmail());
       String email = (String) session.getAttribute("email");
        System.out.println("Email after login from session: "+ email);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        var user = userRepository.findUserByEmail(request.getEmail());
            //.orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));
        var jwtToken = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                . build();
    }
}
