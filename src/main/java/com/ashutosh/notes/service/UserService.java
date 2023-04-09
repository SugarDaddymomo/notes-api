package com.ashutosh.notes.service;


import com.ashutosh.notes.requests.AuthenticationRequest;
import com.ashutosh.notes.responses.AuthenticationResponse;
import com.ashutosh.notes.requests.RegisterRequest;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}