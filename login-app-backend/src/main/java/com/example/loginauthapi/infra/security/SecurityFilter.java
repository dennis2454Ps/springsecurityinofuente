package com.example.loginauthapi.infra.security;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// Marca esta clase como un componente gestionado por Spring.
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Inyecta el servicio de tokens.
    @Autowired
    TokenService tokenService;

    // Inyecta el repositorio de usuarios.
    @Autowired
    UserRepository userRepository;

    // Sobrescribe el método para filtrar cada solicitud HTTP.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// Recupera el token de la solicitud.
        var token = this.recoverToken(request);

// Valida el token.
        var login = tokenService.validateToken(token);

// Si el token es válido, configura la autenticación.
        if(login != null){
 // Busca el usuario por su correo electrónico.
            User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User Not Found"));

// Crea una lista de roles (en este caso, solo ROLE_USER).
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

            // Crea un objeto de autenticación con el usuario y los roles.
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

            // Establece la autenticación en el contexto de seguridad.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continúa con el siguiente filtro en la cadena.
        filterChain.doFilter(request, response);
    }

    // Método para recuperar el token de la solicitud HTTP.
    private String recoverToken(HttpServletRequest request){
        // Obtiene el encabezado de autorización de la solicitud.
        var authHeader = request.getHeader("Authorization");

        // Si el encabezado es nulo, devuelve null.
        if(authHeader == null) return null;

  // Devuelve el token sin el prefijo "Bearer ".
        return authHeader.replace("Bearer ", "");
    }
}
