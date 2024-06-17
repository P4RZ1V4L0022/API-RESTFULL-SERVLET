package com.biblioteca.dataBase.dao;

import com.biblioteca.dataBase.entidad.Libro;
import com.biblioteca.dataBase.repositorio.LibroRepositorio;
import com.biblioteca.mysql.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDao implements LibroRepositorio {

    @Override
    public List<Libro> getAllLibros() {
        List<Libro> libros = new ArrayList<>();

        String query = "SELECT * FROM libros";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libros;
    }

    @Override
    public Libro getLibroById(int id) {
        Libro libro = null;
        String query = "SELECT * FROM libros WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }

    @Override
    public void saveLibro(Libro libro) {
        String query = "INSERT INTO libros (titulo, autor) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateLibro(Libro libro) {
        String query="UPDATE libros SET titulo = ?, autor = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLibro(int id) {
        String query="DELETE FROM libros WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
