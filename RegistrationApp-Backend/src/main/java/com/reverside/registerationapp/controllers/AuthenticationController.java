package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.AuthRequest;
import com.reverside.registerationapp.services.AuthenticateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticateUserService authenticateUserService;

    @PostMapping
    public ResponseEntity<Object> activateUser(@RequestBody AuthRequest request) {
        return authenticateUserService.activateAccount(request);
    }
}
