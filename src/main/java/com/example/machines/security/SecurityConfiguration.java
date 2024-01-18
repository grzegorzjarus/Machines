package com.example.machines.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Component
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;


    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/owner/**").permitAll()
                        .requestMatchers("/fetch-data").permitAll()
                        .requestMatchers("/demo/*").permitAll()
                        .anyRequest()
//                        .hasRole("OWNER")
                        .authenticated()
                );

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionConcurrency((sessionConcurrency) ->
                                        sessionConcurrency
                                                .maximumSessions(1)
                                                .expiredUrl("/login?expired")
                                )

                );
        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//        http
//                .authorizeHttpRequests()
//                .requestMatchers("")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//

        return http.build();
    }
}
