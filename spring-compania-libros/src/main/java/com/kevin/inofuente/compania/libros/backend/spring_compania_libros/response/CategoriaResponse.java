package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response;

import java.util.List;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Categoria;

public class CategoriaResponse {

    private List<Categoria> categoria;

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }
}