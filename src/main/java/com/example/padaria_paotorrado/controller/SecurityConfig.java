package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthorizationService authorizationService;

    public SecurityConfig(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ADMIN pode acessar usuários e gerenciar produtos
                        .requestMatchers("/usuario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/produto/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("ADMIN")

                        // USER e ADMIN podem visualizar produtos
                        .requestMatchers(HttpMethod.GET, "/produto/**").hasAnyRole("USER", "ADMIN")

                        // qualquer outra rota exige estar logado
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")           // sua página customizada
                        .successHandler((request, response, authentication) -> {
                            // redireciona baseado na role
                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

                            if (isAdmin) {
                                response.sendRedirect("/usuarios.html");
                            } else {
                                response.sendRedirect("/produtos.html");
                            }
                        })
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}