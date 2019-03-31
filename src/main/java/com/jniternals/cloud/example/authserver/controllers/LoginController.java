package com.jniternals.cloud.example.authserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Controller
public class LoginController {

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @Autowired
    private ApprovalStore approvalStore;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping("/")
    @PreAuthorize("hasRole('OAUTH_ADMIN')")
    public ModelAndView root(Map<String,Object> model, Principal principal){

        List<Approval> approvals=clientDetailsService.listClientDetails().stream()
                .map(clientDetails -> approvalStore.getApprovals(principal.getName(),clientDetails.getClientId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        model.put("approvals",approvals);
        model.put("clientDetails",clientDetailsService.listClientDetails());
        return new ModelAndView ("index",model);

    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value="/approval/revoke")
    public String revokeApproval(@ModelAttribute Approval approval){

        approvalStore.revokeApprovals(asList(approval));

        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
                .forEach(tokenStore::removeAccessToken);

        return "redirect:/";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (nonNull(authentication)){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login?logout";}

}
