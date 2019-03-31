package com.jniternals.cloud.example.authserver.services;


import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.vo.UserRegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User save(UserRegistrationForm registration);

    User updatePassword(String updatedPassword,User user);

}