package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.AuthRequest;
import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationUserServiceImpl implements AuthenticateUserService {
    User user;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> activateAccount(AuthRequest request) {
        if (request.getActivate()) {
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

            if (userOptional.isPresent()) {
                user = userOptional.get();
                user.setHrVerified(true);
                try {
                    userRepository.save(user);
                    return new ResponseEntity<>("Account activated and email sent to user\n" +
                            "http://localhost:8080/login", HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>("An error occurred sending email", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("User email not found\n" + userRepository.findAll(), HttpStatus.NOT_FOUND);
            }
        } else
            return new ResponseEntity<>("Request Rejected", HttpStatus.OK);

    }

}
