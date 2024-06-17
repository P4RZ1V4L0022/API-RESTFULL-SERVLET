package com.biblioteca.dataBase.repositorio;

import com.biblioteca.dataBase.entidad.Libro;

import java.util.List;

public interface LibroRepositorio {
    List<Libro> getAllLibros();
    Libro getLibroById(int id);
    void saveLibro(Libro libro);
    void updateLibro(Libro libro);
    void deleteLibro(int id);
}