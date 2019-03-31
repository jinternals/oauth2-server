package com.jniternals.cloud.example.authserver.services.impl;

import com.jniternals.cloud.example.authserver.services.LocalDateTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocalDateTimeServiceImpl implements LocalDateTimeService {

    @Override
    public LocalDateTime current() {
        return LocalDateTime.now();
    }

}
