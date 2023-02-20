package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.LoginRequest;
import com.reverside.registerationapp.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping
    public ResponseEntity<Object> registerNewUser(@RequestBody String email) {
        return registerUserService.requestRegistration(email);
    }
}
