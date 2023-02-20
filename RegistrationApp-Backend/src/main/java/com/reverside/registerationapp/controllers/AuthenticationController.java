package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.services.AuthenticateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    @Autowired
    private AuthenticateUserService authenticateUserService;

    @PostMapping("/activate")
    public ResponseEntity<Object> activateUser(@RequestBody String email) {
        return authenticateUserService.activateAccount(email);
    }
}
