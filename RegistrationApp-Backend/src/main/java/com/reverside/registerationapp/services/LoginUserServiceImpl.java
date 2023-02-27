package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.LoginRequest;
import com.reverside.registerationapp.models.LoginResponse;
import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import com.reverside.registerationapp.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    User user;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest request) throws Exception {
//        authenticate(request.getEmail(), request.getPassword());
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
//    }
//
//    private void authenticate(String email, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

    @Override
    public ResponseEntity<Object> loginUser(String email, String password) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);

            user = userOptional.get();
            if (user.getHrVerified()){
                if (!user.getPasswordChanged()) {
                    return new ResponseEntity<>("Change default password\nhttp://localhost:8080/login/changePassword", HttpStatus.NOT_ACCEPTABLE);
                } else if (userOptional.get().getPassword().equals(password)) {
                    User savedUser = user;
                    savedUser.setLastLogin(new Date());
                    userRepository.save(savedUser);
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
        ResponseEntity<Object> response = null;
        try {
            userOptional  = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                if (userOptional.get().getHrVerified()) {
                    User user = userOptional.get();

                    user.setPassword(password);
                    user.setPasswordChanged(true);

                    try {
                        userRepository.save(user);
                        response = new ResponseEntity<>("User password updated", HttpStatus.OK);
                    } catch (Exception e) {
                        response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    response = new ResponseEntity<>("Not approved by HR", HttpStatus.UNAUTHORIZED);
                }
            }
        } catch (Exception e) {
            response = new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
