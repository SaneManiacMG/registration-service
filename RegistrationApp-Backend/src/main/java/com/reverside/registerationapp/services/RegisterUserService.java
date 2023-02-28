package com.reverside.registerationapp.services;

import org.springframework.http.ResponseEntity;

public interface RegisterUserService {
    ResponseEntity<Object> sendEmailForRegistration(String email);
    ResponseEntity<Object> requestRegistration(String email);
}
