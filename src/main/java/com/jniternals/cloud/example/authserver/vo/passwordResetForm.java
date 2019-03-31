package com.jniternals.cloud.example.authserver.vo;

import javax.validation.constraints.NotEmpty;

public class passwordResetForm {

    @NotEmpty
    private String password;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
