package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.service;

import org.springframework.http.ResponseEntity;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Categoria;
import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response.CategoriaResponseRest;

public interface ICategoriaService {

    public ResponseEntity<CategoriaResponseRest> buscarCategorias();
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);
    public ResponseEntity<CategoriaResponseRest> eliminar (Long id);
    
}