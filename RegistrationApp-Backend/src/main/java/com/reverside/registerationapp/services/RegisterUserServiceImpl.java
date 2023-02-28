package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.HttpResponse;
import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> sendEmailForRegistration(String email) {
        HttpResponse response = new HttpResponse();
        User user = new User();
        try {
            response.setMessage("User " + email + " recorded, please contact HR");
            response.setUser(user);
        } catch (Exception e) {
            response.setMessage("Could not save new user");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> requestRegistration(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        HttpResponse response = new HttpResponse();
        User user = new User(email);
        if (userOptional.isEmpty()) {
            try {
                userRepository.save(user);
            } catch (Exception e) {
                response.setMessage(e.toString());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return sendEmailForRegistration(email);
        } else {
            response.setMessage("User already exists.");
            response.setUser(user);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
