package com.project.tax.user.service;

import com.project.tax.common.jwt.JwtProvider;
import com.project.tax.user.entity.db.UserEntity;
import com.project.tax.user.entity.req.LoginReqEntity;
import com.project.tax.user.entity.req.SignupReqEntity;
import com.project.tax.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupReqEntity req) {
        UserEntity user = UserEntity.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);
    }

    public String login(LoginReqEntity req) {

        UserEntity user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        return jwtProvider.createToken(user.getEmail());
    }

}
