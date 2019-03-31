package com.jniternals.cloud.example.authserver.vo;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgotForm {

    @Email
    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}