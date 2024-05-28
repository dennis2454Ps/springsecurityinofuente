package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response;

public class LibroResponseRest extends ResponseRest{

    private LibroResponse libroResponse = new LibroResponse();

    public LibroResponse getLibroResponse() {
        return libroResponse;
    }

    public void setLibroResponse(LibroResponse libroResponse) {
        this.libroResponse = libroResponse;
    }

}