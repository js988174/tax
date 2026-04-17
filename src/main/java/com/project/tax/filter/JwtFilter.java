package com.project.tax.filter;

import com.project.tax.common.jwt.JwtProvider;

import com.project.tax.common.jwt.UserPrincipal;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtFilter extends GenericFilter {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println("Authorization: " + req.getHeader("Authorization"));

        String path = req.getRequestURI();

        // 🔥 로그인/회원가입은 필터 패스
        if (path.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String token = resolveToken(req);

        if (token != null) {
            String email = jwtProvider.getEmail(token);

            UserPrincipal principal = new UserPrincipal(email);

            SecurityContextHolder.getContext()
                    .setAuthentication(principal.getAuthentication());
        }

        System.out.println("Authorization: " + req.getHeader("Authorization"));

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }

        return null;
    }
}
