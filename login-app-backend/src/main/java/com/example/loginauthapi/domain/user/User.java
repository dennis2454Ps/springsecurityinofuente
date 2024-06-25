package com.example.loginauthapi.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Esta clase representa nuesta  entidad JPA
// que se mapeará con ayuda deeee ORM (OBJET RELATIONAL MAPPING)
// a una tabla llamada "users" en la base de datos.
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // MarcaMOS el campo "id" como la clave primaria de la entidad y especifica que su valor
    // se generará automáticamente Y sera incremental


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    //definimos el cuerpo de nuestra entidad y todos los valores que vamos a necesitar
    private String id;
    private String name;
    private String email;
    private String password;
}
