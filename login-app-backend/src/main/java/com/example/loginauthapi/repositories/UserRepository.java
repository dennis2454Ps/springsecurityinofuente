package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Define un repositorio de Spring Data JPA para la entidad User.
public interface UserRepository extends JpaRepository<User, String> {


    // Declara un método para buscar un usuario por su correo electrónico

    //este metodo personalizado nnos perm,ite hacer una consulta especfifica
    //sobre la entidad user
    Optional<User> findByEmail(String email);
}
