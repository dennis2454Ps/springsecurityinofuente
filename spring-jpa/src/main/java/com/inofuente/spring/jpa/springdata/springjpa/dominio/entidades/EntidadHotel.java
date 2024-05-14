package com.inofuente.spring.jpa.springdata.springjpa.dominio.entidades;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel")
@NoArgsConstructor//Las Entidades si o si tienen que tener un Constructor Vacio
                //Constructor sin Argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Data //Implementa los Getters and Setters de mis atributos
@Builder //Tenemos que evitar la sobrecarga de Constructores
public class EntidadHotel {

    @Id
    @GeneratedValue(strategy = )
}
