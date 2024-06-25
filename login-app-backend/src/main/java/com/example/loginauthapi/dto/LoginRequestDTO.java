package com.example.loginauthapi.dto;






/*almacenamos tanto el correo como la contraseña para que sean los valores
de nuestro record inmutable */
public record LoginRequestDTO (String email, String password){}

/*Record se usa normalmente para definir clases inmutables
lo que significa que no pueden cambiar
por eso es que no tienen predefinidos los setters
 pero si el resto de metodos
*/