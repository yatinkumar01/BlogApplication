package com.example.blogapplication.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;

import static jakarta.xml.bind.JAXBIntrospector.getValue;

public class JwtGenerator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
            String token = Jwts.builder()
                    .setIssuer("Yatin")
                    .setSubject("JWT_Token")
                    .claim("email", authentication.getName())
                    .claim("authorities", getValue(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 25000000))
                    .signWith(key).compact();
            httpServletResponse.setHeader(SecurityConstants.JWT_HEADER, token);
        } else {
            System.out.println("Error Occurred !");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
