package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    User user;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> loginUser(String email, String password) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);

            user = userOptional.get();
            if (user.getHrVerified()){
                if (!user.getPasswordChanged()) {
                    return new ResponseEntity<>("Change default password\nhttp://localhost:8080/login/changePassword", HttpStatus.NOT_ACCEPTABLE);
                } else if (userOptional.get().getPassword().equals(password)) {
                    return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>("HR needs to activate", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> changeDefaultPassword(String email, String password) {
        Optional<User> userOptional;
        try {
            userOptional  = userRepository.findByEmail(email);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (userOptional.get().getHrVerified()) {
            User user = userOptional.get();

            user.setPassword(password);
            user.setPasswordChanged(true);

            try {
                userRepository.save(user);
                return new ResponseEntity<>("User password updated", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Not approved by HR", HttpStatus.UNAUTHORIZED);
        }
    }
}
