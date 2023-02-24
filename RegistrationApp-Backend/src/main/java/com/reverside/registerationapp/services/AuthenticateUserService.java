package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.AuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticateUserService {
    ResponseEntity<Object> activateAccount(AuthRequest request);
}
