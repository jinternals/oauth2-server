package com.jniternals.cloud.example.authserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
