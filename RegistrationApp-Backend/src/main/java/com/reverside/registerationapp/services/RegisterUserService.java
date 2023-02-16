package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.User;
import org.springframework.http.ResponseEntity;

public interface RegisterUserService {
    ResponseEntity<Object> sendEmailForRegistration(String email);
    ResponseEntity<Object> requestRegistration(String email);
}
