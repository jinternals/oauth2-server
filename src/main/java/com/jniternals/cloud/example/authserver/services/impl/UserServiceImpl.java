package com.jniternals.cloud.example.authserver.services.impl;

import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import com.jniternals.cloud.example.authserver.services.UserService;
import com.jniternals.cloud.example.authserver.vo.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserRegistrationForm registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        //user.setAuthorities(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(String updatedPassword, User user) {
        user.setPassword(updatedPassword);
        return userRepository.save(user);
    }
}
