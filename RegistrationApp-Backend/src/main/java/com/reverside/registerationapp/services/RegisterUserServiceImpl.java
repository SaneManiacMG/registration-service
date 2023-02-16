package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    User newUser = new User();
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> sendEmailForRegistration(String email) {
        try {
            return new ResponseEntity<>("Email sent to HR for registration.\nlocalhost:8080/authenticate", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Email could not be sent.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> requestRegistration(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            newUser = new User(email);
            try {
                userRepository.save(newUser);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return sendEmailForRegistration(email);
        } else {
            return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
        }
    }
}
