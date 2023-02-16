package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.LoginRequest;
import com.reverside.registerationapp.services.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginUserService loginUserService;

    @PostMapping
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest request) {
        return loginUserService.loginUser(request.getEmail(), request.getPassword());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody LoginRequest request) {
        return loginUserService.changeDefaultPassword(request.getEmail(), request.getPassword());
    }
}
