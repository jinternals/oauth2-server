package com.jniternals.cloud.example.authserver.services;

import com.jniternals.cloud.example.authserver.vo.Mail;

public interface EmailService {

    void sendEmail(Mail mail);

}
