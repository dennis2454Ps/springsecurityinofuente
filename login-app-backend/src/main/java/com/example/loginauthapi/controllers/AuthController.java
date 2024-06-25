package com.example.loginauthapi.controllers;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.dto.LoginRequestDTO;
import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/*Definimos esta clase como un controlador que maneje las solicitudes
establecemos el endPoint base para todas las solicitudes"auth".
iNJECTAMOS DEPENDENCIAS DE LA FORMA TRADICIONAL"
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // DECLARAMOS NUESTRAS TRES DEPENDENCIAS QUE INJECTAMOS A TRAVEZ
    // DE LA ANTERIOR ANOTACION "@RequiredArgsConstructor"
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    /*Primer metodo post para manejar la solicitud hacia la ruta "auth/login"*/
    @PostMapping("/login")
    /*funcion para el manejo del login
     para la busqueda del usuario en la database usamos su email.
     y lo consultamos con ayuda de Jpa*/
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
       /*Creamos una condicional para validar al usuario, si la contraseña
        ingresada por el usuario coincide con la extraimos del repositorio
         generamos un token de autenticacion */
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            //devolvemos una respuesta http de 200 junto al nombre y al token
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        //devuelve 400 osea que la contraseña es incorrecta
        return ResponseEntity.badRequest().build();
    }

    // segundo metodo pero  para manejar las solicitudes POST a "/auth/register".
    @PostMapping("/register")
    //funcion con la que vamos a registrar a nuestro usario,
    //usamos response entity para el manejo de las solicitudfes http


    // Se espera que el cuerpo de la solicitud contenga
    // los datos de registro en formato jjson (RegisterRequestDTO)
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){


// Buscamos  un usuario por su correo electrónico para verificar si ya está
// registrado en nuestra database...
        Optional<User> user = this.repository.findByEmail(body.email());

// Si el usuario no existe, procede a registrarlo.
        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);
// de igual manera Generamos un token de autenticación para el nuevo usuario.
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
 // Si el usuario ya existe, devuelve una respuesta HTTP 400 .
        return ResponseEntity.badRequest().build();
    }
}
