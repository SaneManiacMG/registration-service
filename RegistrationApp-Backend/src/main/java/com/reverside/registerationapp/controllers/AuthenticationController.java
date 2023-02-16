package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.services.AuthenticateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticateUserService authenticateUserService;

    @PostMapping("/activate")
    public ResponseEntity<Object> activateUser(@RequestBody String email) {
        return authenticateUserService.activateAccount(email);
    }
}
