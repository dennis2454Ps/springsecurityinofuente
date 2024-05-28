package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {


}