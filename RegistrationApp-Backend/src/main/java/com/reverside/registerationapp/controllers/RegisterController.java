package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.LoginRequest;
import com.reverside.registerationapp.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping
    public ResponseEntity<Object> registerNewUser(@RequestBody String email) {
        return registerUserService.requestRegistration(email);
    }
}
