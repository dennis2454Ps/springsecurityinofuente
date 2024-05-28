package com.kevin.inofuente.compania.libros.backend.spring_compania_libros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.model.Libro;
import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.response.LibroResponseRest;
import com.kevin.inofuente.compania.libros.backend.spring_compania_libros.service.ILibroService;

@RestController
@RequestMapping("/v1")
public class LibroRestController {

    @Autowired
    private ILibroService service;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> consultaCat() {
        ResponseEntity<LibroResponseRest> response = service.buscarLibros();
        return response;
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultaPorId(@PathVariable Long id) {
        ResponseEntity<LibroResponseRest> response = service.buscarPorId(id);
        return response;
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crear(@RequestBody Libro request) {
        ResponseEntity<LibroResponseRest> response = service.crear(request);
        return response;
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> actualizar(@RequestBody Libro request, @PathVariable Long id) {
        ResponseEntity<LibroResponseRest> response = service.actualizar(request, id);
        return response;
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> eliminar(@PathVariable Long id) {
        ResponseEntity<LibroResponseRest> response = service.eliminar(id);
        return response;
    }
}