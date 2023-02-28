package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.AuthRequest;
import com.reverside.registerationapp.models.HttpResponse;
import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationUserServiceImpl implements AuthenticateUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> activateAccount(AuthRequest request) {
        User user;
        HttpResponse response = new HttpResponse();
        if (request.getActivate()) {
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

            if (userOptional.isPresent()) {
                user = userOptional.get();
                user.setHrVerified(request.getActivate());
                try {
                    userRepository.save(user);
                    response.setMessage("Account activated and email sent to user");
                    response.setUser(user);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (Exception e) {
                    response.setMessage(e.toString());
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                response.setMessage("User email not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } else {
            response.setMessage("Request Rejected");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
