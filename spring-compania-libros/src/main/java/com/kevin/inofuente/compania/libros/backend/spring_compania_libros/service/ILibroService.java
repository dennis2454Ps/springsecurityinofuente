package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.service;

import org.springframework.http.ResponseEntity;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Libro;
import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response.LibroResponseRest;

public interface ILibroService {

    public ResponseEntity<LibroResponseRest> buscarLibros();
    public ResponseEntity<LibroResponseRest> buscarPorId(Long id);
    public ResponseEntity<LibroResponseRest> crear(Libro libro);
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id);
    public ResponseEntity<LibroResponseRest> eliminar (Long id);

}