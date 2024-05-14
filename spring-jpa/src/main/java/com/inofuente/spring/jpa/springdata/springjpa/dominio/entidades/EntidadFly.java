package com.inofuente.spring.jpa.springdata.springjpa.dominio.entidades;

import java.math.BigDecimal;

import com.inofuente.spring.jpa.springdata.springjpa.util.Aerolinea;

import jakarta.persistence.Column;

//JPA - Mayo Martes 14

//Los Repositorios de Spring Data se encargan de "gestionar" el Entity Manager de Hibernate.
//- CrudRepository

//Entity Manager de Hibernate: Es el objeto que se encarga de hacer toda la "gestión" de Entidades
//y Transformarlas a consultas (o a query)

import jakarta.persistence.Entity; //La nueva version de Java Enterprise Edition
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Usamos "lombok" para nuestras Entidades

@Entity(name = "fly")
@NoArgsConstructor//Las Entidades si o si tienen que tener un Constructor Vacio
                //Constructor sin Argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Data //Implementa los Getters and Setters de mis atributos
@Builder //Tenemos que evitar la sobrecarga de Constructores
public class EntidadFly {

    //Mapeo de la Entidad Fly
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le digo que este Id se incrementa por la cantidad de elementos en mi tabla
    private Long id;

    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;

    @Column(length = 20)
    private String originName;

    @Column(length = 20)
    private String destinyName;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Aerolinea aeroLine;
    //Fin del Mapeo de la Entidad Fly
}
