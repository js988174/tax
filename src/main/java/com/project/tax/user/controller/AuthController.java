package com.project.tax.user.controller;

import com.project.tax.user.entity.req.LoginReqEntity;
import com.project.tax.user.entity.req.SignupReqEntity;
import com.project.tax.user.entity.res.AuthResEntity;
import com.project.tax.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupReqEntity req) {
        service.signup(req);
    }

    @PostMapping("/login")
    public AuthResEntity login(@RequestBody LoginReqEntity req) {
        String token = service.login(req);

        return AuthResEntity.builder()
                .accessToken(token)
                .build();
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        return authentication.getName(); // 이메일
    }

}
