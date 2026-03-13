package com.smart.sotral.Shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import com.smart.sotral.Shared.constants.JavaConstant;
import com.smart.sotral.Shared.jwt.filters.JwtAuthorizationToken;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthorizationToken jwtAuthorizationToken
    ) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/api/auth/**",
                        "/api/capteurs/position",
                        "/api/users/login",
                        "/api/users/register",
                        "/api/users/register/usager"
                ).permitAll()
                // Création d'admin rendue publique
                .requestMatchers(JavaConstant.ADMIN_ONLY_URLS).permitAll()
                .requestMatchers(JavaConstant.PUBLIC_URLS).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/capteurs/position").permitAll()
                // Conducteur ou Admin peuvent mettre à jour l'état des bus (retard, panne, etc.)
                .requestMatchers(HttpMethod.PUT, "/api/bus/**").hasAnyRole("ADMIN", "CONDUCTEUR", "USAGER")
                .requestMatchers(HttpMethod.PATCH, "/api/bus/**").hasAnyRole("ADMIN", "CONDUCTEUR", "USAGER")
                .requestMatchers(HttpMethod.POST, "/api/bus/**").hasAnyRole("ADMIN", "CONDUCTEUR", "USAGER")
                .requestMatchers(HttpMethod.GET,
                        "/api/arrets/**",
                        "/api/bus/**",
                        "/api/vehicules/**",
                        "/api/lignes/**",
                        "/api/ligne-arrets/**",
                        "/api/capteurs/dernieres/**",
                        "/api/types-ligne/**",
                        "/api/predictions/**",
                        "/api/users/**"
                ).permitAll()
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USAGER", "CONDUCTEUR")
                .requestMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        http.addFilterBefore(jwtAuthorizationToken, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
