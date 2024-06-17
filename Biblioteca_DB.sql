CREATE DATABASE IF NOT EXISTS biblioteca;

USE biblioteca;

CREATE TABLE IF NOT EXISTS libros (
  id INT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  autor VARCHAR(255) NOT NULL
);

INSERT INTO libros (titulo, autor) VALUES
  ('El Señor de los Anillos', 'J.R.R. Tolkien'),
  ('La Sombra del Viento', 'Carlos Ruiz Zafón'),
  ('El Aleph', 'Jorge Luis Borges'),
  ('1984', 'George Orwell'),
  ('La Iliada', 'Homero'),
  ('El Principito', 'Antoine de Saint-Exupéry'),
  ('Cien años de soledad', 'Gabriel García Márquez'),
  ('La Odisea', 'Homero'),
  ('El Amor en los Tiempos del Cólera', 'Gabriel García Márquez');