package com.example.blogapplication.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configuration(HttpSecurity http) throws Exception {

        http.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                cors(cors -> {
                    cors.configurationSource(new org.springframework.web.cors.CorsConfigurationSource() {

                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                            configuration.setAllowedMethods(Collections.singletonList("*"));
                            configuration.setAllowCredentials(true);
                            configuration.setAllowedHeaders(Collections.singletonList("*"));
                            configuration.setExposedHeaders(Arrays.asList("Authorization"));
                            return configuration;
                        }
                    });
                }).authorizeHttpRequests(auth -> auth.requestMatchers(
                                HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/users/login").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/posts/getAll").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/posts/today").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/users/getUsers/{email}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/posts/getAllByCategoryName/{name}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/comments/create_comment/{userId}/{postId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/comments/getComments/{postId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/comments/update_comment/{commentId}").hasAnyRole("ADMIN", "USER")
                        .anyRequest().hasRole("ADMIN"))
                .csrf(csrf -> csrf.disable())
                .addFilterAfter(new JwtGenerator(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
