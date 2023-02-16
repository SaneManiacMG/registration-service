package com.reverside.registerationapp.services;

import org.springframework.http.ResponseEntity;

public interface AuthenticateUserService {
    ResponseEntity<Object> activateAccount(String email);
}
