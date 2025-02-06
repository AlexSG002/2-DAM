package dao;

import model.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berto
 */

public class PeliculaDao {
    private Connection connection;

    public PeliculaDao(Connection connection) {
        this.connection = connection;
    }

    public void agregarPelicula(Pelicula pelicula) throws SQLException {
        String sql = "INSERT INTO peliculas (nombre, genero, visto, cine_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pelicula.getNombre());
            statement.setString(2, pelicula.getGenero());
            statement.setBoolean(3, pelicula.isVisto());
            statement.setInt(4, pelicula.getCineId());
            statement.executeUpdate();
        }
    }

    public List<Pelicula> obtenerPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String genero = resultSet.getString("genero");
                boolean visto = resultSet.getBoolean("visto");
                int cineId = resultSet.getInt("cine_id");
                peliculas.add(new Pelicula(id, nombre, genero, visto, cineId));
            }
        }
        return peliculas;
    }

    // Método para obtener géneros únicos desde la tabla de películas
    public List<String> obtenerGenerosUnicos() throws SQLException {
        List<String> generos = new ArrayList<>();
        String sql = "SELECT DISTINCT genero FROM peliculas"; // Obtener valores únicos

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                generos.add(resultSet.getString("genero"));
            }
        }
        return generos;
    }
    
    public String obtenerNombreCine(int cineId) throws SQLException {
    String nombreCine = "Desconocido"; // Valor por defecto si no se encuentra el cine
    String sql = "SELECT nombre FROM cines WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, cineId);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                nombreCine = resultSet.getString("nombre");
            }
        }
    }
    return nombreCine;
}
}