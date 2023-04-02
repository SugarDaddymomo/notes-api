package com.ashutosh.notes.service.impl;

import com.ashutosh.notes.model.Role;
import com.ashutosh.notes.model.User;
import com.ashutosh.notes.repository.UserRepository;
import com.ashutosh.notes.requests.AuthenticationRequest;
import com.ashutosh.notes.requests.AuthenticationResponse;
import com.ashutosh.notes.requests.RegisterRequest;
import com.ashutosh.notes.security.JwtService;
import com.ashutosh.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .number(request.getNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNumber(),
                         request.getPassword()
                )
        );
        var user = userRepository.findByNumber(request.getNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}