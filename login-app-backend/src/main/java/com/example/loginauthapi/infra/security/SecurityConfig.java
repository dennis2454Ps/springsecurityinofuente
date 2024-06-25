package com.example.loginauthapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Indica que esta es una clase de configuración para Spring.
@Configuration
// Habilita la seguridad web de Spring.
@EnableWebSecurity
public class SecurityConfig {

    // Inyecta el servicio personalizado de detalles de usuario.
    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Inyecta el filtro de seguridad personalizado.
    @Autowired
    SecurityFilter securityFilter;

    // Define la cadena de filtros de seguridad.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita la protección CSRF (Cross-Site Request Forgery).
                .csrf(csrf -> csrf.disable())
                // Configura la política de creación de sesiones como sin estado (stateless).
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura las reglas de autorización para las solicitudes HTTP.
                .authorizeHttpRequests(authorize -> authorize
                        // Permite el acceso sin autenticación a las solicitudes POST a /auth/login.
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Permite el acceso sin autenticación a las solicitudes POST a /auth/register.
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // Requiere autenticación para cualquier otra solicitud.
                        .anyRequest().authenticated()
                )
                // Añade el filtro de seguridad personalizado antes del filtro de autenticación de usuario y contraseña.
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        // Construye y devuelve la configuración de seguridad.
        return http.build();
    }

    // Define un bean para el codificador de contraseñas utilizando BCrypt.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define un bean para el gestor de autenticación.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
