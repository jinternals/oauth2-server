package com.jniternals.cloud.example.authserver.controllers;

import com.jniternals.cloud.example.authserver.configuration.AuthServerProperties;
import com.jniternals.cloud.example.authserver.domain.PasswordResetToken;
import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.PasswordResetTokenRepository;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import com.jniternals.cloud.example.authserver.services.EmailService;
import com.jniternals.cloud.example.authserver.services.LocalDateTimeService;
import com.jniternals.cloud.example.authserver.vo.Mail;
import com.jniternals.cloud.example.authserver.vo.PasswordForgotForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    private UserRepository userRepository;
    private PasswordResetTokenRepository tokenRepository;
    private EmailService emailService;
    private LocalDateTimeService  localDateTimeService;
    private AuthServerProperties authServerProperties;

    @Autowired
    public PasswordForgotController(UserRepository userRepository,
                                    PasswordResetTokenRepository tokenRepository,
                                    EmailService emailService,
                                    LocalDateTimeService  localDateTimeService,
                                    AuthServerProperties authServerProperties){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.localDateTimeService = localDateTimeService;
        this.authServerProperties = authServerProperties;
    }


    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotForm forgotPasswordDto() {
        return new PasswordForgotForm();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotForm form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        Optional<User> user = userRepository.findByEmail(form.getEmail());

        if (!user.isPresent()){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user.get());
        token.setExpiryDateTime(localDateTimeService.current().plusHours(authServerProperties.getResetPasswordTokenExpiryHours()));
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(user.get().getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user.get());
        model.put("signature", "https://localhost:9000/");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }

}
