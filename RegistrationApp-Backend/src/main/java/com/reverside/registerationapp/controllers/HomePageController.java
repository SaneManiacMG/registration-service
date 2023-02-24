package com.reverside.registerationapp.controllers;

import com.reverside.registerationapp.models.User;
import com.reverside.registerationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/home")
public class HomePageController {
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers() {
        System.out.println(new Date() + "\n/home endpoint hit");
        return userRepository.findAll();
    }
}
