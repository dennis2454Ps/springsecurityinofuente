package com.example.loginauthapi.dto;


/*Para nuestro registro igual la utilizamos para
definir esta clae como inmutable y
alamcenar nuestra informacion requerida para el registro*/
public record RegisterRequestDTO (String name, String email, String password) {
}
