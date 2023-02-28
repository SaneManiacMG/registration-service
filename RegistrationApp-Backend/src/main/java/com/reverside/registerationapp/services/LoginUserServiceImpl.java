package com.reverside.registerationapp.services;

import com.reverside.registerationapp.models.HttpResponse;
import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private HttpResponse response = new HttpResponse();

    @Override
    public ResponseEntity<Object> loginUser(String email, String password) {

        try {
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (user.getHrVerified()) {
                    if (!user.getPasswordChanged()) {
                        response.setMessage("Change default password");
                        response.setUser(user);
                    } else if (userOptional.get().getPassword().equals(password)) {
                        user.setLastLogin(new Date());
                        userRepository.save(user);
                        response.setMessage("Logged in successfully");
                        response.setUser(user);
                    }
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.setMessage("HR needs to activate");
                    response.setUser(user);
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
            } else {
                response.setMessage("User not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage(e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> changeDefaultPassword(String email, String password) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                if (userOptional.get().getHrVerified()) {
                    user = userOptional.get();

                    user.setPassword(password);
                    user.setPasswordChanged(true);

                    try {
                        userRepository.save(user);
                        response.setMessage("User password updated");
                        response.setUser(user);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } catch (Exception e) {
                        response.setMessage(e.toString());
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    response.setMessage("Not approved by HR");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
            } else {
                response.setMessage("User not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage(e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
