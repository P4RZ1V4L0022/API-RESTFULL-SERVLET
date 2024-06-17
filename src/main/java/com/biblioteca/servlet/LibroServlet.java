package com.biblioteca.servlet;

import com.biblioteca.dataBase.dao.LibroDao;
import com.biblioteca.dataBase.entidad.Libro;
import com.biblioteca.dataBase.repositorio.LibroRepositorio;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "libros", value = "/libros")
public class LibroServlet extends HttpServlet {
    private LibroRepositorio libroServicio = new LibroDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);

        if (id != null && !id.isEmpty()){
            Libro libro = libroServicio.getLibroById(Integer.parseInt(id));
            resp.setContentType("application/json");
            Gson gson = new Gson();
            String json = gson.toJson(libro);
            resp.getWriter().write(json);
        }else{
            List<Libro> libros = libroServicio.getAllLibros();
            resp.setContentType("application/json");
            Gson gson = new Gson();
            String json = gson.toJson(libros);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Libro libro = gson.fromJson(req.getReader(), Libro.class);
        libroServicio.saveLibro(libro);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Libro libro = gson.fromJson(req.getReader(), Libro.class);
        Libro libroExistente = libroServicio.getLibroById(libro.getId());

        if (libroExistente != null) {
            libroExistente.setTitulo(libro.getTitulo());
            libroExistente.setAutor(libro.getAutor());
            libroServicio.updateLibro(libroExistente);
            resp.setStatus(200);
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        libroServicio.deleteLibro(Integer.parseInt(id));
        resp.setStatus(200);
    }
}
