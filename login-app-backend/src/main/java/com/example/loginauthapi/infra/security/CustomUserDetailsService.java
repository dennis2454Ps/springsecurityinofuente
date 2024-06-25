package com.example.loginauthapi.infra.security;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
// Marcamos  esta clase como un componente gestionado por Spring.
@Component
public class CustomUserDetailsService implements UserDetailsService {

    // inyectamos el  UserRepository.
    @Autowired
    private UserRepository repository;
    @Override
    // Buscamos el usuario en el repositorio por su correo electrónico.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // Si no se encuentra en nuestra databse , lanzamos  una excepción.
        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Devuelve o retronarmos  un objeto UserDetails con el correo, la contraseña y una lista vacía de roles.
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
