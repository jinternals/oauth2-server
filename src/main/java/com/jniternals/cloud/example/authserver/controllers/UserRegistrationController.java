package com.jniternals.cloud.example.authserver.controllers;

import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import com.jniternals.cloud.example.authserver.services.UserService;
import com.jniternals.cloud.example.authserver.vo.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registerUserAccount(@Valid UserRegistrationForm userRegistrationForm){

        Optional<User> user = userService.findByEmail(userRegistrationForm.getEmail());
        if (user.isPresent()){
           // result.rejectValue("email", null, "There is already an account registered with that email");
        }

        return status(HttpStatus.CREATED).build();

    }

}