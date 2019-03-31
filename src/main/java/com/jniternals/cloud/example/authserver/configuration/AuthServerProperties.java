package com.jniternals.cloud.example.authserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jinternals.authserver")
public class AuthServerProperties {

    private Integer resetPasswordTokenExpiryHours = 2;

    public Integer getResetPasswordTokenExpiryHours() {
        return resetPasswordTokenExpiryHours;
    }

    public void setResetPasswordTokenExpiryTime(Integer resetPasswordTokenExpiryHours) {
        this.resetPasswordTokenExpiryHours = resetPasswordTokenExpiryHours;
    }
}
