package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response;

import java.util.List;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Libro;

public class LibroResponse {
    private List<Libro> libro;

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }    
}