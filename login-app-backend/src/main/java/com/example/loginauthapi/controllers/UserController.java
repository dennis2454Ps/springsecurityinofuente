package com.example.loginauthapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/* Definimos una clase de controlador que manejará las
 solicitudes relacionadas con nuestroa usuarios.*/
@RestController
//el endpoint base para las solicitudes a esta clase sera "user"
@RequestMapping("/user")

public class UserController {
    // Método para manejar las solicitudes GET a "/user".

    /* Devolveremos una respuesta HTTP 200 OK con el
    mensaje "sucesso!" en el cuerpo de la respuesta.*/
    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("sucesso!");
    }


    // Método para manejar las solicitudes GET a "/user/error".
    @GetMapping("/error")
    public ResponseEntity<String> getUserError(){

// Devuelve una respuesta HTTP 400 Bad Request con el mensaje "Error:.
        return ResponseEntity.badRequest().body("Error: errorcito");

    }


}
