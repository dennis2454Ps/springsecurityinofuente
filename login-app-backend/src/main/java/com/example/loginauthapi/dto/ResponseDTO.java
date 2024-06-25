package com.example.loginauthapi.dto;
/*
taambien necesitamos devolver el usuario y el token como una respuesta
tanto para el register y el login
ambos valores para nuestra clase inmutable*/


public record ResponseDTO (String name, String token) { }
