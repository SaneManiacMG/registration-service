package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.LoginRequest;
import com.reverside.registerationapp.services.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private LoginUserService loginUserService;

    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody LoginRequest request) {
        return loginUserService.loginUser(request.getEmail(), request.getPassword());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody LoginRequest request) {
        return loginUserService.changeDefaultPassword(request.getEmail(), request.getPassword());
    }
}
