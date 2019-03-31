package com.jniternals.cloud.example.authserver.services.impl;

import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static java.lang.String.format;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        user.orElseThrow(()->  new UsernameNotFoundException(format("User %s can not be found",username)));

        return  buildUserDetails(user.get());

    }

    private UserDetails buildUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.isEnabled(),true,true,true,user.getAuthorities());
    }


}
