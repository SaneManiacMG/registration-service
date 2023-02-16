package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.User;
import org.springframework.http.ResponseEntity;

public interface LoginUserService {
    ResponseEntity<Object> loginUser(String email, String password);
    ResponseEntity<Object> changeDefaultPassword(String email, String password);
}
