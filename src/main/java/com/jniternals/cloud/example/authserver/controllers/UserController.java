package com.jniternals.cloud.example.authserver.controllers;


import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import com.jniternals.cloud.example.authserver.vo.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public void createUser(RegisterUser user){
        userRepository.save(new User());
    }

}
