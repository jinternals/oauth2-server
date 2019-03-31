package com.jniternals.cloud.example.authserver.controllers;


import com.jniternals.cloud.example.authserver.domain.PasswordResetToken;
import com.jniternals.cloud.example.authserver.domain.User;
import com.jniternals.cloud.example.authserver.repository.PasswordResetTokenRepository;
import com.jniternals.cloud.example.authserver.repository.UserRepository;
import com.jniternals.cloud.example.authserver.services.UserService;
import com.jniternals.cloud.example.authserver.vo.passwordResetForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
    @RequestMapping("/reset-password")
    public class PasswordResetController {

        @Autowired private UserService userService;
        @Autowired private PasswordResetTokenRepository tokenRepository;
        @Autowired private PasswordEncoder passwordEncoder;




        @ModelAttribute("passwordResetForm")
        public passwordResetForm passwordReset() {
            return new passwordResetForm();
        }

        @GetMapping
        public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                               Model model) {

            Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);

            if (!resetToken.isPresent()){
                model.addAttribute("error", "Could not find password reset token.");
            } else if (resetToken.get().isExpired()){
                model.addAttribute("error", "Token has expired, please request a new password reset.");
            } else {
                model.addAttribute("token", resetToken.get().getToken());
            }

            return "reset-password";
        }

        @PostMapping
        @Transactional
        public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid passwordResetForm form,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes) {

            if (result.hasErrors()){
                redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
                redirectAttributes.addFlashAttribute("passwordResetForm", form);
                return "redirect:/reset-password?token=" + form.getToken();
            }

            Optional<PasswordResetToken>  token = tokenRepository.findByToken(form.getToken());

            token.ifPresent(passwordResetToken -> {
                User user = passwordResetToken.getUser();
                String updatedPassword = passwordEncoder.encode(form.getPassword());
                userService.updatePassword(updatedPassword,user);
                tokenRepository.delete(passwordResetToken);
            });

            return "redirect:/login?resetSuccess";
        }

    }