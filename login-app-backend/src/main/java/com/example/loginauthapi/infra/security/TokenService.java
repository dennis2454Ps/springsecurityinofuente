package com.example.loginauthapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.loginauthapi.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Marca esta clase como un servicio gestionado por Spring.
@Service
public class TokenService {

    // Inyecta el valor de la propiedad 'api.security.token.secret' desde el archivo de configuración.
    @Value("${api.security.token.secret}")
    private String secret;

    // Genera un token JWT para el usuario.
    public String generateToken(User user){
        try {
            // Crea un algoritmo HMAC256 con la clave secreta.
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Crea el token JWT con el emisor, el sujeto (email del usuario) y la fecha de expiración.
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            // Devuelve el token generado.
            return token;
        } catch (JWTCreationException exception){
            // Lanza una excepción si ocurre un error durante la creación del token.
            throw new RuntimeException("Error while authenticating");
        }
    }

    // Valida el token JWT y devuelve el sujeto (email del usuario) si es válido.
    public String validateToken(String token){
        try {
            // Crea un algoritmo HMAC256 con la clave secreta.
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica el token y devuelve el sujeto (email del usuario) si es válido.
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // Devuelve null si la verificación del token falla.
            return null;
        }
    }

    // Genera una fecha de expiración para el token (2 horas desde el momento actual).
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
